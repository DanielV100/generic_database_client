package view;

import javax.swing.*;
import java.awt.*;
/*
This class builds the UI of the application's main frame - here shouldn't be any business logic.
Therefor Swing and AWT are used. Components such as frames, buttons and labels are created by calling methods from view.UIHelpers class.
 */
public class View {
    UIHelpers uiHelpers = new UIHelpers();
    private JFrame frameMain = new JFrame();
    private JPanel panelMain = new JPanel();
    private JButton buttonStart = new JButton();
    private JLabel labelGreeting = new JLabel();

    //creating the main window with it's components
    public View(){
        //initializing components which later will be on the panel
        buttonStart = UIHelpers.createJButton(buttonStart, 500, 350, 200, 100, "Hello ON22!", Color.darkGray);
        labelGreeting = UIHelpers.createJLabel(labelGreeting, "Hello World!", 40, 120, 200, 80);
        //initializing main panel which later will be on the main frame
        panelMain = UIHelpers.createJPanel(panelMain, 0, 0, 1200, 800, null);
        panelMain.add(buttonStart);
        panelMain.add(labelGreeting);
        //initializing main frame
        frameMain = UIHelpers.createJFrame(frameMain, "Generic database Client", 1200, 800, null, true);
        frameMain.add(panelMain);

    }
}
