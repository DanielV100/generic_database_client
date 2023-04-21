package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.sql.*;

public class DBConnection {
    Model model = new Model();
    String hostname;
    String databaseName;
    String port;
    String username;
    String password;
    Connection dbConnection;

    //gets parameters and writes it into local variables + start building connection
    public Connection initDBConnection(String hostname, String port, String databaseName, String username, String password) throws SQLException {
        this.hostname = hostname;
        this.port = validatePort(port);
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        return connect();
    }
    //should be validated --> numbers no chars
    private String validatePort(String port) {
        return port;
    }
    public String createConnectionString(String hostname, String port, String databaseName) {
        return "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;
    }

    //trying to connect to db via jdbc - driver is needed!
    public Connection connect() throws SQLException {
        return model.connectToDB(createConnectionString(hostname, port, databaseName), username, password);
    }


    //getting all tables in the db and parsing the list to a string array, which is than be shown in a JList
    public String[] getAllTablesFromDB(Connection connection) throws SQLException {
        return model.getAllTablesFromDB(connection).toArray(new String[0]);
    }
}
//https://www.youtube.com/watch?v=1xF_PFJLs4g
