package model;

import controller.DBConnection;
import controller.PopupMessageController;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This model class got methods in it for processing database-related things. It's especially used for executing queries.
 * It interacts via DBConnection controller class with the view.
 *
 * @author Daniel, Valentin, Marius
 * @see DBConnection
 * @see DBHelpers
 */
public class DBWorker {
    DBHelpers dbHelpers = new DBHelpers();
    PopupMessageController popupMessageController = new PopupMessageController();
    ResourcesGetter resourcesGetter = new ResourcesGetter();
    Sizes sizes = new Sizes();
    String foreignKeys = "";
    String primaryKeys = "";

    /**
     * Method for connecting to database.
     *
     * @param connectionString (JDBC string)
     * @param username         (as String)
     * @param password         (as String)
     * @return Database connection as type Connection
     * @return Daniel
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection connectToDB(String connectionString, String username, String password) throws SQLException, ClassNotFoundException {
        if (connectionString.contains("postgresql")) {
            //necessary for getting driver when running .jar
            Class.forName("org.postgresql.Driver");
        }
        return DriverManager.getConnection(connectionString, username, password);
    }

    /**
     * Method for getting all tables in database.
     *
     * @param connection
     * @return all table names from database in a string list
     * @throws SQLException
     * @author Daniel
     */
    public List<String> getAllTablesFromDB(Connection connection) throws SQLException {
        List<String> allTables = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet;
        if (connection.getMetaData().getURL().contains("postgresql")) {
            //@author Valentin
            resultSet = databaseStatement.executeQuery("SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname='public';");
        } else {
            resultSet = databaseStatement.executeQuery("SHOW tables");
        }
        while (resultSet.next()) {
            allTables.add(resultSet.getString(1));
        }
        System.out.println("Got all tables from database");
        return allTables;
    }

