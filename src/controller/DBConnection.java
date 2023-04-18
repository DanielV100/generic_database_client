package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DBConnection {
    String hostname;
    String databaseName;
    String port;
    String username;
    String password;

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
        try {
            Connection conn = DriverManager.getConnection(createConnectionString(hostname, port, databaseName), username, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }

    }
}

