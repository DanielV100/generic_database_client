package model;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import view.PanelDatabaseConnection;
import controller.DBConnection;
import static view.PanelDatabaseConnection.selectedDB;

public class Model {
    ImportFilesGetter importFilesGetter = new ImportFilesGetter();
    Sizes sizes = new Sizes();
    String foreignKeys = "";
    String primaryKeys = "";
    // In use for different SQL-Queries in PostgreSQL
//    String selectedDBValue = PanelDatabaseConnection.selectedDB;
    String databaseNameValue = DBConnection.databaseName;
    //create connection to db
    public Connection connectToDB(String connectionString, String username, String password) throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }
    //getting all tables in db and return it as a list
    public List<String> getAllTablesFromDB(Connection connection) throws SQLException {
        List<String> allTables = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet;
        if (selectedDB == "postgresql") {
            resultSet = databaseStatement.executeQuery("SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname='public';");

        } else {
            resultSet = databaseStatement.executeQuery("Show tables");
        }
        while (resultSet.next()) {
            allTables.add(resultSet.getString(1));
        }
        return allTables;
    }
    public List<String> getColumnsFromTable(Connection connection, String table) throws SQLException {
        List<String> allColumns = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "Select * from " + table
        );
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            allColumns.add(resultSetMetaData.getColumnName(i));
        }
        return allColumns;
    }
    public String[][] getRowsFromTable(Connection connection, String[] columns, String table) throws SQLException {
        //counts how many rows are in the table
        int countRows = 0;
        List<String> allRows = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT * from " + table
        );
        while (resultSet.next()) {
            for(int i = 1; i <= columns.length; i++) {
                allRows.add(resultSet.getString(i));
            }
            countRows++;
        }
        System.out.println(countRows);
        String[][] test = new String[countRows + 1][columns.length + 1];
        for(int y = 0; y < countRows; y++) {
            for(int x = 0; x < columns.length; x++){
                test[y][x] = allRows.get(x + (y*columns.length));
            }
        }
        return test;
    }

    public void deleteRows(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        String deleteQuery = "DELETE FROM " + table + " WHERE ";
        System.out.println("Test");
        for(int i = 0; i < columns.size(); i++){

            if(i == columns.size() - 1 ){
                deleteQuery += columns.get(i) + " = ? ;";
                System.out.println("Delete Quary: "+ deleteQuery);
            } else {
                deleteQuery += columns.get(i) + " = ?" + " AND ";
            }

            /*if (selectedDB == "postgresql") {
                System.out.println("Delete Query für PostgreSQL");
                if(i == columns.size() - 1 ){
                    // column_name muss durch den Namen der Spalte augetauscht werden
                    deleteQuery += columns.get(i) + " column_name = ?" + ";";
                } else {
                    deleteQuery += columns.get(i) + " = ?" + " AND ";
                }
            } else {
                if(i == columns.size() - 1 ){
                    deleteQuery += columns.get(i) + " = ?" + ";";
                    System.out.println("Delete Quary: "+ deleteQuery);
                } else {
                    deleteQuery += columns.get(i) + " = ?" + " AND ";
                }
            }*/
        }
        PreparedStatement st = connection.prepareStatement(deleteQuery);
        //Test getting type
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int x = 1; x <= rows.size(); x++){
            if(resultSetMetaData.getColumnTypeName(x).contains("int") || resultSetMetaData.getColumnTypeName(x).contains("serial")) {
                st.setInt(x, Integer.parseInt(rows.get(x-1)));
            } else {
                st.setString(x, rows.get(x-1));
            }
        }
        System.out.println("Fertiger String: " + st);
        st.execute();
        JOptionPane.showMessageDialog(null, "Row deleted!");
    }
        public void addRow(Connection connection, String table) throws SQLException {
        String addQuery = "INSERT INTO " + table + "(";
        List<String> columns = getAllWriteableColumns(connection, table);
        String[] input = getInputDialogForCreatingNewRow(connection, table, columns);
        if(input != null) {
            for(int i = 0; i < columns.size(); i++) {
                if(i == columns.size() - 1) {
                    addQuery += columns.get(i) + ") VALUES(";
                } else {
                    addQuery += columns.get(i) + ", ";
                }
            }
            for(int x = 0; x < columns.size(); x++) {
                if(x == columns.size() - 1) {
                    addQuery += "?" + ");";
                } else {
                    addQuery += "?" + ", ";
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            for(int y = 1; y <= input.length; y++) {
                preparedStatement.setString(y, input[y-1]);
            }
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
            JOptionPane.showMessageDialog(null, "Added row!");
        }

    }
    public void editRow(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        //if a column isn't writable it will shown anyways - but its not editable - this list is needed to compare columns with the writable columns to find out which is readonly
        List<String> writableColumns = getAllWriteableColumns(connection, table);
        String editQuery = "UPDATE " + table + " SET ";
        String[] input = getInputDialogForEditingRow(connection, table, columns, rows, writableColumns);
        for (int i = 0; i < columns.size(); i++) {
            if(i == columns.size() - 1) {
                editQuery += columns.get(i) + "=" + "?" + " WHERE ";
            } else {
                editQuery += columns.get(i) + "=" + "?" + ",";
            }
        }
        for (int x = 0; x < columns.size(); x++) {
            //check if there is a row wich is empty, if so don't add it t the query
            if(!(rows.get(x).isEmpty())) {
                if(x == columns.size() - 1) {
                    editQuery += columns.get(x) + "=" + "?" + ";";
                } else {
                    editQuery += columns.get(x) + "=" + "?" + " AND ";
                }
            }
        }
        if(editQuery.endsWith(" AND ")) {
            editQuery = editQuery.replaceAll("AND $", " ");
            System.out.println("Edit query: " + editQuery);
        }

        PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
        //Test getting type
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int y = 1; y <= input.length; y++) {
            if(resultSetMetaData.getColumnTypeName(y).contains("int") || resultSetMetaData.getColumnTypeName(y).contains("serial")) {
                preparedStatement.setInt(y, Integer.parseInt(rows.get(y-1)));
            } else {
                preparedStatement.setString(y, rows.get(y-1));
            }
        }
        //remove empty rows from row
        for (int z = rows.size() - 1; z >= 0; z--) {
            if (rows.get(z).equals("")) {
                rows.remove(z);
            }
        }
        int test = 0;
        for (int xy = input.length+1; xy <= input.length + rows.size(); xy++) {
            if(resultSetMetaData.getColumnTypeName(xy).contains("int") || resultSetMetaData.getColumnTypeName(xy).contains("serial")) {
                preparedStatement.setInt(xy, Integer.parseInt(rows.get(test)));
            } else {
                preparedStatement.setString(xy, rows.get(test));
            }
            test++;
        }
        preparedStatement.setString(input.length+1, rows.get(0));
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Edited row!");

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
            if(!(resultSetMetaData.isReadOnly(i))) {
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
        pane.setBounds(sizes.getScreenWidth()/2, sizes.getScreenHeight()/2, sizes.getScreenWidth()/2,sizes.getScreenHeight()/2);
        int option = pane.showConfirmDialog(null, createContainerForJOptionPane(connection, table, columns, labels, inputFields, columnsType, columnsTypeLength, false, rows, writableColumns), "Add rows", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            for(int x = 0; x < columns.size(); x++) {
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
        pane.setBounds(sizes.getScreenWidth()/2, sizes.getScreenHeight()/2, sizes.getScreenWidth()/2,sizes.getScreenHeight()/2);
        int option = pane.showConfirmDialog(null, createContainerForJOptionPane(connection, table, columns, labelForColumns, inputFields, columnsType, columnsTypeLength, true, rows, writableColumns), "Edit rows", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            for(int x = 0; x < columns.size(); x++) {
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
        List<List<String>> data = importFilesGetter.getColumnsAndRowsFromCSV();
        for (int i = 0; i < data.get(0).size(); i++) {
            if(i == data.get(0).size() - 1) {
                addQueryMeta += data.get(0).get(i) +") VALUES(";
            } else {
                addQueryMeta +=data.get(0).get(i) + ", ";
            }
            columns += data.get(0).get(i) + ";";
        }
        //x --> rows; y --> columns in rows
        for(int x = 1; x < data.size(); x++) {
            for(int y = 0; y < data.get(x).size(); y++) {
                if(y == data.get(x).size() - 1) {
                    addQueryValues += "?" + ");";
                } else {
                    addQueryValues += "?" + ", ";
                }

            }
            addQueryMeta = addQueryMeta.replace("\uFEFF", "");
            addQueryValues = addQueryValues.replace("\uFEFF", "");
            System.out.println("Pre Prepared statement: " + addQueryMeta + addQueryValues);
            PreparedStatement preparedStatement = connection.prepareStatement(addQueryMeta + addQueryValues);
            for(int n = 1; n <= data.get(x).size(); n++) {
                preparedStatement.setString(n, data.get(x).get(n-1));
            }
            System.out.println("Prepared statement: " + preparedStatement);
            preparedStatement.executeUpdate();
            addQueryValues = "";
        }

    }
    private JPanel createContainerForJOptionPane(Connection connection, String table, List<String> columns, JLabel[] labelForColumns, JTextField[] inputFields, List<String> columnsType, List<Integer> columnsTypeLength, Boolean isEdit, List<String> rows, List<String> writableColumns) throws SQLException {
        getAllKeys(connection);
        String numbers = "123456789";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = ";,+*#'_^^´`;";
        JPanel container = new JPanel(new GridLayout(columns.size(), 2));
        for (int i = 0; i < labelForColumns.length; i++) {
            //on the left: lables
            labelForColumns[i] = new JLabel(columns.get(i) + " (" + columnsType.get(i) + ", " + columnsTypeLength.get(i) + ")");
            container.add(labelForColumns[i]);
            //on the right-handed side: textfields
            if(isEdit) {
                System.out.println("Foreign Keys: " + foreignKeys);
                System.out.println(table.toLowerCase() + "." + columns.get(i));
                inputFields[i] = new JTextField(rows.get(i));
                if((!(writableColumns.contains(columns.get(i))))  || primaryKeys.contains(table.toLowerCase() + "." + columns.get(i))) {
                    inputFields[i].setEditable(false);
                    inputFields[i].setBackground(Color.LIGHT_GRAY);
                    inputFields[i].setToolTipText("This field is a primary key and can't be changed because it's used in other tables.");
                } else if (foreignKeys.contains(table.toLowerCase() + "." + columns.get(i))) {
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
                        if (columnsType.get(index) == "DATE" &&(alphabet.contains(String.valueOf(e.getKeyChar())) || alphabet.toLowerCase().contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())))) {
                            JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                            inputFields[index].setText("");
                        }
                        if(columnsTypeLength.get(index) < inputFields[index].getText().length()) {
                            JOptionPane.showMessageDialog(null, "Input to long. Maximum size: " + columnsTypeLength.get(index));
                            inputFields[index].setText("");
                        }
                        if(columnsType.get(index) == "INT" && (alphabet.contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())) ||alphabet.toLowerCase().contains(String.valueOf(e.getKeyChar())))) {
                            JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                            inputFields[index].setText("");
                        }
                        if(columnsType.get(index) == "TEXT" && (numbers.contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())))) {
                            JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                            inputFields[index].setText("");
                        }
                    }
            });
            container.add(inputFields[i]);
        }
        return container;
    }
    public void getAllKeys(Connection connection) throws SQLException {
        String foreignKey = "";
        String primaryKeys = "";
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tableResultSet = databaseMetaData.getTables(null, null, "%", new String[]{"TABLE"});
        while(tableResultSet.next()) {
            String tableName = tableResultSet.getString("TABLE_NAME");
            ResultSet keyResultSet = databaseMetaData.getImportedKeys(null, null, tableName);
            while(keyResultSet.next()) {
                foreignKey += keyResultSet.getString("FKTABLE_NAME") + "." + keyResultSet.getString("FKCOLUMN_NAME") + " ";
                primaryKeys += keyResultSet.getString("PKTABLE_NAME") + "." + keyResultSet.getString("PKCOLUMN_NAME") + " ";
            }
        }
        this.primaryKeys = primaryKeys;
        this.foreignKeys = foreignKey;
        System.out.println(primaryKeys);
        System.out.println(foreignKey);
    }
    public void clearTable(Connection connection, String table) throws SQLException {
        String deleteQuery = "DELETE FROM " + table;
        PreparedStatement st = connection.prepareStatement(deleteQuery);
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cleared table");
    }
}
