package view;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PanelTableView {
    UIHelpers uiHelpers = new UIHelpers();
    DBConnection dbConnection = new DBConnection();
    Sizes sizes = new Sizes();
    JPanel panelTableView = new JPanel();
    JTable tableFromDB = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem menuItemEdit = new JMenuItem("Edit row");
    JMenuItem menuItemDelete = new JMenuItem("Delete row");
    public JScrollPane PanelTableView(Connection connection, int index) throws SQLException {
        //creating popupmenu with (1) edit (2) delete
        popupMenu.add(menuItemEdit);
        popupMenu.add(menuItemDelete);

        String[] columns = dbConnection.getColumnsFromTable(connection, index);
        tableFromDB = uiHelpers.createJTable(tableFromDB, columns, dbConnection.getRowsFromTable(connection, columns, index), sizes.getTable_panelTableView_tableFromDB_tableX(), sizes.getTable_panelTableView_tableFromDB_tableY(), sizes.getTable_panelTableView_tableFromDB_tableWidth(), sizes.getTable_panelTableView_tableFromDB_tableHeight());
        tableFromDB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) || (System.getProperty("os.name").contains("Mac OS X") && e.isControlDown())){
                    // Display the popup menu at the location of the mouse click
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
                //deleting row
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    List<String> columns = new ArrayList<>();
                    List<String> rows = new ArrayList<>();
                    JTable target = (JTable) e.getSource();
                    int rowNumber = target.getSelectedRow();
                    for(int i = 0; i < target.getColumnCount(); i++) {
                        try {
                            if(target.getValueAt(rowNumber, i).toString() != null) {
                                columns.add(target.getColumnName(i));
                                rows.add(target.getValueAt(rowNumber, i).toString());
                            }
                        } catch (NullPointerException nullPointerException) {

                        }


                        System.out.println(target.getValueAt(rowNumber, i));
                    }
                    try {
                        dbConnection.deleteRow(connection, dbConnection.getAllTablesFromDB(connection)[index], columns, rows);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Something went wrong.");
                    }
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
        scrollPane = new JScrollPane(tableFromDB);
        scrollPane.setBounds( sizes.getPanel_panelTableView_panelX(),sizes.getPanel_panelTableView_panelY() , sizes.getPanel_panelTableView_panelWidth(), sizes.getPanel_panelTableView_panelHeight());
        scrollPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        dbConnection.addRow(connection, dbConnection.getAllTablesFromDB(connection)[index]);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
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
        return scrollPane;
    }
}
