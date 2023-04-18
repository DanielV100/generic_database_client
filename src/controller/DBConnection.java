package controller;

import view.PanelDatabaseConnection;

public class DBConnection {
    PanelDatabaseConnection panelDatabaseConnection = new PanelDatabaseConnection();

    //getting hostname from text field
    private String getHostname() {
        return panelDatabaseConnection.getTextFieldHostname().getText();
    }
    //getting port from text field
    private String getPort() {
        return panelDatabaseConnection.getTextFieldPort().getText();
    }
    //getting database name from text field
    private String getDatabaseName() {
        return panelDatabaseConnection.getTextFieldDatabaseName().getText();
    }
}
