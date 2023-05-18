package model;

import controller.DBConnection;
import controller.PopupMessageController;
import resources.Sizes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This model class got methods in it for processing database-related things. It's especially used for executing queries.
 * It interacts via DBConnection controller class with the view.
 *
 * @author Daniel, Valentin, Marius
 * @see DBConnection
 * @see DBHelpers
 */
public class DBWorker {
    DBHelpers dbHelpers = new DBHelpers();
    PopupMessageController popupMessageController = new PopupMessageController();
    ResourcesGetter resourcesGetter = new ResourcesGetter();

    /**
     * Method for connecting to database.
     *
     * @param connectionString (JDBC string)
     * @param username         (as String)
     * @param password         (as String)
     * @return Database connection as type Connection
     * @return Daniel
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection connectToDB(String connectionString, String username, String password) throws SQLException, ClassNotFoundException {
        if (connectionString.contains("postgresql")) {
            //necessary for getting driver when running .jar
            Class.forName("org.postgresql.Driver");
        }
        return DriverManager.getConnection(connectionString, username, password);
    }

    /**
     * Method for getting all tables in database.
     *
     * @param connection
     * @return all table names from database in a string list
     * @throws SQLException
     * @author Daniel
     */
    public List<String> getAllTablesFromDB(Connection connection) throws SQLException {
        List<String> allTables = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet;
        if (connection.getMetaData().getURL().contains("postgresql")) {
            //@author Valentin
            resultSet = databaseStatement.executeQuery("SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname='public';");
        } else {
            resultSet = databaseStatement.executeQuery("SHOW tables");
        }
        while (resultSet.next()) {
            allTables.add(resultSet.getString(1));
        }
        System.out.println("Got all tables from database");
        return allTables;
    }

