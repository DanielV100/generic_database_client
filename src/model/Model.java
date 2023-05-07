package model;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    ImportFilesGetter importFilesGetter = new ImportFilesGetter();
    Sizes sizes = new Sizes();
    //create connection to db
    public Connection connectToDB(String connectionString, String username, String password) throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }
    //getting all tables in db and return it as a list
    public List<String> getAllTablesFromDB(Connection connection) throws SQLException {
        List<String> allTables = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("Show tables");
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
        for(int i = 0; i < columns.size(); i++){
            if(i == columns.size() - 1 ){
                deleteQuery += columns.get(i) + " = ?" + ";";
            } else {
                deleteQuery += columns.get(i) + " = ?" + " AND ";
            }

        }
        PreparedStatement st = connection.prepareStatement(deleteQuery);
        for (int x = 1; x <= rows.size(); x++){
            st.setString(x, rows.get(x-1));
        }
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Row deleted!");
    }
    public void addRow(Connection connection, String table) throws SQLException {
        String addQuery = "INSERT INTO " + table + "(";
        List<String> columns = getAllWriteableColumns(connection, table);
        String[] input = getInputDialogForCreatingNewRow(columns);
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
        String editQuery = "UPDATE " + table + " SET ";
        String[] input = getInputDialogForEditingRow(columns, rows);
        for (int i = 0; i < columns.size(); i++) {
            if(i == columns.size() - 1) {
                editQuery += columns.get(i) + "=" + "?" + " WHERE ";
            } else {
                editQuery += columns.get(i) + "=" + "?" + ",";
            }
        }
        for (int x = 0; x < columns.size(); x++) {
            if(x == columns.size() - 1) {
                editQuery += columns.get(x) + "=" + "?" + ";";
            } else {
                editQuery += columns.get(x) + "=" + "?" + " AND ";
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
        for (int y = 1; y <= input.length; y++) {
            preparedStatement.setString(y, input[y-1]);
        }
        int test = 0;
        for (int xy = input.length+1; xy <= input.length + rows.size(); xy++) {
            preparedStatement.setString(xy, rows.get(test));
            test++;
        }
        preparedStatement.setString(input.length+1, rows.get(0));
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Edited row!");

    }
    //needed for adding row
    private List<String> getAllWriteableColumns(Connection connection, String table) throws SQLException {
        List<String> columnsWriteable = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            if(!(resultSetMetaData.isReadOnly(i))) {
                columnsWriteable.add(resultSetMetaData.getColumnName(i));
            }
        }
        return columnsWriteable;
    }
    //needed for adding row
    private String[] getInputDialogForCreatingNewRow(List<String> columns) {
        JTextField[] inputFields = new JTextField[columns.size()];
        String input[] = new String[columns.size()];
        for(int i = 0; i < columns.size(); i++) {
            inputFields[i] = new JTextField(columns.get(i));
        }
        JOptionPane pane = new JOptionPane();
        pane.setBounds(sizes.getScreenWidth()/2, sizes.getScreenHeight()/2, sizes.getScreenWidth()/2,sizes.getScreenHeight()/2);
        int option = pane.showConfirmDialog(null, inputFields, "Add rows", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            for(int x = 0; x < columns.size(); x++) {
                input[x] = inputFields[x].getText();
            }
        } else {
            input = null;
        }
        return input;
    }
    private String[] getInputDialogForEditingRow(List<String> columns, List<String> rows) {
        String input[] = new String[columns.size()];
        JPanel container = new JPanel(new GridLayout(columns.size(), 2));
        JLabel[] labelForColumns = new JLabel[columns.size()];
        JTextField[] inputFields = new JTextField[columns.size()];
        for (int i = 0; i < labelForColumns.length; i++) {
            //on the left: lables
            labelForColumns[i] = new JLabel(columns.get(i));
            container.add(labelForColumns[i]);
            //on the right-handed side: textfields
            inputFields[i] = new JTextField(rows.get(i));
            container.add(inputFields[i]);
        }
        JOptionPane pane = new JOptionPane();
        pane.setBounds(sizes.getScreenWidth()/2, sizes.getScreenHeight()/2, sizes.getScreenWidth()/2,sizes.getScreenHeight()/2);
        int option = pane.showConfirmDialog(null, container, "Edit rows", JOptionPane.OK_CANCEL_OPTION);
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
        List<List<String>> data = importFilesGetter.getColumnsAndRowsFromCSV();
        for (int i = 0; i < data.get(0).size(); i++) {
            if(i == data.get(0).size() - 1) {
                addQueryMeta += data.get(0).get(i) +") VALUES(";
            } else {
                addQueryMeta +=data.get(0).get(i) + ", ";
            }
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
}
