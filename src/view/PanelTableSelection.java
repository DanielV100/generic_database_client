package view;
import controller.DBConnection;
import controller.PopupMessageController;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelTableSelection {
    PopupMessageController popupMessageController = new PopupMessageController();
    UIHelpers uiHelpers = new UIHelpers();
    DBConnection dbConnection = new DBConnection();
    PanelTableView panelTableView = new PanelTableView();
    Sizes sizes = new Sizes();
    JPanel panelTableSelection = new JPanel();
    JList jlistTableSelection = new JList();

    public JPanel PanelTableSelection(Connection connection, String selectedDB) throws SQLException {
        sizes.init();
        //creating list which shows the tables
        jlistTableSelection = uiHelpers.createJList(jlistTableSelection, dbConnection.getAllTablesFromDB(connection), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistX(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlisty(), sizes.getJlist_panelTableSelection_jlistTableSelection_jlistWidth(), sizes.getScreenHeight());
        jlistTableSelection.setBackground(new Color(211, 211, 211));
        jlistTableSelection.setFont(new Font("Arial",Font.PLAIN, 14));
        jlistTableSelection.setFixedCellHeight(30);
        jlistTableSelection.setToolTipText("Tables");
        jlistTableSelection.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    try {
                        panelTableSelection.remove(1);
                        panelTableSelection.repaint();
                    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {

                    }
                    JScrollPane scrollPaneWithTable = panelTableView.PanelTableView(connection, jlistTableSelection.getSelectedIndex(), selectedDB);
                    panelTableSelection.add(scrollPaneWithTable);
                } catch (SQLException ex) {
                    popupMessageController.showErrorMessage(ex);
                }
            }
        });
        //creating the panel
        panelTableSelection = uiHelpers.createJPanel(panelTableSelection, sizes.getPanel_panelTableSelection_panelX(), sizes.getPanel_panelTableSelection_panelY(), sizes.getScreenWidth(), sizes.getScreenHeight(), null, sizes.getSize());
        panelTableSelection.add(jlistTableSelection);
        panelTableSelection.setPreferredSize(sizes.getSize());
        return panelTableSelection;
    }
}
