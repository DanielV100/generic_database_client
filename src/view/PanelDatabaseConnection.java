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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelDatabaseConnection {
    DBConnection dbConnection = new DBConnection();
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    UIHelpers uiHelpers = new UIHelpers();

    private JPanel panelDatabaseConnection = new JPanel();

    // Choose your DB with a Checkbox
    private JCheckBox checkBoxMySql = new JCheckBox();
    private JCheckBox checkBoxMariaDB = new JCheckBox();
    private JCheckBox checkBoxPostgreSQL = new JCheckBox();

    // Choose your DB with a RadioButton
    private JRadioButton radioButtonMySql = new JRadioButton();
    private JRadioButton radioButtonMariaDB = new JRadioButton();
    private JRadioButton radioButtonPostgreSQL = new JRadioButton();
    public ButtonGroup panelDatabaseConnection_buttonGroupDB = new ButtonGroup();
    public static String selectedDB = "";
    /*panelDatabaseConnection_buttonGroupDB.add(radioButtonMySql);
    panelDatabaseConnection_buttonGroupDB.add(radioButtonMariaDB);
    panelDatabaseConnection_buttonGroupDB.add(radioButtonPostgreSQL);*/

    private JTextField textFieldHostname = new JTextField();
    private JTextField textFieldPort = new JTextField();
    private JTextField textFieldDatabaseName = new JTextField();
    private JTextField textFieldUsername = new JTextField();
    private JPasswordField textFieldPassword = new JPasswordField();
    private JButton buttonConnect = new JButton();
    String filePathtext = "Credentials/credentials.txt";
    File filetext = new File(filePathtext);

    JFrame test = new JFrame();

    int result = 0;

    PanelTableSelection panelTableSelection = new PanelTableSelection();

    //"/Users/lorenz.lederer/Downloads/5g-networking-technology-background-with-blue-digital-line/rm373batch2-08.jpg"));
    public JPanel PanelDatabaseConnection() throws IOException {
        sizes.init();
        test.setLayout(null);
        test.setBounds(0, 0, sizes.getScreenWidth(), sizes.getScreenHeight());
        test.setVisible(false);
        test.setExtendedState(JFrame.MAXIMIZED_BOTH);
        test.setUndecorated(false);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //creating checkboxes for (1) MySQL (2) mariaDB (3) PostgreSQL
        /*checkBoxMySql = uiHelpers.createJCheckBox(checkBoxMySql, ((sizes.getScreenWidth()/2)-(sizes.getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxX())), (sizes.getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxY()), sizes.getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxWidth(), sizes.getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxHeight(), sizes.getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxLabel());
        checkBoxMariaDB = uiHelpers.createJCheckBox(checkBoxMariaDB, ((sizes.getScreenWidth()/2)-(sizes.getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxX())), (sizes.getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxY()), sizes.getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxWidth(), sizes.getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxHeight(), sizes.getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxLabel());
        checkBoxPostgreSQL = uiHelpers.createJCheckBox(checkBoxPostgreSQL, ((sizes.getScreenWidth()/2)-(sizes.getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxX())), (sizes.getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxY()), sizes.getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxWidth(), sizes.getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxHeight(), sizes.getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxLabel());
        */

        // crating radioButtons for (1) MySQL (2) mariaDB (3) PostgreSQL
        radioButtonMySql = uiHelpers.createJRadioButton(radioButtonMySql, ((sizes.getScreenWidth()/2)-(sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonLabel());
        radioButtonMariaDB = uiHelpers.createJRadioButton(radioButtonMariaDB, ((sizes.getScreenWidth()/2)-(sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonLabel());
        radioButtonPostgreSQL = uiHelpers.createJRadioButton(radioButtonPostgreSQL, ((sizes.getScreenWidth()/2)-(sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonLabel());

        // Eventlistener for radioButtons
        radioButtonMySql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "mysql";
            }
        });
        radioButtonMariaDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "mariadb";
            }
        });
        radioButtonPostgreSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "postgresql";
            }
        });
        //creating text fields for (1) hostname (2) port (3) Database name (4) username (5) password

        textFieldHostname = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight());
        textFieldPort = uiHelpers.createJTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight());
        textFieldDatabaseName = uiHelpers.createJTextField(textFieldDatabaseName, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldDatabaseName"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight());
        textFieldUsername = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldUsername"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldHeight());
        textFieldPassword = uiHelpers.createJPasswordField(textFieldPassword, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPassword"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldHeight());

        File directory = new File("Credentials");
        if (!directory.exists()) {
            directory.mkdir();
        }

        //Speichern der Daten soll nicht beim ersten Start erfolgen
        if (filetext.exists()) {
            result = JOptionPane.showConfirmDialog(null, "Möchten Sie gespeicherte Anmeldeinformationen laden?", "Bestätigen", JOptionPane.YES_NO_OPTION);

        }
        //creating button for building the connection
        buttonConnect = uiHelpers.createJButton(buttonConnect, (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonY(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonHeight(), controller.getAppPropertiesWithKey("button.panelDatabaseConnection.buttonConnect"), Color.GREEN);
        //Textfeld-Inhalte nach Click entfernen

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
                    String selecteddb = selectedDB;
                    if (passwordspeichern == JOptionPane.YES_OPTION) {
                    password = textFieldPassword.getText();
                    }
                    UserCredentials credentials = new UserCredentials(hostname, portname, dbname, username, password, selecteddb);
                    CredentialManager.saveCredentials(credentials, filePathtext);
                    test.add(panelTableSelection.PanelTableSelection(connection));
                    //test.requestFocus();
                    test.setVisible(true);
                    //frameMain.setVisible(false);
                    //close db connection while closing application
                    test.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            try {
                                System.out.println("DB connection closed");
                                connection.close();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });

                } catch (SQLException ex) {
                    //Fehlermeldung
                    if(ex.toString().startsWith("Unknown database")) {
                        System.out.println(ex.toString());
                        JOptionPane.showMessageDialog(null, "Verbindung nicht möglich. Die Datenbank wurde nicht gefunden...");
                    } else {
                        JOptionPane.showMessageDialog(null, "Verbindung nicht möglich");
                    }


                    throw new RuntimeException(ex);
                }
            }
        });

        //initializing panel with the text fields and the button which later will be on the main frame
        panelDatabaseConnection = UIHelpers.createJPanel(panelDatabaseConnection, sizes.getPanel_view_panelDatabaseConnection_panelX(), sizes.getPanel_view_panelDatabaseConnection_panelY(), sizes.getScreenWidth(),  sizes.getScreenHeight(), null);

        panelDatabaseConnection.setPreferredSize(sizes.getSize());

        // Buttongroup: only one DB can be selected
        panelDatabaseConnection_buttonGroupDB.add(radioButtonMySql);
        panelDatabaseConnection_buttonGroupDB.add(radioButtonMariaDB);
        panelDatabaseConnection_buttonGroupDB.add(radioButtonPostgreSQL);

        panelDatabaseConnection.add(radioButtonMySql);
        panelDatabaseConnection.add(radioButtonMariaDB);
        panelDatabaseConnection.add(radioButtonPostgreSQL);

        panelDatabaseConnection.add(textFieldHostname,Component.CENTER_ALIGNMENT);
        panelDatabaseConnection.add(textFieldPort,BorderLayout.CENTER);
        panelDatabaseConnection.add(textFieldDatabaseName);
        panelDatabaseConnection.add(textFieldUsername);
        panelDatabaseConnection.add(textFieldPassword);
        panelDatabaseConnection.add(buttonConnect);

        //laden von gespeicherten Nutzerdaten
        int verbindung = 0;
        UserCredentials credentials = CredentialManager.loadCredentials(filePathtext, verbindung);
        if (credentials != null) {
            // do something with the loaded credentials
            if (result == JOptionPane.YES_OPTION) {
                // Anmeldeinformationen laden und in Textfelder anzeigen
                String hostnameload = credentials.getHostname();
                String portnameload = credentials.getPortname();
                String dbnameload = credentials.getDbname();
                String usernameload = credentials.getUsername();
                String passwordload = credentials.getPassword();
                String selecteddbload = credentials.getSelectedDB();
                textFieldHostname.setText(hostnameload);
                textFieldPort.setText(portnameload);
                textFieldDatabaseName.setText(dbnameload);
                textFieldUsername.setText(usernameload);
                textFieldPassword.setText(passwordload);
                if(selecteddbload.toLowerCase().equals("mysql")) {
                    selectedDB = selecteddbload;
                    radioButtonMySql.setSelected(true);
                } else if(selecteddbload.toLowerCase().equals("mariadb")) {
                    selectedDB = selecteddbload;
                    radioButtonMariaDB.setSelected(true);
                } else if(selecteddbload.toLowerCase().equals("postgresql")) {
                    selectedDB = selecteddbload;
                    radioButtonPostgreSQL.setSelected(true);
                }
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
