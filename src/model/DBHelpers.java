package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for doing everything database-related but no query execution.
 * @author Daniel
 */
public class DBHelpers {

    /**
     * @param connection   (connection as type Connection)
     * @param table        (as table name)
     * @param metadataType (as int (0 = datatype, 1 = column display size))
     * @return specific metadata as String List
     * @throws SQLException
     */
    public List<String> getColumnMetadata(Connection connection, String table, int metadataType) throws SQLException {
        List<String> columnMetadata = new ArrayList<>();
        Statement databaseStatement = connection.createStatement();
        ResultSet resultSet = databaseStatement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            if (metadataType == 0) {
                columnMetadata.add(resultSetMetaData.getColumnTypeName(i));
            } else if (metadataType == 1) {
                columnMetadata.add(String.valueOf(resultSetMetaData.getColumnDisplaySize(i)));
            }
        }
        return columnMetadata;
    }

    /**
     * Method for getting all keys from table (primary and foreign keys).
     * @param connection (connection as type Connection)
     * @return all keys as list (index 0: primary keys, index 1: foreign keys)
     * @throws SQLException
     * @author Daniel
     */
    public List<String> getAllKeys(Connection connection, String table) throws SQLException {
        List<String> allKeys = new ArrayList<>();
        String foreignKey = "";
        String primaryKeys = "";
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tableResultSet = databaseMetaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tableResultSet.next()) {
            String tableName = tableResultSet.getString("TABLE_NAME");
            ResultSet keyResultSet = databaseMetaData.getImportedKeys(null, null, tableName);
            while (keyResultSet.next()) {
                foreignKey += keyResultSet.getString("FKTABLE_NAME") + "." + keyResultSet.getString("FKCOLUMN_NAME") + " ";
                primaryKeys += keyResultSet.getString("PKTABLE_NAME") + "." + keyResultSet.getString("PKCOLUMN_NAME") + " ";
            }
        }
        allKeys.add(primaryKeys);
        allKeys.add(foreignKey);
        return allKeys;
    }
}
