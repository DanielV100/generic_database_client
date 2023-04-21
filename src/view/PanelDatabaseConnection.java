package view;

import controller.Controller;
import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelDatabaseConnection {
    DBConnection dbConnection = new DBConnection();
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    UIHelpers uiHelpers = new UIHelpers();

    private JPanel panelDatabaseConnection = new JPanel();
    private JTextField textFieldHostname = new JTextField();
    private JTextField textFieldPort = new JTextField();
    private JTextField textFieldDatabaseName = new JTextField();
    private JTextField textFieldUsername = new JTextField();
    private JTextField textFieldPassword = new JTextField();
    private JButton buttonConnect = new JButton();
    JFrame test = new JFrame();
    PanelTableSelection panelTableSelection = new PanelTableSelection();
    public JPanel PanelDatabaseConnection() throws IOException {
        test.setLayout(null);
        test.setBounds(0, 0, 1200, 800);
        test.setVisible(false);

        //creating text fields for (1) hostname (2) port (3) Database name (4) username (5) password
        textFieldHostname = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight());
        textFieldPort = uiHelpers.createJTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight());
        textFieldDatabaseName = uiHelpers.createJTextField(textFieldDatabaseName, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldDatabaseName"), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight());
        textFieldUsername = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldUsername"), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldHeight());
        textFieldPassword = uiHelpers.createJTextField(textFieldPassword, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPassword"), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldHeight());
        //creating button for building the connection
        buttonConnect = uiHelpers.createJButton(buttonConnect, sizes.getButton_panelDatabaseConnection_buttonConnect_buttonX(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonY(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonHeight(), controller.getAppPropertiesWithKey("button.panelDatabaseConnection.buttonConnect"), Color.GREEN);
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = dbConnection.initDBConnection(textFieldHostname.getText(), textFieldPort.getText(), textFieldDatabaseName.getText(), textFieldUsername.getText(), textFieldPassword.getText());
                    //Erfolgsmeldung
                    test.add(panelTableSelection.PanelTableSelection(conn));
                    test.setVisible(true);
                } catch (SQLException ex) {
                    //Fehlermeldung
                    throw new RuntimeException(ex);
                }
            }
        });
        //initializing panel with the text fields and the button which later will be on the main frame
        panelDatabaseConnection = UIHelpers.createJPanel(panelDatabaseConnection, sizes.getPanel_view_panelDatabaseConnection_panelX(), sizes.getPanel_view_panelDatabaseConnection_panelY(), sizes.getPanel_view_panelDatabaseConnection_panelWidth(), sizes.getPanel_view_panelDatabaseConnection_panelHeight(), null);
        panelDatabaseConnection.add(textFieldHostname);
        panelDatabaseConnection.add(textFieldPort);
        panelDatabaseConnection.add(textFieldDatabaseName);
        panelDatabaseConnection.add(textFieldUsername);
        panelDatabaseConnection.add(textFieldPassword);
        panelDatabaseConnection.add(buttonConnect);
        return panelDatabaseConnection;
    }

}
