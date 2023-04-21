package view;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelTableSelection {
    UIHelpers uiHelpers = new UIHelpers();
    DBConnection dbConnection = new DBConnection();
    Sizes sizes = new Sizes();
    JPanel panelTableSelection = new JPanel();
    JList jlistTableSelection = new JList();

    public JPanel PanelTableSelection(Connection connection) throws SQLException {
        //creating list which shows the tables
        jlistTableSelection = uiHelpers.createJList(jlistTableSelection, dbConnection.getAllTablesFromDB(connection), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistX(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlisty(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistWidth(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistHeight());
        //creating the panel
        panelTableSelection = uiHelpers.createJPanel(panelTableSelection, sizes.getPanel_panelTableSelection_panelX(), sizes.getPanel_panelTableSelection_panelY(), sizes.getPanel_panelTableSelection_panelWidth(), sizes.getPanel_panelTableSelection_panelHeight(), null);
        panelTableSelection.add(jlistTableSelection);
        return panelTableSelection;
    }
}
