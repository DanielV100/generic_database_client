package controller;

import model.DBHelpers;
import model.DBWorker;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * This is the controller class which is used for all database related things. It's the communicator between view and DBWorker.
 * @see DBWorker
 * @author Daniel
 */
public class DBConnection {
    DBHelpers dbHelpers = new DBHelpers();
    DBWorker DBWorker = new DBWorker();
    public Connection initDBConnection(String hostname, String port, String databaseName, String username, String password, String selectedDB) throws SQLException, ClassNotFoundException {
        return DBWorker.connectToDB(createConnectionString(hostname, port, databaseName, selectedDB), username, password);
    }
    public String createConnectionString(String hostname, String port, String databaseName, String selectedDB) {
        return "jdbc:"+ selectedDB +"://" + hostname + ":" + port + "/" + databaseName;
    }


    //getting all tables in the db and parsing the list to a string array, which is than be shown in a JList
    public String[] getAllTablesFromDB(Connection connection) throws SQLException {
        return DBWorker.getAllTablesFromDB(connection).toArray(new String[0]);
    }
    public String[] getColumnsFromTable (Connection connection, int tableIndex) throws SQLException {
        return DBWorker.getColumnsFromTable(connection, DBWorker.getAllTablesFromDB(connection).get(tableIndex)).toArray(new String[0]);
    }
    public String[][] getRowsFromTable(Connection connection, int tableIndex) throws SQLException {
        return DBWorker.getRowsFromTable(connection, DBWorker.getAllTablesFromDB(connection).get(tableIndex));
    }
    public void deleteRow(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        DBWorker.deleteRows(connection, table, columns, rows);
    }
    public void addRow(Connection connection, String table) throws SQLException {
        DBWorker.addRow(connection, table);
    }
    public void editRow(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        DBWorker.editRow(connection, table, columns, rows);
    }
    public void addImportedRows(Connection connection, String table) throws SQLException, IOException {
        DBWorker.addImportedRows(connection, table);
    }
    public void clearTable(Connection connection, String table) throws SQLException {
       DBWorker.clearTable(connection, table);
    }
    public List<String> getColumnMetadata(Connection connection, String table, int metadataType) throws SQLException {
        return dbHelpers.getColumnMetadata(connection, table, metadataType);
    }
}