    /**
     * Method for getting all columns from a particular table.
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @return all columns from a table as a list
     * @throws SQLException
     */
    public List<String> getColumnsFromTable(Connection connection, String table) throws SQLException {
        List<String> allColumns = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT * FROM " + table
        );
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            allColumns.add(resultSetMetaData.getColumnName(i));
        }
        System.out.println("Got all columns from " + table);
        return allColumns;
    }

    /**
     * Method for getting all rows from a particular table.
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @return two-dimensional string array (first dimension is column, second rows)
     * @throws SQLException
     * @author Daniel
     */
    public String[][] getRowsFromTable(Connection connection, String table) throws SQLException {
        int countRows = 0;
        List<String> columns = getColumnsFromTable(connection, table);
        List<String> allRows = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT * FROM " + table
        );
        while (resultSet.next()) {
            for (int i = 1; i <= columns.size(); i++) {
                allRows.add(resultSet.getString(i));
            }
            countRows++;
        }
        String[][] rowsFromTable = new String[countRows + 1][columns.size() + 1];
        for (int y = 0; y < countRows; y++) {
            for (int x = 0; x < columns.size(); x++) {
                rowsFromTable[y][x] = allRows.get(x + (y * columns.size()));
            }
        }
        return rowsFromTable;
    }

    /**
     * Method for deleting particular rows.
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @param columns    (columns where rows aren't empty as String List)
     * @param rows       (rows which aren't empty as String List)
     * @throws SQLException
     * @author Daniel
     * @important this method isn't working with postgresql. See comments for more information.
     */
    public void deleteRows(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        String deleteQuery = "DELETE FROM " + table + " WHERE ";
        List<String> columnDatatype = dbHelpers.getColumnMetadata(connection, table, 0);
        //building whole delete query with properties
        for (int i = 0; i < columns.size(); i++) {
            if (i == columns.size() - 1) {
                deleteQuery += columns.get(i) + " = ? ;";
            } else {
                deleteQuery += columns.get(i) + " = ?" + " AND ";
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        //adding properties to the query
        for (int x = 1; x <= rows.size(); x++) {
            //if statement needed for postgresql because strict types are required (so you have to set int by "setInt" and so on) --> all datatypes are needed for getting this to work.
            if (columnDatatype.get(x - 1).contains("int") || columnDatatype.get(x - 1).contains("serial")) {
                preparedStatement.setInt(x, Integer.parseInt(rows.get(x - 1)));
            } else {
                preparedStatement.setString(x, rows.get(x - 1));
            }
        }
        preparedStatement.executeUpdate();
        popupMessageController.showSuccessMessage("Successfully deleted row");
    }

    public void addRow(Connection connection, String table) throws SQLException {
        String addQuery = "INSERT INTO " + table + "(";
        List<String> columns = getAllWriteableColumns(connection, table);
        String[] input = getInputDialogForCreatingNewRow(connection, table, columns);
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        if (input != null) {
            for (int i = 0; i < columns.size(); i++) {
                if (i == columns.size() - 1) {
                    addQuery += columns.get(i) + ") VALUES(";
                } else {
                    addQuery += columns.get(i) + ", ";
                }
            }
            for (int x = 0; x < columns.size(); x++) {
                if (x == columns.size() - 1) {
                    if (connection.getMetaData().getURL().contains("postgresql")) {
                        addQuery += "?::" + resultSetMetaData.getColumnTypeName(x + 1) + ");";

                    } else {
                        addQuery += "?" + ");";
                    }

                } else {
                    if (connection.getMetaData().getURL().contains("postgresql")) {
                        addQuery += "?::" + resultSetMetaData.getColumnTypeName(x + 1) + ", ";

                    } else {
                        addQuery += "?" + ", ";
                    }

                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            for (int y = 1; y <= input.length; y++) {
                if (input[y - 1].equals("") && connection.getMetaData().getURL().contains("postgresql")) {
                    preparedStatement.setNull(y, java.sql.Types.NULL);
                } else {
                    preparedStatement.setObject(y, input[y - 1]);
                }
            }
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            popupMessageController.showSuccessMessage("Successfully added row");
        }

    }

    public void editRow(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        //if a column isn't writable it will shown anyways - but its not editable - this list is needed to compare columns with the writable columns to find out which is readonly
        List<String> writableColumns = getAllWriteableColumns(connection, table);
        String editQuery = "UPDATE " + table + " SET ";
        String[] input = getInputDialogForEditingRow(connection, table, columns, rows, writableColumns);
        for (int i = 0; i < columns.size(); i++) {
            if (i == columns.size() - 1) {
                editQuery += columns.get(i) + "=" + "?" + " WHERE ";
            } else {
                editQuery += columns.get(i) + "=" + "?" + ",";
            }
        }
        for (int x = 0; x < columns.size(); x++) {
            //check if there is a row wich is empty, if so don't add it t the query
            if (!(rows.get(x).isEmpty())) {
                if (x == columns.size() - 1) {
                    editQuery += columns.get(x) + "=" + "?" + ";";
                } else {
                    editQuery += columns.get(x) + "=" + "?" + " AND ";
                }
            }
        }
        if (editQuery.endsWith(" AND ")) {
            editQuery = editQuery.replaceAll("AND $", " ");
            System.out.println("Edit query: " + editQuery);
        }

        PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
        //Test getting type
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int y = 1; y <= input.length; y++) {
            if (resultSetMetaData.getColumnTypeName(y).contains("int") || resultSetMetaData.getColumnTypeName(y).contains("serial")) {
                preparedStatement.setInt(y, Integer.parseInt(input[y - 1]));
            } else {
                preparedStatement.setString(y, input[y - 1]);
            }
        }
        //remove empty rows from row
        for (int z = rows.size() - 1; z >= 0; z--) {
            if (rows.get(z).equals("")) {
                rows.remove(z);
            }
        }
        int test = 0;
        for (int xy = input.length + 1; xy <= input.length + rows.size(); xy++) {
            if (resultSetMetaData.getColumnTypeName(test + 1).contains("int") || resultSetMetaData.getColumnTypeName(test + 1).contains("serial")) {
                preparedStatement.setInt(xy, Integer.parseInt(rows.get(test)));
            } else {
                preparedStatement.setString(xy, rows.get(test));
            }
            test++;
            System.out.println("Ich bin hier");
        }
        //what is this needed for?
        //preparedStatement.setString(input.length+1, rows.get(0));
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
        popupMessageController.showSuccessMessage("Successfully edited row");

    }

    private List<String> columnsType = new ArrayList<>();
    private List<Integer> columnsTypeLength = new ArrayList<>();

    //needed for adding row
    private List<String> getAllWriteableColumns(Connection connection, String table) throws SQLException {
        List<String> columnsWriteable = new ArrayList<>();
        List<String> columnsType = new ArrayList<>();
        List<Integer> columnsTypeLength = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            if (!(resultSetMetaData.isReadOnly(i))) {
                columnsWriteable.add(resultSetMetaData.getColumnName(i));
                columnsType.add(resultSetMetaData.getColumnTypeName(i));
                columnsTypeLength.add(resultSetMetaData.getColumnDisplaySize(i));
            }
        }
        this.columnsType = columnsType;
        this.columnsTypeLength = columnsTypeLength;
        return columnsWriteable;
    }

    //needed for adding row
    private String[] getInputDialogForCreatingNewRow(Connection connection, String table, List<String> columns) throws SQLException {
        JTextField[] inputFields = new JTextField[columns.size()];
        JLabel[] labels = new JLabel[columns.size()];
        String input[] = new String[columns.size()];
        //pseudo elements (optional parameter)
        List<String> rows = new ArrayList<>();
        List<String> writableColumns = new ArrayList<>();
        JOptionPane pane = new JOptionPane();
        pane.setBounds(sizes.getScreenWidth() / 2, sizes.getScreenHeight() / 2, sizes.getScreenWidth() / 2, sizes.getScreenHeight() / 2);
        int option = pane.showConfirmDialog(null, createContainerForJOptionPane(connection, table, columns, labels, inputFields, columnsType, columnsTypeLength, false, rows, writableColumns), "Add rows", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            for (int x = 0; x < columns.size(); x++) {
                input[x] = inputFields[x].getText();
            }
        } else {
            input = null;
        }
        return input;
    }

    private String[] getInputDialogForEditingRow(Connection connection, String table, List<String> columns, List<String> rows, List<String> writableColumns) throws SQLException {
        String input[] = new String[columns.size()];
        JPanel container = new JPanel(new GridLayout(columns.size(), 2));
        JLabel[] labelForColumns = new JLabel[columns.size()];
        JTextField[] inputFields = new JTextField[columns.size()];
        JOptionPane pane = new JOptionPane();
        pane.setBounds(sizes.getScreenWidth() / 2, sizes.getScreenHeight() / 2, sizes.getScreenWidth() / 2, sizes.getScreenHeight() / 2);
        int option = pane.showConfirmDialog(null, createContainerForJOptionPane(connection, table, columns, labelForColumns, inputFields, columnsType, columnsTypeLength, true, rows, writableColumns), "Edit rows", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            for (int x = 0; x < columns.size(); x++) {
                input[x] = inputFields[x].getText();
            }
        } else {
            input = null;
        }
        return input;
    }

    public void addImportedRows(Connection connection, String table) throws SQLException, IOException {
        System.out.println(table);
        //Insert into table(n, x, y...) Values(
        String addQueryMeta = "INSERT INTO " + table + "(";
        //String addQueryMeta = "INSERT INTO `" + table + " VALUES(";
        String addQueryValues = "";
        String columns = "";
        List<List<String>> data = resourcesGetter.getColumnsAndRowsFromCSV();
        for (int i = 0; i < data.get(0).size(); i++) {
            if (i == data.get(0).size() - 1) {
                addQueryMeta += data.get(0).get(i) + ") VALUES(";
            } else {
                addQueryMeta += data.get(0).get(i) + ", ";
            }
            columns += data.get(0).get(i) + ";";
        }
        //x --> rows; y --> columns in rows
        for (int x = 1; x < data.size(); x++) {
            for (int y = 0; y < data.get(x).size(); y++) {
                if (y == data.get(x).size() - 1) {
                    addQueryValues += "?" + ");";
                } else {
                    addQueryValues += "?" + ", ";
                }

            }
            addQueryMeta = addQueryMeta.replace("\uFEFF", "");
            addQueryValues = addQueryValues.replace("\uFEFF", "");
            System.out.println("Pre Prepared statement: " + addQueryMeta + addQueryValues);
            PreparedStatement preparedStatement = connection.prepareStatement(addQueryMeta + addQueryValues);
            for (int n = 1; n <= data.get(x).size(); n++) {
                preparedStatement.setString(n, data.get(x).get(n - 1));
            }
            System.out.println("Prepared statement: " + preparedStatement);
            preparedStatement.executeUpdate();
            addQueryValues = "";
        }

    }

    private JPanel createContainerForJOptionPane(Connection connection, String table, List<String> columns, JLabel[] labelForColumns, JTextField[] inputFields, List<String> columnsType, List<Integer> columnsTypeLength, Boolean isEdit, List<String> rows, List<String> writableColumns) throws SQLException {
        List<String> allKeys = dbHelpers.getAllKeys(connection, table);
        String numbers = "123456789";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = ";,+*#'_^^´`;";
        JPanel container = new JPanel(new GridLayout(columns.size(), 2));
        for (int i = 0; i < labelForColumns.length; i++) {
            //on the left: lables
            labelForColumns[i] = new JLabel(columns.get(i) + " (" + columnsType.get(i) + ", " + columnsTypeLength.get(i) + ")");
            container.add(labelForColumns[i]);
            //on the right-handed side: textfields
            if (isEdit) {
                inputFields[i] = new JTextField(rows.get(i));
                if ((!(writableColumns.contains(columns.get(i)))) || allKeys.get(0).contains(table.toLowerCase() + "." + columns.get(i))) {
                    inputFields[i].setBackground(Color.YELLOW);
                    inputFields[i].setToolTipText("This field is a primary key and can't be changed because it's used in other tables.");
                } else if (allKeys.get(1).contains(table.toLowerCase() + "." + columns.get(i))) {
                    inputFields[i].setBackground(Color.YELLOW);
                    inputFields[i].setToolTipText("Mind that the data from this row comes from another table (foreign key).");
                }
            } else {
                inputFields[i] = new JTextField();
            }
            int index = i;

            //validate input
            inputFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (columnsType.get(index) == "DATE" && (alphabet.contains(String.valueOf(e.getKeyChar())) || alphabet.toLowerCase().contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())))) {
                        JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                        inputFields[index].setText("");
                    }
                    if (columnsTypeLength.get(index) < inputFields[index].getText().length()) {
                        JOptionPane.showMessageDialog(null, "Input to long. Maximum size: " + columnsTypeLength.get(index));
                        inputFields[index].setText("");
                    }
                    if (columnsType.get(index) == "INT" && (alphabet.contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())) || alphabet.toLowerCase().contains(String.valueOf(e.getKeyChar())))) {
                        JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                        inputFields[index].setText("");
                    }
                    if (columnsType.get(index) == "TEXT" && (numbers.contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())))) {
                        JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                        inputFields[index].setText("");
                    }
                }
            });
            container.add(inputFields[i]);
        }
        return container;
    }



    /**
     * Method is used for clearing table.
     * @param connection (connection as type Connection)
     * @param table (as table name)
     * @throws SQLException
     * @author Marius
     */
    public void clearTable(Connection connection, String table) throws SQLException {
        String deleteQuery = "DELETE FROM " + table;
        PreparedStatement st = connection.prepareStatement(deleteQuery);
        st.executeUpdate();
        popupMessageController.showSuccessMessage("Successfully cleared table");
    }

}
