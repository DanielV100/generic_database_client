package view;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
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
    JFrame test2 = new JFrame();

    public JPanel PanelTableSelection(Connection connection) throws SQLException {
        test2.setLayout(null);
        test2.setBounds(0, 0, 1200, 800);
        test2.setVisible(false);
        //creating list which shows the tables
        jlistTableSelection = uiHelpers.createJList(jlistTableSelection, dbConnection.getAllTablesFromDB(connection), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistX(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlisty(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistWidth(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistHeight());
        jlistTableSelection.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println(jlistTableSelection.getSelectedIndex());
                    test2.add(panelTableView.PanelTableView(connection, jlistTableSelection.getSelectedIndex()));
                    test2.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
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
