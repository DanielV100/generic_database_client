package view;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    JPopupMenu popupMenu;
    JMenuItem menuItemEdit;
    JMenuItem menuItemDelete;

    public JScrollPane PanelTableView(Connection connection, int index) throws SQLException {


        String[] columns = dbConnection.getColumnsFromTable(connection, index);
        tableFromDB = uiHelpers.createJTable(tableFromDB, columns, dbConnection.getRowsFromTable(connection, columns, index), sizes.getTable_panelTableView_tableFromDB_tableX(), sizes.getTable_panelTableView_tableFromDB_tableY(), sizes.getTable_panelTableView_tableFromDB_tableWidth(), sizes.getTable_panelTableView_tableFromDB_tableHeight());
        tableFromDB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) || (System.getProperty("os.name").contains("Mac OS X") && e.isControlDown())){
                    popupMenu = new JPopupMenu();
                    menuItemEdit = new JMenuItem("Edit row");
                    menuItemDelete = new JMenuItem("Delete row");
                    //creating popupmenu with (1) edit (2) delete
                    popupMenu.add(menuItemEdit);

                    popupMenu.add(menuItemDelete);
                    // Display the popup menu at the location of the mouse click
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    menuItemEdit.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent mouseEventEdit) {
                            List<String> columns = new ArrayList<>();
                            List<String> rows = new ArrayList<>();
                            //getting actual table
                            JTable target = (JTable) e.getSource();
                            //getting selected row
                            int rowNumber = target.getSelectedRow();
                            for(int i = 0; i < target.getColumnCount(); i++) {
                                try {
                                    if(target.getValueAt(rowNumber, i).toString() != null) {
                                        columns.add(target.getColumnName(i));
                                        rows.add(target.getValueAt(rowNumber, i).toString());
                                    }
                                } catch (NullPointerException nullPointerException) {

                                }
                            }
                            try {
                                dbConnection.editRow(connection,dbConnection.getAllTablesFromDB(connection)[index], columns, rows);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    menuItemDelete.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent mouseEventDelete) {
                            //deleting row
                            List<String> columns = new ArrayList<>();
                            List<String> rows = new ArrayList<>();
                            //getting actual table
                            JTable target = (JTable) e.getSource();
                            //getting selected row
                            int rowNumber = target.getSelectedRow();
                            for(int i = 0; i < target.getColumnCount(); i++) {
                                try {
                                    if(target.getValueAt(rowNumber, i).toString() != null) {
                                        columns.add(target.getColumnName(i));
                                        rows.add(target.getValueAt(rowNumber, i).toString());
                                    }
                                } catch (NullPointerException nullPointerException) {

                                }
                            }
                            try {
                                dbConnection.deleteRow(connection, dbConnection.getAllTablesFromDB(connection)[index], columns, rows);
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Something went wrong.");
                            }
                        }
                    });
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
