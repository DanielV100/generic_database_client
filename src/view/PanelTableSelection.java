package view;
import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelTableSelection {
    UIHelpers uiHelpers = new UIHelpers();
    DBConnection dbConnection = new DBConnection();
    PanelTableView panelTableView = new PanelTableView();
    Sizes sizes = new Sizes();
    JPanel panelTableSelection = new JPanel();
    JList jlistTableSelection = new JList();

    public JPanel PanelTableSelection(Connection connection) throws SQLException {
        //creating list which shows the tables
        jlistTableSelection = uiHelpers.createJList(jlistTableSelection, dbConnection.getAllTablesFromDB(connection), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistX(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlisty(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistWidth(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistHeight());

        jlistTableSelection.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    try {
                        panelTableSelection.remove(1);
                        panelTableSelection.repaint();
                    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {

                    }
                    JScrollPane scrollPaneWithTable = panelTableView.PanelTableView(connection, jlistTableSelection.getSelectedIndex());
                    panelTableSelection.add(scrollPaneWithTable);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //creating the panel
        panelTableSelection = uiHelpers.createJPanel(panelTableSelection, sizes.getPanel_panelTableSelection_panelX(), sizes.getPanel_panelTableSelection_panelY(), sizes.getPanel_panelTableSelection_panelWidth(), sizes.getPanel_panelTableSelection_panelHeight(), null);
        panelTableSelection.add(jlistTableSelection);
        return panelTableSelection;
    }
}
