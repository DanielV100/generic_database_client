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
        getColumnsFromTable();
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
    public void getColumnsFromTable() throws SQLException {
        Statement databaseStatement = dbConnection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT COLUMN_NAME\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_NAME = 'dataTable'\n" +
                "ORDER BY ORDINAL_POSITION"
        );
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }
}
