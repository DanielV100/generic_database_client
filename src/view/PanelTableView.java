package view;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
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
    JMenuItem menuItemAdd;
    JMenuItem menuItemImport;
    JMenuItem menuItemClearTable;

    public JScrollPane PanelTableView(Connection connection, int index) throws SQLException {
        sizes.init();

        String[] columns = dbConnection.getColumnsFromTable(connection, index);
        //Table height is extremly high --> show all data
        //tableFromDB = uiHelpers.createJTable(tableFromDB, columns, dbConnection.getRowsFromTable(connection, columns, index), sizes.getTable_panelTableView_tableFromDB_tableX(), sizes.getTable_panelTableView_tableFromDB_tableY(), sizes.getScreenWidth()-sizes.getJlist_panelTableSelection_jlistTableSelection_jlistWidth(), sizes.getScreenHeight() + 1000);
       //makes cells non editable
        DefaultTableModel model = new DefaultTableModel(dbConnection.getRowsFromTable(connection, columns, index), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //make the first column non-editable
            }
        };
        tableFromDB = new JTable(model);
        tableFromDB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFromDB.getTableHeader().setBackground(new Color(238, 220, 130));
        tableFromDB.getTableHeader().setFont(new Font("Arial",Font.BOLD, 14));
        tableFromDB.setRowHeight(30);
        tableFromDB.setGridColor(new Color(211, 211, 211));
        tableFromDB.setDefaultRenderer(Object.class, new AlternateRowColorRenderer());
        tableFromDB.setSelectionBackground(Color.BLUE);
        tableFromDB.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) || (System.getProperty("os.name").contains("Mac OS X") && e.isControlDown())){
                    popupMenu = new JPopupMenu();
                    menuItemAdd = new JMenuItem("Add row");
                    menuItemEdit = new JMenuItem("Edit row");
                    menuItemImport = new JMenuItem("Import from .csv");
                    menuItemDelete = new JMenuItem("Delete row");
                    menuItemClearTable = new JMenuItem("Clear table");
                    menuItemClearTable.setForeground(Color.RED);
                    //creating popupmenu with (1) edit (2) delete (3) add (4) import from .csv

                    popupMenu.add(menuItemEdit);
                    popupMenu.addSeparator();
                    popupMenu.add(menuItemAdd);
                    popupMenu.add(menuItemImport);
                    popupMenu.addSeparator();
                    popupMenu.add(menuItemDelete);
                    popupMenu.add(menuItemClearTable);
                    //Display the popup menu at the location of the mouse click
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    //----Editing----
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
                                    columns.add(target.getColumnName(i));
                                    rows.add("");
                                }
                            }
                            try {
                                dbConnection.editRow(connection,dbConnection.getAllTablesFromDB(connection)[index], columns, rows);
                            } catch (SQLException ex) {
                                if(ex.toString().contains("java.sql.SQLIntegrityConstraintViolationException")) {
                                    JOptionPane.showMessageDialog(null, "This row can't be changed because it's referenced in an other table.");
                                }
                            }
                        }
                    });
                    //----Editing----

                    //----Deleting----
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
                    //----Deleting----

                    //----Adding----
                    menuItemAdd.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            try {
                                dbConnection.addRow(connection, dbConnection.getAllTablesFromDB(connection)[index]);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    //----Adding----

                    //----Importing----
                    menuItemImport.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            try {
                                String[] columns = dbConnection.getColumnsFromTable(connection, index);
                                String csvColumnTemplate = "";
                                for(int i = 0; i < columns.length; i++) {
                                    csvColumnTemplate += columns[i] + "; ";
                                }
                                int option = JOptionPane.showConfirmDialog(null, "Has your CSV the correct syntax? In first line:\n" + csvColumnTemplate + "\nIn lines below the data for every cell with ';' separated?");
                                if (option == JOptionPane.OK_OPTION) {
                                    dbConnection.addImportedRows(connection, dbConnection.getAllTablesFromDB(connection)[index]);
                                    JOptionPane.showMessageDialog(null, "Imported CSV");
                                }

                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    //----Importing----

                    //----clear table----
                    menuItemClearTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            int option = JOptionPane.showConfirmDialog(null, "Are you sure about deleting this all data from this table? Data can't be restored after deleting!");
                            if(option == JOptionPane.OK_OPTION) {
                                try {
                                    dbConnection.clearTable(connection,  dbConnection.getAllTablesFromDB(connection)[index]);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    });

                }
            }
        });
        scrollPane = new JScrollPane(tableFromDB);
        scrollPane.setBounds(sizes.getPanel_panelTableView_panelX(),sizes.getPanel_panelTableView_panelY() , sizes.getScreenWidth()-sizes.getJlist_panelTableSelection_jlistTableSelection_jlistWidth(), sizes.getScreenHeight()-50);

        return scrollPane;
    }
}
