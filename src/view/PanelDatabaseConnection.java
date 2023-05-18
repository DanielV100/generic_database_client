package view;

import controller.Controller;
import controller.DBConnection;
import controller.PopupMessageController;
import resources.Colors;
import resources.Sizes;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Creating components for the start-screen and initializing the frame for the table view.
 * @author Daniel, Lorenz, Luca, Valentin, Marius
 */
public class PanelDatabaseConnection {
    PopupMessageController popupMessageController = new PopupMessageController();
    DBConnection dbConnection = new DBConnection();
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    UIHelpers uiHelpers = new UIHelpers();

    //Panel for the start-screen
    private JPanel panelDatabaseConnection = new JPanel();
    // Choose your DB with a RadioButton
    private JRadioButton radioButtonMySql = new JRadioButton();
    private JRadioButton radioButtonMariaDB = new JRadioButton();
    private JRadioButton radioButtonPostgreSQL = new JRadioButton();
    //All db radio buttons are in this group
    public ButtonGroup panelDatabaseConnection_buttonGroupDB = new ButtonGroup();
    public static String selectedDB = "";
    private JRadioButton radioButtonSave = new JRadioButton();
    private JTextField textFieldHostname = new JTextField();
    private JTextField textFieldPort = new JTextField();
    private JTextField textFieldDatabaseName = new JTextField();
    private JTextField textFieldUsername = new JTextField();
    private JPasswordField textFieldPassword = new JPasswordField();
    private JButton buttonConnect = new JButton();
    private JTextField textFieldspeichern = new JTextField();
    JFrame frameTableView;
    String filePathtext = "Credentials/credentials.txt";
    File directory = new File("Credentials");
    int result = 0;
    Colors colors = new Colors();
    Color SetColor = colors.getStandardColor();
    CredentialManager credentialManager = new CredentialManager();

    PanelTableSelection panelTableSelection = new PanelTableSelection();

