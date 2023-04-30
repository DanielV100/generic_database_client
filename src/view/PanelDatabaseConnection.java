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
    private JTextField textFieldHostname = new JTextField();
    private JTextField textFieldPort = new JTextField();
    private JTextField textFieldDatabaseName = new JTextField();
    private JTextField textFieldUsername = new JTextField();
    private JTextField textFieldPassword = new JTextField();
    private JButton buttonConnect = new JButton();
    JFrame test = new JFrame();

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

        //creating text fields for (1) hostname (2) port (3) Database name (4) username (5) password
        textFieldHostname = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldHostname"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight());
        textFieldPort = uiHelpers.createJTextField(textFieldPort, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPort"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight());
        textFieldDatabaseName = uiHelpers.createJTextField(textFieldDatabaseName, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldDatabaseName"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight());
        textFieldUsername = uiHelpers.createJTextField(textFieldHostname, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldUsername"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldUsername_textFieldHeight());
        textFieldPassword = uiHelpers.createJTextField(textFieldPassword, controller.getAppPropertiesWithKey("textField.panelDatabaseConnection.textFieldPassword"), (sizes.getScreenWidth()/2)-(sizes.getButton_panelDatabaseConnection_buttonConnect_buttonWidth()/2), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldY(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldWidth(), sizes.getTextField_panelDatabaseConnection_textFieldPassword_textFieldHeight());
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
                    if(ex.toString().startsWith("Unkown database")) {
                        JOptionPane.showMessageDialog(null, "Verbindung nicht möglich. Die Datenbank wurde nicht gefunden...");
                    }
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
