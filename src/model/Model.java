package model;

import controller.DBConnection;

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
    public List<String> getColumnsFromTable(String table) throws SQLException {
        List<String> allColumns = new ArrayList<>();
        Statement databaseStatement = dbConnection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT COLUMN_NAME\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_NAME ='" + table + "'\n" +
                "ORDER BY ORDINAL_POSITION"
        );
        while (resultSet.next()) {
            allColumns.add(resultSet.getString(1));
        }
        return allColumns;
    }
    public List<String> getRowsFromTable(List<String> columns, String table) throws SQLException {
        List<String> allRows = new ArrayList<>();
        Statement databaseStatement = dbConnection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT * from " + table
        );
        while (resultSet.next()) {
            for(int i = 1; i <= columns.size(); i++) {
                allRows.add(resultSet.getString(i));
            }
        }
        return allRows;
    }
}
