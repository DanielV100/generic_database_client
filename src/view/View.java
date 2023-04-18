package view;

import controller.Controller;
import resources.Sizes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
This class builds the UI of the application's main frame - here shouldn't be any business logic.
Therefor Swing and AWT are used. Components such as frames, buttons and labels are created by calling methods from view.UIHelpers class.
 */
public class View {
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    private JFrame frameMain = new JFrame();
    private JPanel panelDatabaseConnection = new JPanel();
    private JButton buttonStart = new JButton();
    private JLabel labelGreeting = new JLabel();

    //creating the main window with its panels on it
    public View() throws IOException {
        //initializing panel with the  which later will be on the main frame
        panelDatabaseConnection = UIHelpers.createJPanel(panelDatabaseConnection, sizes.getPanel_view_panelDatabaseConnection_panelX(), sizes.getPanel_view_panelDatabaseConnection_panelY(), sizes.getPanel_view_panelDatabaseConnection_panelWidth(), sizes.getPanel_view_panelDatabaseConnection_panelHeight(), null);
        //initializing main frame
        frameMain = UIHelpers.createJFrame(frameMain, controller.getAppPropertiesWithKey("title.view.frameMain"), sizes.getFrame_view_mainFrame_frameWidth(), sizes.getFrame_view_mainFrame_frameHeight(), null, true);
        frameMain.add(panelDatabaseConnection);
        panelDatabaseConnection.setVisible(false);

    }
}
