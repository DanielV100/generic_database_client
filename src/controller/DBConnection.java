package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //getting hostname from text field
    private String getHostname() {
        return "panelDatabaseConnection.getTextFieldHostname().getText()";
    }

    //getting port from text field
    private String getPort() {
        return "panelDatabaseConnection.getTextFieldPort().getText()";
    }

    //getting database name from text field
    private String getDatabaseName() {
        return "panelDatabaseConnection.getTextFieldDatabaseName().getText()";
    }

    public void connect() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_database", "root", "4a22w0rt!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }

    }
}

