package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UIHelpers {
    public UIHelpers() {
    }
    //Frames can be created by calling this method
    protected static JFrame createJFrame(JFrame frame, String frameTitle, int frameWidth, int frameHeight, LayoutManager frameLayout, boolean frameVisibility){
        frame = new JFrame(frameTitle);
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(frameLayout);
        frame.setVisible(frameVisibility);
        return frame;
    }

    //Buttons can be created by calling this method
    protected static JButton createJButton(JButton button, int buttonX, int buttonY, int buttonWidth, int buttonHeight, String buttonLabel, Color buttonColor){
        button = new JButton(buttonLabel);
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        button.setBackground(buttonColor);
        return button;
    }
    //Labels can be created by calling this method
    protected static JLabel createJLabel(JLabel label, String labelText, int labelX, int labelY, int labelWidth, int labelHeight){
        label = new JLabel(labelText);
        label.setBounds(labelX, labelY, labelWidth, labelHeight);
        return label;
    }
    //JPanels can be created by calling this method - Panels should strictly be used as a container of components!
    protected static JPanel createJPanel(JPanel panel, int panelX, int panelY, int panelWidth, int panelHeight, LayoutManager panelLayout){
        panel = new JPanel();
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);
        panel.setLayout(panelLayout);
        return panel;
    }
    //Text fields can be created by calling this method
    protected static JTextField createJTextField(JTextField textField, String textFieldText, int textFieldX, int textFieldY, int textFieldWidth, int textFieldHeight) {
        textField = new JTextField(textFieldText);
        textField.setBounds(textFieldX, textFieldY, textFieldWidth, textFieldHeight);
        return textField;
    }
    protected static JPasswordField createJPasswordField(JPasswordField PasswordField, String textFieldText, int textFieldX, int textFieldY, int textFieldWidth, int textFieldHeight) {
        PasswordField = new JPasswordField(textFieldText);
        PasswordField.setBounds(textFieldX, textFieldY, textFieldWidth, textFieldHeight);
        return PasswordField;
    }

    protected static JList createJList(JList jlist, String[] dataToDisplay, int listX, int listY, int listWidth, int listHeight) {
        jlist = new JList(dataToDisplay);
        jlist.setBounds(listX, listY, listWidth, listHeight);
        return jlist;
    }

    protected static JTable createJTable(JTable table, String[] columns, String[][] data, int tableX, int tableY, int tableWidth, int tableHeight) {
        table = new JTable(data, columns);
        table.setBounds(tableX, tableY, tableWidth, tableHeight);
        return table;
    }
}
