package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    String hostname;
    String databaseName;
    String port;
    String username;
    String password;

    public void initDBConnection(String hostname, String port, String databaseName, String username, String password) throws SQLException {
        this.hostname = hostname;
        this.port = validatePort(port);
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        connect();
    }
    private String validatePort(String port) {
        return port;
    }
    private String createConnectionString(String hostname, String port, String databaseName) {
        return "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;
    }


    public void connect() throws SQLException {
        try {
            System.out.println(createConnectionString(hostname, port, databaseName));
            Connection conn = DriverManager.getConnection(createConnectionString(hostname, port, databaseName), username, password);
            System.out.println("Connection succeded!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }

    }
}

