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
    PanelDatabaseConnection panelDatabaseConnection = new PanelDatabaseConnection();
    public JFrame frameMain = new JFrame("Fullscreen");

    //JLabel backImgPanel = new JLabel(new ImageIcon("/Users/lorenz.lederer/Downloads/5g-networking-technology-background-with-blue-digital-line/rm373batch2-08.jpg"));


    //creating the main window with its panels on it
    public View() throws IOException {
        //initializing main frame
        //frameMain = UIHelpers.createJFrame(frameMain, controller.getAppPropertiesWithKey("title.view.frameMain"),sizes.getPanel_panelTableView_panelWidth(),sizes.getPanel_panelTableView_panelHeight(),null, true);/backImgPanel.setLayout(null);

        //backImgPanel.setLayout(null);

        //frameMain.add(backImgPanel);
        //frameMain.setBackground(Color.BLACK);
        frameMain.add(panelDatabaseConnection.PanelDatabaseConnection());
        frameMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameMain.setUndecorated(false);
        frameMain.setVisible(true);

    }
}
