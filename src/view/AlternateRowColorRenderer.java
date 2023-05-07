package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class AlternateRowColorRenderer extends DefaultTableCellRenderer {
    private static final Color EVEN_ROW_COLOR = new Color(211, 211, 211);
    private static final Color ODD_ROW_COLOR = Color.WHITE;

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


        return c;
    }
}