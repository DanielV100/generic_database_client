package model;

import controller.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    Connection dbConnection;

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
        System.out.println(deleteQuery);
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
            JOptionPane.showMessageDialog(null, "Added row!");
        }

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
        int option = JOptionPane.showConfirmDialog(null, inputFields, "Add rows", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            for(int x = 0; x < columns.size(); x++) {
                input[x] = inputFields[x].getText();
            }
        } else {
            input = null;
        }
        return input;
    }
}
