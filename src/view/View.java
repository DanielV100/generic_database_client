package view;

import view.UIHelpers;

import javax.swing.*;
import java.awt.*;
/*
This class builds the UI of the application's main frame - here shouldn't be any business logic.
Therefor Swing and AWT are used. Components such as frames, buttons and labels are created by calling methods from view.UIHelpers class.
 */
public class View {
    UIHelpers uiHelpers = new UIHelpers();
    private JFrame frameMain = new JFrame();
    private JButton buttonStart = new JButton();
    public View(){
        //creating the main window and adding a button to it
        UIHelpers.createJFrame(frameMain, "Generic database Client", 1200, 800, null, true)
                .add(UIHelpers.createJButton(buttonStart, 500, 350, 200, 100, "Hello ON22!", Color.darkGray));

    }

}
