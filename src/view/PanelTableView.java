package view;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelTableView {
    UIHelpers uiHelpers = new UIHelpers();
    DBConnection dbConnection = new DBConnection();
    Sizes sizes = new Sizes();
    JPanel panelTableView = new JPanel();
    JTable tableFromDB = new JTable();
    JScrollPane scrollPane = new JScrollPane();

    public JScrollPane PanelTableView(Connection connection, int index) throws SQLException {
        String[] columns = dbConnection.getColumnsFromTable(connection, index);
        tableFromDB = uiHelpers.createJTable(tableFromDB, columns, dbConnection.getRowsFromTable(connection, columns, index), sizes.getTable_panelTableView_tableFromDB_tableX(), sizes.getTable_panelTableView_tableFromDB_tableY(), sizes.getTable_panelTableView_tableFromDB_tableWidth(), sizes.getTable_panelTableView_tableFromDB_tableHeight());
        scrollPane = new JScrollPane(tableFromDB);
        scrollPane.setBounds( sizes.getPanel_panelTableView_panelX(),sizes.getPanel_panelTableView_panelY() , sizes.getPanel_panelTableView_panelWidth(), sizes.getPanel_panelTableView_panelHeight());
        return scrollPane;
    }
}
