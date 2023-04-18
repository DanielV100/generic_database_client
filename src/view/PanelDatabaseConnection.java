package view;

import controller.Controller;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PanelDatabaseConnection {
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    private JPanel panelDatabaseConnection = new JPanel();
    private JTextField textFieldHostname = new JTextField();
    private JTextField textFieldPort = new JTextField();
    private JTextField textFieldDatabaseName = new JTextField();
    private JButton buttonConnect = new JButton();

    public JPanel PanelDatabaseConnection() throws IOException {
        //creating text fields for (1) hostname (2) port (3) Database name
        textFieldHostname = UIHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight());
        textFieldPort = UIHelpers.createJTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight());
        textFieldDatabaseName = UIHelpers.createJTextField(textFieldDatabaseName, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldDatabaseName"), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight());
        //creating button for building the connection
        buttonConnect = UIHelpers.createJButton(buttonConnect, sizes.getButton_panelDatabaseConnection_buttonConnect_buttonX(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonY(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonHeight(), controller.getAppPropertiesWithKey("button.panelDatabaseConnection.buttonConnect"), Color.GREEN);
        //initializing panel with the text fields and the button which later will be on the main frame
        panelDatabaseConnection = UIHelpers.createJPanel(panelDatabaseConnection, sizes.getPanel_view_panelDatabaseConnection_panelX(), sizes.getPanel_view_panelDatabaseConnection_panelY(), sizes.getPanel_view_panelDatabaseConnection_panelWidth(), sizes.getPanel_view_panelDatabaseConnection_panelHeight(), null);
        panelDatabaseConnection.add(textFieldHostname);
        panelDatabaseConnection.add(textFieldPort);
        panelDatabaseConnection.add(textFieldDatabaseName);
        panelDatabaseConnection.add(buttonConnect);
        return panelDatabaseConnection;
    }

    public JTextField getTextFieldHostname() {
        return textFieldHostname;
    }

    public JTextField getTextFieldPort() {
        return textFieldPort;
    }

    public JTextField getTextFieldDatabaseName() {
        return textFieldDatabaseName;
    }
}
