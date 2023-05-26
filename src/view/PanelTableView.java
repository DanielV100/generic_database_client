package view;

import controller.Controller;
import controller.DBConnection;
import controller.PopupMessageController;
import resources.Colors;
import resources.Sizes;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PanelTableView {
    UIHelpers uiHelpers = new UIHelpers();
    Colors colors = new Colors();
    Controller controller = new Controller();
    DBConnection dbConnection = new DBConnection();
    Sizes sizes = new Sizes();
    JTable tableFromDB = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    JPopupMenu popupMenu;
    JMenuItem menuItemEdit;
    JMenuItem menuItemDelete;
    JMenuItem menuItemAdd;
    JMenuItem menuItemImport;
    JMenuItem menuItemClearTable;
    JMenuItem menuItemExportToCSV;
    JMenuItem menuItemShowDBTypes;
    PopupMessageController popupMessageController = new PopupMessageController();

    /**
     * This is the class which contains and builds the table view (seeing rows and columns).
     *
     * @param connection (as type Connection)
     * @param index      (table index as int)
     * @param selectedDB (selected data-base as string)
     * @return JScrollPane with table view on it
     * @throws SQLException
     * @author Marius, Valentin, Luca
     */
    public JScrollPane PanelTableView(Connection connection, int index, String selectedDB) throws SQLException {
        sizes.init();
        DefaultTableModel model = new DefaultTableModel(dbConnection.getRowsFromTable(connection, index), dbConnection.getColumnsFromTable(connection, index)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //make the first column non-editable
            }
        };
        tableFromDB = uiHelpers.createJTable(tableFromDB, model, ListSelectionModel.SINGLE_SELECTION, colors.getTableHeaderBackground(), new Font("Arial", Font.BOLD, 14), 50, colors.getTableGridColor(), colors.getSelectionTableColor());
        tableFromDB.setDefaultRenderer(Object.class, new AlternateRowColorRenderer());
        tableFromDB.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) || (System.getProperty("os.name").contains("Mac OS X") && e.isControlDown())) {
                    popupMenu = new JPopupMenu();
                    menuItemShowDBTypes = new JMenuItem("Show column datatypes");
                    menuItemAdd = new JMenuItem("Add row");
                    menuItemEdit = new JMenuItem("Edit row");
                    menuItemImport = new JMenuItem("Import from .csv");
                    menuItemExportToCSV = new JMenuItem("Export to .csv");
                    menuItemDelete = new JMenuItem("Delete row");
                    menuItemClearTable = new JMenuItem("Clear table");
                    menuItemClearTable.setForeground(Color.RED);
                    popupMenu.add(menuItemShowDBTypes);
                    popupMenu.add(menuItemEdit);
                    popupMenu.addSeparator();
                    popupMenu.add(menuItemAdd);
                    popupMenu.add(menuItemImport);
                    popupMenu.add(menuItemExportToCSV);
                    popupMenu.addSeparator();
                    popupMenu.add(menuItemDelete);
                    popupMenu.add(menuItemClearTable);
                    if (selectedDB.equals("postgresql")) {
                        menuItemDelete.setEnabled(false);
                        menuItemImport.setEnabled(false);
                        menuItemEdit.setEnabled(false);
                    }
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
                            for (int i = 0; i < target.getColumnCount(); i++) {
                                try {
                                    if (target.getValueAt(rowNumber, i).toString() != null) {
                                        columns.add(target.getColumnName(i));
                                        rows.add(target.getValueAt(rowNumber, i).toString());
                                    }
                                } catch (NullPointerException nullPointerException) {
                                    columns.add(target.getColumnName(i));
                                    rows.add("");
                                }
                            }
                            try {
                                dbConnection.editRow(connection, dbConnection.getAllTablesFromDB(connection)[index], columns, rows);
                            } catch (SQLException ex) {
                                popupMessageController.showErrorMessage(ex);
                            } catch (NullPointerException nullPointerException) {
                                System.out.println(nullPointerException);
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
                            for (int i = 0; i < target.getColumnCount(); i++) {
                                try {
                                    if (target.getValueAt(rowNumber, i).toString() != null) {
                                        columns.add(target.getColumnName(i));
                                        rows.add(target.getValueAt(rowNumber, i).toString());
                                    }
                                } catch (NullPointerException nullPointerException) {

                                }
                            }
                            try {
                                dbConnection.deleteRow(connection, dbConnection.getAllTablesFromDB(connection)[index], columns, rows);
                            } catch (SQLException ex) {
                                popupMessageController.showErrorMessage(ex);
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
                                popupMessageController.showErrorMessage(ex);
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
                                for (int i = 0; i < columns.length; i++) {
                                    csvColumnTemplate += columns[i] + "; ";
                                }
                                int option = JOptionPane.showConfirmDialog(null, "Has your CSV the correct syntax? In first line:\n" + csvColumnTemplate + "\nIn lines below the data for every cell with ';' separated?");
                                if (option == JOptionPane.OK_OPTION) {
                                    dbConnection.addImportedRows(connection, dbConnection.getAllTablesFromDB(connection)[index]);
                                    popupMessageController.showSuccessMessage("Successfully imported CSV");
                                }
                            } catch (SQLException ex) {
                                popupMessageController.showErrorMessage(ex);
                            } catch (IOException ex) {
                                popupMessageController.showErrorMessage(ex);
                            }
                        }
                    });
                    //----Importing----

                    //----clear table----
                    menuItemClearTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            int option = JOptionPane.showConfirmDialog(null, "Are you sure about deleting this all data from this table? Data can't be restored after deleting!");
                            if (option == JOptionPane.OK_OPTION) {
                                try {
                                    dbConnection.clearTable(connection, dbConnection.getAllTablesFromDB(connection)[index]);
                                } catch (SQLException ex) {
                                    popupMessageController.showErrorMessage(ex);
                                }
                            }
                        }
                    });
                    //---- Export ----
                    menuItemExportToCSV.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent mouseEvent) {
                            try {
                                JTable table = (JTable) e.getSource();
                                controller.exportToCSV(table);
                                popupMessageController.showSuccessMessage("Successfully exported table");
                            } catch (IOException ex) {
                                popupMessageController.showErrorMessage(ex);
                            }
                        }
                    });
                    //---- Export ----

                    //---- Datatypes ----
                    menuItemShowDBTypes.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            try {
                                String message = "";
                                String[] columns = dbConnection.getColumnsFromTable(connection, index);
                                List<String> columnsDatatype = dbConnection.getColumnMetadata(connection, dbConnection.getAllTablesFromDB(connection)[index], 0);
                                List<String> columnsDisplaySize = dbConnection.getColumnMetadata(connection, dbConnection.getAllTablesFromDB(connection)[index], 1);
                                for (int i = 0; i < columns.length; i++) {
                                    message += columns[i] + ":" + columnsDatatype.get(i) + "(" + columnsDisplaySize.get(i) + ")\n";
                                }
                                JOptionPane.showMessageDialog(null, message);
                            } catch (SQLException ex) {
                                popupMessageController.showErrorMessage(ex);
                            }
                        }
                    });
                }
            }
        });
        scrollPane = new JScrollPane(tableFromDB, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(sizes.getPanel_panelTableView_panelX(), sizes.getPanel_panelTableView_panelY(), sizes.getScreenWidth() - sizes.getJlist_panelTableSelection_jlistTableSelection_jlistWidth(), sizes.getScreenHeight() - 50);
        scrollPane.setViewportView(tableFromDB);
        return scrollPane;
    }
}
