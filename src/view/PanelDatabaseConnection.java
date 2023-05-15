package view;

import controller.Controller;
import controller.DBConnection;
import resources.Sizes;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import static view.CredentialManager.loadCredentials;

public class PanelDatabaseConnection {
    PopupMessages popupMessages = new PopupMessages();
    DBConnection dbConnection = new DBConnection();
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    UIHelpers uiHelpers = new UIHelpers();

    private JPanel panelDatabaseConnection = new JPanel();


    // Choose your DB with a RadioButton
    private JRadioButton radioButtonMySql = new JRadioButton();
    private JRadioButton radiocheckbox = new JRadioButton();
    private JRadioButton radioButtonMariaDB = new JRadioButton();
    private JRadioButton radioButtonPostgreSQL = new JRadioButton();
    private JRadioButton radioButtonspeichern = new JRadioButton();
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
    private JTextField textFieldspeichern = new JTextField();
    JFrame test = new JFrame();
    String filePathtext = "Credentials/credentials.txt";
    File directory = new File("Credentials");
    int result = 0;
    Colorscheme colors = new Colorscheme();
    Color SetColor = colors.getStandardColor();

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

        /*JLabel headingLabel = new JLabel("Generic Database Client");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 48));
        headingLabel.setForeground(Color.DARK_GRAY);
        headingLabel.setBounds((sizes.getScreenWidth() / 2) - 280, 10, 600, 200);*/