    /**
     * Method for getting all columns from a particular table.
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @return all columns from a table as a list
     * @throws SQLException
     */
    public List<String> getColumnsFromTable(Connection connection, String table) throws SQLException {
        List<String> allColumns = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT * FROM " + table
        );
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            allColumns.add(resultSetMetaData.getColumnName(i));
        }
        System.out.println("Got all columns from " + table);
        return allColumns;
    }

    /**
     * Method for getting all rows from a particular table.
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @return two-dimensional string array (first dimension is column, second rows)
     * @throws SQLException
     * @author Daniel
     */
    public String[][] getRowsFromTable(Connection connection, String table) throws SQLException {
        int countRows = 0;
        List<String> columns = getColumnsFromTable(connection, table);
        List<String> allRows = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery(
                "SELECT * FROM " + table
        );
        while (resultSet.next()) {
            for (int i = 1; i <= columns.size(); i++) {
                allRows.add(resultSet.getString(i));
            }
            countRows++;
        }
        String[][] rowsFromTable = new String[countRows + 1][columns.size() + 1];
        for (int y = 0; y < countRows; y++) {
            for (int x = 0; x < columns.size(); x++) {
                rowsFromTable[y][x] = allRows.get(x + (y * columns.size()));
            }
        }
        return rowsFromTable;
    }

    /**
     * Method for deleting particular rows.
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @param columns    (columns where rows aren't empty as String List)
     * @param rows       (rows which aren't empty as String List)
     * @throws SQLException
     * @author Daniel
     * @important this method isn't working with postgresql. See comments for more information.
     */
    public void deleteRows(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        String deleteQuery = "DELETE FROM " + table + " WHERE ";
        List<String> columnDatatype = dbHelpers.getColumnMetadata(connection, table, 0);
        //building whole delete query with properties
        for (int i = 0; i < columns.size(); i++) {
            if (i == columns.size() - 1) {
                deleteQuery += columns.get(i) + " = ? ;";
            } else {
                deleteQuery += columns.get(i) + " = ?" + " AND ";
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        //adding properties to the query
        for (int x = 1; x <= rows.size(); x++) {
            //if statement needed for postgresql because strict types are required (so you have to set int by "setInt" and so on) --> all datatypes are needed for getting this to work.
            if (columnDatatype.get(x - 1).contains("int") || columnDatatype.get(x - 1).contains("serial")) {
                preparedStatement.setInt(x, Integer.parseInt(rows.get(x - 1)));
            } else {
                preparedStatement.setString(x, rows.get(x - 1));
            }
        }
        preparedStatement.executeUpdate();
        popupMessageController.showSuccessMessage("Successfully deleted row");
    }

    /**
     * Method for creating a single row.
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @throws SQLException
     * @author Daniel
     */
    public void addRow(Connection connection, String table) throws SQLException {
        String addQuery = "INSERT INTO " + table + "(";
        List<String> columnDatatype = dbHelpers.getColumnMetadata(connection, table, 0);
        List<String> columns = getColumnsFromTable(connection, table);
        String[] input = dbHelpers.getInputFromInputOptionPane(connection, table, columns, null, "Add row", false);
        if (input != null) {
            for (int i = 0; i < columns.size(); i++) {
                if (i == columns.size() - 1) {
                    addQuery += columns.get(i) + ") VALUES(";
                } else {
                    addQuery += columns.get(i) + ", ";
                }
            }
            for (int x = 0; x < columns.size(); x++) {
                //last property
                if (x == columns.size() - 1) {
                    //type casting is required for postgresql
                    if (connection.getMetaData().getURL().contains("postgresql")) {
                        addQuery += "?::" + columnDatatype.get(x) + ");";
                    } else {
                        addQuery += "?" + ");";
                    }

                } else {
                    //type casting is required for postgresql
                    if (connection.getMetaData().getURL().contains("postgresql")) {
                        addQuery += "?::" + columnDatatype.get(x) + ", ";
                    } else {
                        addQuery += "?" + ", ";
                    }
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            for (int y = 1; y <= input.length; y++) {
                if (input[y - 1].equals("") && connection.getMetaData().getURL().contains("postgresql")) {
                    preparedStatement.setNull(y, java.sql.Types.NULL);
                } else {
                    preparedStatement.setObject(y, input[y - 1]);
                }
            }
            preparedStatement.executeUpdate();
            popupMessageController.showSuccessMessage("Successfully added row");
        }
    }

    /**
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @param columns    (columns as string list)
     * @param rows       (rows as string list)
     * @throws SQLException
     * @author Daniel
     */
    public void editRow(Connection connection, String table, List<String> columns, List<String> rows) throws SQLException {
        String editQuery = "UPDATE " + table + " SET ";
        String[] input = dbHelpers.getInputFromInputOptionPane(connection, table, columns, rows, "Edit row", true);
        for (int i = 0; i < columns.size(); i++) {
            if (i == columns.size() - 1) {
                editQuery += columns.get(i) + "=" + "?" + " WHERE ";
            } else {
                editQuery += columns.get(i) + "=" + "?" + ",";
            }
        }
        for (int x = 0; x < columns.size(); x++) {
            //check if there is a row wich is empty, if so don't add it to the query
            if (!(rows.get(x).isEmpty())) {
                if (x == columns.size() - 1) {
                    editQuery += columns.get(x) + "=" + "?" + ";";
                } else {
                    editQuery += columns.get(x) + "=" + "?" + " AND ";
                }
            }
        }
        if (editQuery.endsWith(" AND ")) {
            editQuery = editQuery.replaceAll("AND $", " ");
        }
        PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
        for (int y = 1; y <= input.length; y++) {
            preparedStatement.setString(y, input[y - 1]);
        }
        //remove empty rows from row
        for (int z = rows.size() - 1; z >= 0; z--) {
            if (rows.get(z).equals("")) {
                rows.remove(z);
            }
        }
        int counter = 0;
        for (int xy = input.length + 1; xy <= input.length + rows.size(); xy++) {
            preparedStatement.setString(xy, rows.get(counter));
            counter++;
        }
        preparedStatement.executeUpdate();
        popupMessageController.showSuccessMessage("Edited row");
    }

    /**
     * Method for importing csv. (Note: Not working for postgresql yet)
     *
     * @param connection (as type Connection)
     * @param table      (table name as String)
     * @throws SQLException
     * @throws IOException
     * @author Daniel
     */
    public void addImportedRows(Connection connection, String table) throws SQLException, IOException {
        String addQueryMeta = "INSERT INTO " + table + "(";
        String addQueryValues = "";
        List<List<String>> data = resourcesGetter.getColumnsAndRowsFromCSV();
        List<String> columnDatatype = dbHelpers.getColumnMetadata(connection, table, 0);
        //data.get(0) --> columns
        for (int i = 0; i < data.get(0).size(); i++) {
            if (i == data.get(0).size() - 1) {
                addQueryMeta += data.get(0).get(i) + ") VALUES(";
            } else {
                addQueryMeta += data.get(0).get(i) + ", ";
            }
        }
        //x --> rows; y --> columns in rows
        for (int x = 1; x < data.size(); x++) {
            for (int y = 0; y < data.get(x).size(); y++) {
                if (y == data.get(x).size() - 1) {
                    if (connection.getMetaData().getURL().contains("postgresql")) {
                        addQueryValues += "?::" + columnDatatype.get(y) + ");";
                    } else {
                        addQueryValues += "?" + ");";
                    }
                } else {
                    if (connection.getMetaData().getURL().contains("postgresql")) {
                        addQueryValues += "?::" + columnDatatype.get(x) + ", ";
                    } else {
                        addQueryValues += "?" + ", ";
                    }
                }
            }
            addQueryMeta = addQueryMeta.replace("\uFEFF", "");
            addQueryValues = addQueryValues.replace("\uFEFF", "");
            PreparedStatement preparedStatement = connection.prepareStatement(addQueryMeta + addQueryValues);
            for (int n = 1; n <= data.get(x).size(); n++) {
                preparedStatement.setString(n, data.get(x).get(n - 1));
            }
            preparedStatement.executeUpdate();
            addQueryValues = "";
        }
    }

    /**
     * Method is used for clearing table.
     *
     * @param connection (connection as type Connection)
     * @param table      (as table name)
     * @throws SQLException
     * @author Marius
     */
    public void clearTable(Connection connection, String table) throws SQLException {
        String deleteQuery = "DELETE FROM " + table;
        PreparedStatement st = connection.prepareStatement(deleteQuery);
        st.executeUpdate();
        popupMessageController.showSuccessMessage("Successfully cleared table");
    }

}
