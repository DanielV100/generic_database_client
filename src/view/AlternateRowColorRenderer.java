package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Custom cell renderer for tables.
 * @author Daniel
 */
public class AlternateRowColorRenderer extends DefaultTableCellRenderer {
    private static final Color EVEN_ROW_COLOR = new Color(211, 211, 211);
    private static final Color ODD_ROW_COLOR = Color.WHITE;

    /**
     * Method for creating custom Component which can be used for JTable.
     * @param table  the <code>JTable</code>
     * @param value  the value to assign to the cell at
     *                  <code>[row, column]</code>
     * @param isSelected true if cell is selected
     * @param hasFocus true if cell has focus
     * @param row  the row of the cell to render
     * @param column the column of the cell to render
     * @return Component
     * @author Daniel
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 0) {
            c.setBackground(EVEN_ROW_COLOR);
        } else {
            c.setBackground(ODD_ROW_COLOR);
        }
        if (isSelected) {
            c.setForeground(Color.WHITE);
            c.setBackground(Color.BLUE);
        } else {
            c.setForeground(Color.BLACK);
        }
        //get last row and set it yellow
        if (table.getRowCount()-1 == row) {
            c.setBackground(new Color(238, 220, 130));
        }
        setToolTipText("Right click to see what you can do");
        return c;
    }
}