package view;

import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
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

    //Popupmenu
    final JPopupMenu popupmenu = new JPopupMenu("Edit");
    JMenuItem menuItemDelete = new JMenuItem("Delete");

    public JScrollPane PanelTableView(Connection connection, int index) throws SQLException {
        String[] columns = dbConnection.getColumnsFromTable(connection, index);
        tableFromDB = uiHelpers.createJTable(tableFromDB, columns, dbConnection.getRowsFromTable(connection, columns, index), sizes.getTable_panelTableView_tableFromDB_tableX(), sizes.getTable_panelTableView_tableFromDB_tableY(), sizes.getTable_panelTableView_tableFromDB_tableWidth(), sizes.getTable_panelTableView_tableFromDB_tableHeight());
        tableFromDB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getButton());
                //on double click popupmenu is shown
                /*if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    menuItemDelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    popupmenu.add(menuItemDelete);
                    popupmenu.show(scrollPane, e.getX(), e.getY());
                }*/
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    List<String> columns = new ArrayList<>();
                    List<String> rows = new ArrayList<>();
                    JTable target = (JTable) e.getSource();
                    int rowNumber = target.getSelectedRow();
                    for(int i = 0; i < target.getColumnCount(); i++) {
                        System.out.println(target.getColumnName(i));
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
        return scrollPane;
    }
}
