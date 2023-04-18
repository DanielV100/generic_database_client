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
    private JFrame frameMain = new JFrame();


    //creating the main window with its panels on it
    public View() throws IOException {


        //initializing main frame
        frameMain = UIHelpers.createJFrame(frameMain, controller.getAppPropertiesWithKey("title.view.frameMain"), sizes.getFrame_view_mainFrame_frameWidth(), sizes.getFrame_view_mainFrame_frameHeight(), null, true);
        frameMain.add(panelDatabaseConnection.PanelDatabaseConnection());

    }
}