    /**
     * @return JPanel with start-screen on it
     * @throws IOException
     * @author Valentin
     */
    public JPanel PanelDatabaseConnection() throws IOException {
        sizes.init();
        frameTableView = uiHelpers.createJFrame(frameTableView, controller.getAppPropertiesWithKey("title.view.frameTableView"), sizes.getScreenWidth(), sizes.getScreenHeight(), null, false, WindowConstants.EXIT_ON_CLOSE, JFrame.MAXIMIZED_BOTH);
        createRadioButtons();
        addRadioButtonsEventListener();
        createTextFields();
        setupButton();
        loadUserCredentials();
        //initializing panel with the text fields and the button which later will be on the main frame
        panelDatabaseConnection = UIHelpers.createJPanel(panelDatabaseConnection, sizes.getPanel_view_panelDatabaseConnection_panelX(), sizes.getPanel_view_panelDatabaseConnection_panelY(), sizes.getScreenWidth(), sizes.getScreenHeight(), null, sizes.getSize());
        //adding all components to panel
        panelDatabaseConnection.add(radioButtonMySql);
        panelDatabaseConnection.add(radioButtonMariaDB);
        panelDatabaseConnection.add(radioButtonPostgreSQL);
        panelDatabaseConnection.add(textFieldHostname);
        panelDatabaseConnection.add(textFieldPort);
        panelDatabaseConnection.add(textFieldDatabaseName);
        panelDatabaseConnection.add(textFieldUsername);
        panelDatabaseConnection.add(textFieldPassword);
        panelDatabaseConnection.add(buttonConnect);
        panelDatabaseConnection.add(textFieldspeichern);
        panelDatabaseConnection.add(radioButtonSave);
        //background image
        JLabel backgroundLabel = new JLabel(new ImageIcon(controller.getImageIconFromResources("resources/test.jpg").getImage().getScaledInstance(panelDatabaseConnection.getWidth(), panelDatabaseConnection.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, panelDatabaseConnection.getWidth(), panelDatabaseConnection.getHeight());
        //logo
        JLabel imgLabel = new JLabel(new ImageIcon(controller.getImageIconFromResources("resources/logo.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        imgLabel.setBounds(sizes.getScreenWidth() / 2 - 75, 0, 150, 150);
        backgroundLabel.add(imgLabel);
        panelDatabaseConnection.add(backgroundLabel);
        return panelDatabaseConnection;
    }

    /**
     * Creating all radio buttons. (1) MySQL (2) MariaDB (3) PostgreSQL (4) Save
     *
     * @author Valentin
     */
    private void createRadioButtons() {
        radioButtonMySql = uiHelpers.createJRadioButton(radioButtonMySql, ((sizes.getScreenWidth() / 2) - (sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonLabel());
        radioButtonMySql.setBackground(SetColor);
        radioButtonMariaDB = uiHelpers.createJRadioButton(radioButtonMariaDB, ((sizes.getScreenWidth() / 2) - (sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonLabel());
        radioButtonPostgreSQL = uiHelpers.createJRadioButton(radioButtonPostgreSQL, ((sizes.getScreenWidth() / 2) - (sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonLabel());
        radioButtonSave = uiHelpers.createJRadioButton(radioButtonSave, ((sizes.getScreenWidth() / 2) - (sizes.getRadioButton_panelDatabaseConnection_radioButtonSave_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonSave_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonSave_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonSave_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonSave_radioButtonLabel());
        // Buttongroup: only one DB can be selected
        panelDatabaseConnection_buttonGroupDB.add(radioButtonMySql);
        panelDatabaseConnection_buttonGroupDB.add(radioButtonMariaDB);
        panelDatabaseConnection_buttonGroupDB.add(radioButtonPostgreSQL);
        System.out.println("Created all radio buttons");
    }

    /**
     * Adding all ActionListener to radio buttons.
     */
    private void addRadioButtonsEventListener() {
        radioButtonMySql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "mysql";
                SetColor = colors.getSqlColor();
                setColorTheme();
            }
        });
        radioButtonMariaDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "mariadb";
                SetColor = colors.getMariaColor();
                setColorTheme();
            }
        });
        radioButtonPostgreSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "postgresql";
                SetColor = colors.getPostColor();
                setColorTheme();
            }
        });
        System.out.println("Set all event listener for radio buttons");
    }

    /**
     * Changing color theme of the start-screen ui.
     *
     * @author Lorenz
     */
    private void setColorTheme() {
        buttonConnect.setBackground(SetColor);
        textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
        textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
        textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
        textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
        textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
        textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
        System.out.println("Made color-theme change");
    }

    /**
     * Creating all text fields (and password field)
     *
     * @throws IOException
     * @author Lorenz
     */
    private void createTextFields() throws IOException {
        textFieldHostname = uiHelpers.createTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), (sizes.getScreenWidth() / 2) - (sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth() / 2), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight(), "Hostname (localhost, ip)", BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue), false, new Font("Arial", Font.PLAIN, 20), false);
        textFieldHostname.addMouseListener(createTextFieldMouseAdapter(textFieldHostname));
        textFieldHostname.addFocusListener(createTextFieldFocusListener(textFieldHostname));

        textFieldPort = uiHelpers.createTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), (sizes.getScreenWidth() / 2) - (sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth() / 2), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight(), "Port number (3306 is standard)", BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue), false, new Font("Arial", Font.PLAIN, 20), false);
        textFieldPort.addMouseListener(createTextFieldMouseAdapter(textFieldPort));
        textFieldPort.addFocusListener(createTextFieldFocusListener(textFieldPort));

        textFieldDatabaseName = uiHelpers.createTextField(textFieldDatabaseName, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldDatabaseName"), (sizes.getScreenWidth() / 2) - (sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth() / 2), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight(), "Database name", BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue), false, new Font("Arial", Font.PLAIN, 20), false);
        textFieldDatabaseName.addMouseListener(createTextFieldMouseAdapter(textFieldDatabaseName));
        textFieldDatabaseName.addFocusListener(createTextFieldFocusListener(textFieldDatabaseName));

        textFieldUsername = uiHelpers.createTextField(textFieldUsername, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldUsername"), (sizes.getScreenWidth() / 2) - (sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth() / 2), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldHeight(), "Username", BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue), false, new Font("Arial", Font.PLAIN, 20), false);
        textFieldUsername.addMouseListener(createTextFieldMouseAdapter(textFieldUsername));
        textFieldUsername.addFocusListener(createTextFieldFocusListener(textFieldUsername));

        textFieldPassword = (JPasswordField) uiHelpers.createTextField(textFieldPassword, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPassword"), (sizes.getScreenWidth() / 2) - (sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth() / 2), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldHeight(), "Username", BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue), false, new Font("Arial", Font.PLAIN, 20), true);
        textFieldPassword.addMouseListener(createTextFieldMouseAdapter(textFieldPassword));
        textFieldPassword.addFocusListener(createTextFieldFocusListener(textFieldPassword));

        textFieldspeichern = uiHelpers.createTextField(textFieldspeichern, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldspeichern"), (sizes.getScreenWidth() / 2) - (sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth() / 2), sizes.getTextField_panelDatabaseConnection_textFieldspeichern_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldspeichern_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldspeichern_textFieldHeight(), "Ladeinformationen Speichername (Name)", BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue), false, new Font("Arial", Font.PLAIN, 20), false);
        textFieldspeichern.addMouseListener(createTextFieldMouseAdapter(textFieldspeichern));
        textFieldspeichern.addFocusListener(createTextFieldFocusListener(textFieldspeichern));
        System.out.println("Created all text fields");
    }

    /**
     * Returns a mouse adapter which get triggerd bei pressing. Sets text-field text to ""
     *
     * @param textField
     * @return MouseAdapter
     * @author Daniel
     */
    private MouseAdapter createTextFieldMouseAdapter(JTextField textField) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField.setText("");
            }
        };
        System.out.println("Added mouse adapter to text-field");
        return mouseAdapter;
    }

    /**
     * Returns a focus listener which is triggered when text-field gets focus. Border color then changes.
     *
     * @param textField
     * @return
     * @author Daniel
     */
    private FocusListener createTextFieldFocusListener(JTextField textField) {
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
            }
        };
        System.out.println("Added focus listener to text-field");
        return focusListener;
    }

    /**
     * Setting up the connect button
     * author Lorenz
     */
    private void setupButton() throws IOException {
        buttonConnect = uiHelpers.createJButton(buttonConnect, (sizes.getScreenWidth() / 2) - (sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth() / 2), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonY(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonHeight(), controller.getAppPropertiesWithKey("button.panelDatabaseConnection.buttonConnect"), SetColor, Color.WHITE);
        buttonConnect.setUI(new BasicButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(Color.ORANGE);
                g.drawRect(0, 0, b.getWidth() - 1, b.getHeight() - 1);
            }
        });
        buttonConnect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change the background color when the mouse enters the button
                buttonConnect.setBackground(Color.BLACK);
                buttonConnect.setForeground(Color.WHITE);
                Font font = buttonConnect.getFont().deriveFont(Font.BOLD, 16f);
                buttonConnect.setFont(font);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Change the background color back to the default when the mouse exits the button
                buttonConnect.setBackground(SetColor);
                buttonConnect.setForeground(Color.WHITE);
                Font font = buttonConnect.getFont().deriveFont(Font.PLAIN, 14f);
                buttonConnect.setFont(font);
            }
        });
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonConnectClicked();
            }
        });
        System.out.println("Created connect button");
    }

    /**
     * On connect button click action
     * @autor Marius
     */
    private void buttonConnectClicked() {
        saveCredentials();
        initDatabaseConnection();
    }

    /**
     * Method for saving credentials.
     * @author Lorenz
     */
    private void saveCredentials() {
        if (radioButtonSave.isSelected()) {
            int savePassword = JOptionPane.showConfirmDialog(null, "Möchten Sie die das Password speichern?", "Bestätigen", JOptionPane.YES_NO_OPTION);
            String hostname = textFieldHostname.getText();
            String portname = textFieldPort.getText();
            String dbname = textFieldDatabaseName.getText();
            String username = textFieldUsername.getText();
            String save = textFieldspeichern.getText();
            String password = "123";
            String selecteddb = selectedDB;
            if (savePassword == JOptionPane.YES_OPTION) {
                password = textFieldPassword.getText();
            }
            UserCredentials credentials = new UserCredentials(hostname, portname, dbname, username, password, selecteddb, save);
            credentialManager.saveCredentials(credentials, filePathtext);
        }
        System.out.println("Credentials saved");
    }

    /**
     * Inits database connection and shows frame with table selection on it.
     * @author Luca
     */
    private void initDatabaseConnection() {
        try {
            Connection connection = dbConnection.initDBConnection(textFieldHostname.getText(), textFieldPort.getText(), textFieldDatabaseName.getText(), textFieldUsername.getText(), textFieldPassword.getText(), selectedDB);
            popupMessageController.showSuccessMessage("Successfully connected to database server");
            System.out.println("Connected to database");
            frameTableView.add(panelTableSelection.PanelTableSelection(connection, selectedDB));
            frameTableView.setVisible(true);
            //close db connection while closing application
            frameTableView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        System.out.println("DB connection closed");
                        connection.close();
                    } catch (SQLException ex) {
                        popupMessageController.showErrorMessage(ex);
                    }
                }
            });
        } catch (SQLException ex) {
            popupMessageController.showErrorMessage(ex);
        } catch (ClassNotFoundException ex) {
            popupMessageController.showErrorMessage(ex);
        }
    }

    /**
     * Loading user credentials or create directory for user credentials.
     * @author Lorenz
     */
    private void loadUserCredentials() {
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            List<UserCredentials> credentialsList = credentialManager.loadCredentials(filePathtext);
            String[] options = new String[credentialsList.size() + 1];
            options[0] = "Nein";
            for (int i = 0; i < credentialsList.size(); i++) {
                options[i + 1] = credentialsList.get(i).getspeichern();
            }
            int result = JOptionPane.showOptionDialog(null, "Möchten Sie gespeicherte Anmeldeinformationen laden?", "Bestätigen", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (result >= 1) {
                UserCredentials selectedCredentials = credentialsList.get(result - 1);
                String hostnameload = selectedCredentials.getHostname();
                String portnameload = selectedCredentials.getPortname();
                String dbnameload = selectedCredentials.getDbname();
                String usernameload = selectedCredentials.getUsername();
                String passwordload = selectedCredentials.getPassword();
                String selecteddbload = selectedCredentials.getSelectedDB();

                textFieldHostname.setText(hostnameload);
                textFieldPort.setText(portnameload);
                textFieldDatabaseName.setText(dbnameload);
                textFieldUsername.setText(usernameload);
                textFieldPassword.setText(passwordload);

                if (selecteddbload.toLowerCase().equals("mysql")) {
                    selectedDB = selecteddbload;
                    radioButtonMySql.setSelected(true);
                    SetColor = colors.getSqlColor();
                    buttonConnect.setBackground(SetColor);
                } else if (selecteddbload.toLowerCase().equals("mariadb")) {
                    selectedDB = selecteddbload;
                    radioButtonMariaDB.setSelected(true);
                    SetColor = colors.getMariaColor();
                    buttonConnect.setBackground(SetColor);
                } else if (selecteddbload.toLowerCase().equals("postgresql")) {
                    selectedDB = selecteddbload;
                    radioButtonPostgreSQL.setSelected(true);
                    SetColor = colors.getPostColor();
                    buttonConnect.setBackground(SetColor);
                }
               textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                System.out.println("User credentials loaded");
            } else {
                System.out.println("User credentials not loaded");
            }
        }
    }
}
