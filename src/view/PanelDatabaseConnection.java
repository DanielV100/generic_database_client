package view;

import controller.Controller;
import resources.Sizes;

import javax.swing.*;
import java.io.IOException;

public class PanelDatabaseConnection {
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    private JPanel panelDatabaseConnection = new JPanel();
    private JTextField textFieldHostname = new JTextField();
    private JTextField textFieldPort = new JTextField();

    public JPanel PanelDatabaseConnection() throws IOException {
        //creating text fields for (1) hostname (2) port
        textFieldHostname = UIHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight());
        textFieldPort = UIHelpers.createJTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), )
        //initializing panel with the  which later will be on the main frame
        panelDatabaseConnection = UIHelpers.createJPanel(panelDatabaseConnection, sizes.getPanel_view_panelDatabaseConnection_panelX(), sizes.getPanel_view_panelDatabaseConnection_panelY(), sizes.getPanel_view_panelDatabaseConnection_panelWidth(), sizes.getPanel_view_panelDatabaseConnection_panelHeight(), null);
        panelDatabaseConnection.add(textFieldHostname);
        return panelDatabaseConnection;
    }
}
