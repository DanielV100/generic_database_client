package view;

import controller.Controller;
import controller.DBConnection;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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
    private JPasswordField textFieldPassword = new JPasswordField();
    private JButton buttonConnect = new JButton();

    int result = JOptionPane.showConfirmDialog(null, "Möchten Sie die gespeicherten Anmeldeinformationen laden?", "Bestätigen", JOptionPane.YES_NO_OPTION);
    String filePathtext = "credentials.txt";
    JFrame test = new JFrame();

    PanelTableSelection panelTableSelection = new PanelTableSelection();

    //"/Users/lorenz.lederer/Downloads/5g-networking-technology-background-with-blue-digital-line/rm373batch2-08.jpg"));
    public JPanel PanelDatabaseConnection() throws IOException {
        sizes.init();
        test.setLayout(null);
        test.setBounds(0, 0, 1200, 800);
        test.setVisible(false);
        test.setExtendedState(JFrame.MAXIMIZED_BOTH);
        test.setUndecorated(false);


        //creating text fields for (1) hostname (2) port (3) Database name (4) username (5) password
        textFieldHostname = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight());
        textFieldPort = uiHelpers.createJTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight());
        textFieldDatabaseName = uiHelpers.createJTextField(textFieldDatabaseName, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldDatabaseName"), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight());
        textFieldUsername = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldUsername"), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldHeight());
        textFieldPassword = uiHelpers.createJPasswordField(textFieldPassword, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPassword"), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldX(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldHeight());
        //creating button for building the connection
        buttonConnect = uiHelpers.createJButton(buttonConnect, sizes.getButton_panelDatabaseConnection_buttonConnect_buttonX(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonY(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonHeight(), controller.getAppPropertiesWithKey("button.panelDatabaseConnection.buttonConnect"), Color.GREEN);
        //Textfeld-Inhalte nach Click entfernen
        panelDatabaseConnection.setLayout(new BorderLayout());

        textFieldPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textFieldPassword.setText("");
            }
        });
        textFieldUsername.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textFieldUsername.setText("");
            }
        });
        textFieldDatabaseName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textFieldDatabaseName.setText("");
            }
        });
        textFieldPort.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textFieldPort.setText("");            }
        });
        textFieldHostname.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textFieldHostname.setText("");
            }

        });

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = dbConnection.initDBConnection(textFieldHostname.getText(), textFieldPort.getText(), textFieldDatabaseName.getText(), textFieldUsername.getText(), textFieldPassword.getText());
                    //Erfolgsmeldung
                    JOptionPane.showMessageDialog(null, "Verbindung erfolgreich");

                    int passwordspeichern = JOptionPane.showConfirmDialog(null, "Möchten Sie die das Password speichern?", "Bestätigen", JOptionPane.YES_NO_OPTION);
                    String hostname = textFieldHostname.getText();
                    String portname = textFieldPort.getText();
                    String dbname   = textFieldDatabaseName.getText();
                    String username = textFieldUsername.getText();
                    String password = "123";
                    if (passwordspeichern == JOptionPane.YES_OPTION) {
                    password = textFieldPassword.getText();
                    }
                    UserCredentials credentials = new UserCredentials(hostname, portname, dbname, username, password);
                    CredentialManager.saveCredentials(credentials, filePathtext);
                    test.add(panelTableSelection.PanelTableSelection(connection));
                    //test.requestFocus();
                    test.setVisible(true);
                    //frameMain.setVisible(false);
                } catch (SQLException ex) {
                    //Fehlermeldung
                     JOptionPane.showMessageDialog(null, "Verbindung nicht möglich");
                    throw new RuntimeException(ex);
                }
            }
        });

        //initializing panel with the text fields and the button which later will be on the main frame
        panelDatabaseConnection = UIHelpers.createJPanel(panelDatabaseConnection, sizes.getPanel_view_panelDatabaseConnection_panelX(), sizes.getPanel_view_panelDatabaseConnection_panelY(), sizes.getScreenWidth(),  sizes.getScreenHeight(), null);

        panelDatabaseConnection.setPreferredSize(sizes.getSize());
        panelDatabaseConnection.add(textFieldHostname,Component.CENTER_ALIGNMENT);
        panelDatabaseConnection.add(textFieldPort,BorderLayout.CENTER);
        panelDatabaseConnection.add(textFieldDatabaseName);
        panelDatabaseConnection.add(textFieldUsername);
        panelDatabaseConnection.add(textFieldPassword);
        panelDatabaseConnection.add(buttonConnect);

        //laden von gespeicherten Nutzerdaten
        UserCredentials credentials = CredentialManager.loadCredentials(filePathtext);
        if (credentials != null) {
            // do something with the loaded credentials
            if (result == JOptionPane.YES_OPTION) {
                // Anmeldeinformationen laden und in Textfelder anzeigen
                String hostnameload = credentials.getHostname();
                String portnameload = credentials.getPortname();
                String dbnameload = credentials.getDbname();
                String usernameload = credentials.getUsername();
                String passwordload = credentials.getPassword();
                textFieldHostname.setText(hostnameload);
                textFieldPort.setText(portnameload);
                textFieldDatabaseName.setText(dbnameload);
                textFieldUsername.setText(usernameload);
                textFieldPassword.setText(passwordload);
            } else {
                // handle case where no credentials were loaded
            }
        }

        File file = new File(getClass().getClassLoader().getResource("resources/test.jpg").getFile());
        String filePath = file.getAbsolutePath();
        ImageIcon backgroundIcon = new ImageIcon(filePath);

        Image image = backgroundIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(panelDatabaseConnection.getWidth(),panelDatabaseConnection.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        backgroundIcon = new ImageIcon(newimg);  // transform it back

        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, panelDatabaseConnection.getWidth(), panelDatabaseConnection.getHeight());
        panelDatabaseConnection.add(backgroundLabel);

        return panelDatabaseConnection;

    }

}
