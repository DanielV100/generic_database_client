package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.sql.*;
import java.util.List;

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
    public String[] getColumnsFromTable (Connection connection, int tableIndex) throws SQLException {
        return model.getColumnsFromTable(connection, model.getAllTablesFromDB(connection).get(tableIndex)).toArray(new String[0]);
    }
    public String[][] getRowsFromTable(Connection connection, String[] columns, int tableIndex) throws SQLException {
        return model.getRowsFromTable(connection, columns, model.getAllTablesFromDB(connection).get(tableIndex));
    }
    public void deleteRow(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        model.deleteRows(connection, table, columns, rows);
    }
    public void addRow(Connection connection, String table) throws SQLException {
        model.addRow(connection, table);
    }
    public void editRow(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        model.editRow(connection, table, columns, rows);
    }
    public void addImportedRows(Connection connection, String table) throws SQLException, IOException {
        model.addImportedRows(connection, table);
    }
}
