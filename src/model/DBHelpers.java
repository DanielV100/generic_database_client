package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
     * @param table        (table name)
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
     * Method for getting all keys from database (primary and foreign keys).
     * @param connection (connection as type Connection)
     * @return all keys as list (index 0: primary keys, index 1: foreign keys)
     * @throws SQLException
     * @author Daniel
     */
    public List<String> getAllKeys(Connection connection) throws SQLException {
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
        System.out.println("Got all keys from database");
        return allKeys;
    }

    /**
     *
     * @param connection (connection as type Connection)
     * @param table (table name as string)
     * @param columns (as string list)
     * @param rows (only needed for editing)
     * @param inputPaneName
     * @param isEdit (false when adding a row, true, when editing row)
     * @return String[] with user input for adding and editing
     * @throws SQLException
     * @author Daniel
     */
    protected String[] getInputFromInputOptionPane(Connection connection, String table, List<String> columns, List<String> rows, String inputPaneName, boolean isEdit) throws SQLException {
        String input[] = new String[columns.size()];
        JLabel[] labelsForInput = new JLabel[columns.size()];
        JTextField[] inputFields = new JTextField[columns.size()];
        JOptionPane inputOptionPane = new JOptionPane();
        int option = inputOptionPane.showConfirmDialog(null, createContainerForJOptionPane(connection, table, columns, labelsForInput, inputFields, getColumnMetadata(connection, table, 0), getColumnMetadata(connection, table, 1), isEdit, rows), inputPaneName, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            for (int x = 0; x < columns.size(); x++) {
                input[x] = inputFields[x].getText();
            }
        } else {
            input = null;
        }
        return input;
    }

    /**
     * Method is creating a JPanel which is later used in a JOptionPane to get user input for adding/editing a row.
     * @param connection (connection as type Connection)
     * @param table (table name as string)
     * @param columns (as string list)
     * @param labelForColumns (as JLabel[])
     * @param inputFields (as JTextField[])
     * @param columnsType (as string list)
     * @param columnsTypeLength (as string list)
     * @param isEdit (false when adding a row, true, when editing row)
     * @param rows (only needed for editing)
     * @return JPanel
     * @throws SQLException
     * @author Daniel
     */
    private JPanel createContainerForJOptionPane(Connection connection, String table, List<String> columns, JLabel[] labelForColumns, JTextField[] inputFields, List<String> columnsType, List<String> columnsTypeLength, Boolean isEdit, List<String> rows) throws SQLException {
        List<String> allKeys = getAllKeys(connection);
        String numbers = "123456789";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = ";,+*#'_^´`;";
        JPanel container = new JPanel(new GridLayout(columns.size(), 2));
        for (int i = 0; i < labelForColumns.length; i++) {
            //on the left: lables
            labelForColumns[i] = new JLabel(columns.get(i) + " (" + columnsType.get(i) + ", " + columnsTypeLength.get(i) + ")");
            container.add(labelForColumns[i]);
            //on the right-handed side: textfields

            if (isEdit) {
                inputFields[i] = new JTextField(rows.get(i));

            } else {
                inputFields[i] = new JTextField();
            }
            if (allKeys.get(0).contains(table.toLowerCase() + "." + columns.get(i))) {
                inputFields[i].setBackground(Color.YELLOW);
                inputFields[i].setToolTipText("This field is a primary key - so it's possible to reference it from another table.");
            } else if (allKeys.get(1).contains(table.toLowerCase() + "." + columns.get(i))) {
                inputFields[i].setBackground(Color.YELLOW);
                inputFields[i].setToolTipText("Mind that the data from this row comes from another table (foreign key).");
            }
            int index = i;
            inputFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (columnsType.get(index) == "DATE" && (alphabet.contains(String.valueOf(e.getKeyChar())) || alphabet.toLowerCase().contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())))) {
                        JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                        inputFields[index].setText("");
                    }
                    if (Integer.parseInt(columnsTypeLength.get(index)) < inputFields[index].getText().length()) {
                        JOptionPane.showMessageDialog(null, "Input to long. Maximum size: " + columnsTypeLength.get(index));
                        inputFields[index].setText("");
                    }
                    if (columnsType.get(index) == "INT" && (alphabet.contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())) || alphabet.toLowerCase().contains(String.valueOf(e.getKeyChar())))) {
                        JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                        inputFields[index].setText("");
                    }
                    if (columnsType.get(index) == "TEXT" && (numbers.contains(String.valueOf(e.getKeyChar())) || specialChars.contains(String.valueOf(e.getKeyChar())))) {
                        JOptionPane.showMessageDialog(null, "This value has to be a " + columnsType.get(index) + " and you typed " + e.getKeyChar());
                        inputFields[index].setText("");
                    }
                }
            });
            container.add(inputFields[i]);
        }
        return container;
    }
}