        // crating radioButtons for (1) MySQL (2) mariaDB (3) PostgreSQL
        radioButtonMySql = uiHelpers.createJRadioButton(radioButtonMySql, ((sizes.getScreenWidth()/2)-(sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonLabel());
        radioButtonMySql.setBackground(SetColor);
        radioButtonMariaDB = uiHelpers.createJRadioButton(radioButtonMariaDB, ((sizes.getScreenWidth()/2)-(sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonLabel());
        radioButtonPostgreSQL = uiHelpers.createJRadioButton(radioButtonPostgreSQL, ((sizes.getScreenWidth()/2)-(sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonLabel());
        radioButtonspeichern = uiHelpers.createJRadioButton(radioButtonspeichern, ((sizes.getScreenWidth()/2)-(sizes.getRadioButton_panelDatabaseConnection_radioButtonspeichern_radioButtonX())), (sizes.getRadioButton_panelDatabaseConnection_radioButtonspeichern_radioButtonY()), sizes.getRadioButton_panelDatabaseConnection_radioButtonspeichern_radioButtonWidth(), sizes.getRadioButton_panelDatabaseConnection_radioButtonspeichern_radioButtonHeight(), sizes.getRadioButton_panelDatabaseConnection_radioButtonspeichern_radioButtonLabel());

        // Eventlistener for radioButtons
        radioButtonMySql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "mysql";
                SetColor = colors.getSqlColor();
                buttonConnect.setBackground(SetColor);
                textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                radioButtonMySql.setBackground(SetColor);
            }
        });
        radioButtonMariaDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "mariadb";
                SetColor = colors.getMariaColor();
                buttonConnect.setBackground(SetColor);
                textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
            }
        });
        radioButtonPostgreSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDB = "postgresql";
                SetColor = colors.getPostColor();
                buttonConnect.setBackground(SetColor);
                textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
                textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor));
            }
        });
        //creating text fields for (1) hostname (2) port (3) Database name (4) username (5) password

        textFieldHostname = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight());
        textFieldHostname.setToolTipText("Hostname (localhost, ip)");
        textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue)); // set the border
        textFieldHostname.setOpaque(false);
        textFieldHostname.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldPort = uiHelpers.createJTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight());
        textFieldPort.setToolTipText("Port number (3306 is standard)");
        textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue)); // set the border
        textFieldPort.setOpaque(false);
        textFieldPort.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldDatabaseName = uiHelpers.createJTextField(textFieldDatabaseName, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldDatabaseName"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight());
        textFieldDatabaseName.setToolTipText("Database name");
        textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue)); // set the border
        textFieldDatabaseName.setOpaque(false);
        textFieldDatabaseName.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldUsername = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldUsername"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldHeight());
        textFieldUsername.setToolTipText("Username");
        textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue)); // set the border
        textFieldUsername.setOpaque(false);
        textFieldUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldPassword = uiHelpers.createJPasswordField(textFieldPassword, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPassword"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldHeight());
        textFieldPassword.setToolTipText("Password");
        textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue)); // set the border
        textFieldPassword.setOpaque(false);
        textFieldPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldspeichern = uiHelpers.createJTextField(textFieldspeichern, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldspeichern"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldspeichern_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldspeichern_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldspeichern_textFieldHeight());
        textFieldspeichern.setToolTipText("Ladeinformationen Speichername (Name)");
        textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.blue)); // set the border
        textFieldspeichern.setOpaque(false);
        //textFieldspeichern.setFont(new Font("Arial", Font.PLAIN, 20));
        //Speichern der Daten soll nicht beim ersten Start erfolgen
        //creating button for building the connection
        buttonConnect = uiHelpers.createJButton(buttonConnect, (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonY(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth(), sizes.getButton_panelDatabaseConnection_buttonConnect_buttonHeight(), controller.getAppPropertiesWithKey("button.panelDatabaseConnection.buttonConnect"));
        buttonConnect.setBackground(SetColor);
        buttonConnect.setForeground(Color.WHITE);
        buttonConnect.setUI(new BasicButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                // Override the paintButtonPressed method to change the border color
                g.setColor(Color.ORANGE);
                g.drawRect(0, 0, b.getWidth()-1, b.getHeight()-1);
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
        textFieldspeichern.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textFieldspeichern.setText("");
            }

        });
        textFieldHostname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black)); // set the border
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldHostname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor)); // set the border
            }
        });
        textFieldPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // set the border
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor)); // set the border
            }
        });
        textFieldUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // set the border
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor)); // set the border
            }
        });
        textFieldPort.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // set the border
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldPort.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor)); // set the border
            }
        });
        textFieldDatabaseName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // set the border
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldDatabaseName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor)); // set the border
            }
        });
        textFieldspeichern.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // set the border
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldspeichern.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SetColor)); // set the border
            }
        });

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = dbConnection.initDBConnection(textFieldHostname.getText(), textFieldPort.getText(), textFieldDatabaseName.getText(), textFieldUsername.getText(), textFieldPassword.getText());
                    //Erfolgsmeldung
                    popupMessages.showSuccessMessage("Successfully connected to database server");


                    if (radioButtonspeichern.isSelected()) {
                    int passwordspeichern = JOptionPane.showConfirmDialog(null, "Möchten Sie die das Password speichern?", "Bestätigen", JOptionPane.YES_NO_OPTION);
                    String hostname = textFieldHostname.getText();
                    String portname = textFieldPort.getText();
                    String dbname   = textFieldDatabaseName.getText();
                    String username = textFieldUsername.getText();
                    String speichern = textFieldspeichern.getText();
                    String password = "123";
                    String selecteddb = selectedDB;
                    if (passwordspeichern == JOptionPane.YES_OPTION) {
                    password = textFieldPassword.getText();
                    }
                    UserCredentials credentials = new UserCredentials(hostname, portname, dbname, username, password, selecteddb, speichern);
                    CredentialManager.saveCredentials(credentials, filePathtext);
                    }
                    test.add(panelTableSelection.PanelTableSelection(connection));
                    test.setVisible(true);
                    //close db connection while closing application
                    test.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            try {
                                System.out.println("DB connection closed");
                                connection.close();
                            } catch (SQLException ex) {
                                popupMessages.showErrorMessage(ex);
                            }
                        }
                    });

                } catch (SQLException ex) {
                    //Fehlermeldung
                    if(ex.toString().startsWith("Unknown database")) {
                        popupMessages.showErrorMessage(ex);
                    } else {
                        popupMessages.showErrorMessage(ex);
                    }
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

        //panelDatabaseConnection.add(headingLabel);
        panelDatabaseConnection.add(textFieldHostname,Component.CENTER_ALIGNMENT);
        panelDatabaseConnection.add(textFieldPort,BorderLayout.CENTER);
        panelDatabaseConnection.add(textFieldDatabaseName);
        panelDatabaseConnection.add(textFieldUsername);
        panelDatabaseConnection.add(textFieldPassword);
        panelDatabaseConnection.add(buttonConnect);

        panelDatabaseConnection.add(textFieldspeichern);
        panelDatabaseConnection.add(radioButtonspeichern);




        //laden von gespeicherten Nutzerdaten
        if (!directory.exists()) {
            directory.mkdir();
        }
        else {
        List<UserCredentials> credentialsList = loadCredentials(filePathtext);
        String[] options = new String[credentialsList.size() + 1];
        options[0] = "Nein";
        for (int i = 0; i < credentialsList.size(); i++) {
            options[i+1] = credentialsList.get(i).getspeichern();
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

            if(selecteddbload.toLowerCase().equals("mysql")) {
                selectedDB = selecteddbload;
                radioButtonMySql.setSelected(true);
                SetColor = colors.getSqlColor();
                buttonConnect.setBackground(SetColor);
            } else if(selecteddbload.toLowerCase().equals("mariadb")) {
                selectedDB = selecteddbload;
                radioButtonMariaDB.setSelected(true);
                SetColor = colors.getMariaColor();
                buttonConnect.setBackground(SetColor);
            } else if(selecteddbload.toLowerCase().equals("postgresql")) {
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
            // Use the selected credentials to establish a database connection
        } else {
            // User selected "Nein"
        }
                // Anmeldeinformationen laden und in Textfelder anzeigen
        }

        File file = new File(getClass().getClassLoader().getResource("resources/test.jpg").getFile());
        String filePath = file.getAbsolutePath();
        ImageIcon backgroundIcon = new ImageIcon(filePath);

        Image image = backgroundIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(panelDatabaseConnection.getWidth(),panelDatabaseConnection.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        backgroundIcon = new ImageIcon(newimg);  // transform it back

        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, panelDatabaseConnection.getWidth(), panelDatabaseConnection.getHeight());
        //backgroundLabel.setOpaque(true);
        ImageIcon icon = new ImageIcon(ImageIO.read(new File("src/resources/logo.png")));
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setBounds(sizes.getScreenWidth()/2-75, 0, 150, 150);
        Image imagelabel = icon.getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(imagelabel));
        backgroundLabel.add(imgLabel);
        panelDatabaseConnection.add(backgroundLabel);
        return panelDatabaseConnection;

    }

}
