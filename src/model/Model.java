package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    Connection dbConnection;

    //create connection to db
    public void connectToDB(String connectionString, String username, String password) throws SQLException {
        dbConnection = DriverManager.getConnection(connectionString, username, password);
        getAllTablesFromDB();
    }
    //getting sll tables in db and return it as a list
    public List<String> getAllTablesFromDB() throws SQLException {
        List<String> allTables = new ArrayList<>();
        Statement databaseStatement = dbConnection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("Show tables");
        while (resultSet.next()) {
            allTables.add(resultSet.getString(1));
        }
        System.out.println(allTables.get(1));
        return allTables;
    }
}
