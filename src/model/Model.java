package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    Connection dbConnection;

    //create connection to db
    public void connectToDB(String connectionString, String username, String password) throws SQLException {
        dbConnection = DriverManager.getConnection(connectionString, username, password);
        //getAllTablesFromDB();
        getRowsFromTable(getColumnsFromTable("myTable"), "myTable");

    }
    //getting all tables in db and return it as a list
    public List<String> getAllTablesFromDB() throws SQLException {
        List<String> allTables = new ArrayList<>();
        Statement databaseStatement = dbConnection.createStatement();
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
    public void getRowsFromTable(List<String> columns, String table) throws SQLException {
        Statement databaseStatement = dbConnection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT * from " + table
        );
        while (resultSet.next()) {
            for(int i = 1; i <= columns.size(); i++) {
                System.out.println(resultSet.getString(i));
            }
        }
    }
}
