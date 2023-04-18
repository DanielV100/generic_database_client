package controller;

import model.Model;

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
    public void initDBConnection(String hostname, String port, String databaseName, String username, String password) throws SQLException {
        this.hostname = hostname;
        this.port = validatePort(port);
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        connect();
    }
    //should be validated --> numbers no chars
    private String validatePort(String port) {
        return port;
    }
    private String createConnectionString(String hostname, String port, String databaseName) {
        return "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;
    }

    public void connect() throws SQLException {
        model.connectToDB(createConnectionString(hostname, port, databaseName), username, password);
    }
}

