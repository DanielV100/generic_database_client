package view;

import javax.swing.*;
import java.awt.*;

/**
 * Every UI-Component created in the program should use these helper-class. It provides every needed UI-Component.
 * Through this, every component has the same default settings.
 * @author Daniel, Marius
 */
public class UIHelpers {
    public UIHelpers() {
    }

    /**
     * Method should be used for creating new JFrames.
     * @param frame
     * @param frameTitle
     * @param frameWidth
     * @param frameHeight
     * @param frameLayout
     * @param frameVisibility
     * @param defaultCloseOperation
     * @param extendedState
     * @return blank JFrame
     * @author Daniel
     */
    protected static JFrame createJFrame(JFrame frame, String frameTitle, int frameWidth, int frameHeight, LayoutManager frameLayout, boolean frameVisibility, int defaultCloseOperation, int extendedState){
        frame = new JFrame(frameTitle);
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(frameLayout);
        frame.setVisible(frameVisibility);
        frame.setDefaultCloseOperation(defaultCloseOperation);
        frame.setExtendedState(extendedState);
        return frame;
    }
    // Checkboxes can be created by calling this method
    protected static JCheckBox createJCheckBox(JCheckBox checkBox, int checkboxX, int checkboxY, int checkboxWidth, int checkboxHeight, String checkboxLabel) {
        checkBox = new JCheckBox(checkboxLabel);
        checkBox.setBounds(checkboxX, checkboxY, checkboxWidth, checkboxHeight);
        return checkBox;
    }

    // Radiobuttons can be created by calling this method
    protected static JRadioButton createJRadioButton(JRadioButton radioButton, int radioButtonX, int radioButtonY, int radioButtonWidth, int radioButtonHeight, String radioButtonLabel) {
        radioButton = new JRadioButton(radioButtonLabel);
        radioButton.setBounds(radioButtonX, radioButtonY, radioButtonWidth, radioButtonHeight);
        return radioButton;
    }

    //Buttons can be created by calling this method
    protected static JButton createJButton(JButton button, int buttonX, int buttonY, int buttonWidth, int buttonHeight, String buttonLabel){
        button = new JButton(buttonLabel);
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
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
