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
    UIHelpers uiHelpers = new UIHelpers();
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    private JFrame frameMain = new JFrame();
    private JPanel panelMain = new JPanel();
    private JButton buttonStart = new JButton();
    private JLabel labelGreeting = new JLabel();

    //creating the main window with its components
    public View() throws IOException {
        //initializing components which later will be on the panel
        buttonStart = UIHelpers.createJButton(buttonStart, sizes.getButton_view_buttonStart_buttonX(), sizes.getButton_view_buttonStart_buttonY(), sizes.getButton_view_buttonStart_buttonWidth(), sizes.getButton_view_buttonStart_buttonHeight(), controller.getAppPropertiesWithKey("button.view.buttonStart"), Color.darkGray);
        labelGreeting = UIHelpers.createJLabel(labelGreeting, controller.getAppPropertiesWithKey("label.view.labelGreeting"), sizes.getLabel_view_labelGreeting_labelX(), sizes.getLabel_view_labelGreeting_labelY(), sizes.getLabel_view_labelGreeting_labelWidth(), sizes.getLabel_view_labelGreeting_labelHeight());
        //initializing main panel which later will be on the main frame
        panelMain = UIHelpers.createJPanel(panelMain, sizes.getPanel_view_panelMain_panelX(), sizes.getPanel_view_panelMain_panelY(), sizes.getPanel_view_panelMain_panelWidth(), sizes.getPanel_view_panelMain_panelHeight(), null);
        panelMain.add(buttonStart);
        panelMain.add(labelGreeting);
        //initializing main frame
        frameMain = UIHelpers.createJFrame(frameMain, controller.getAppPropertiesWithKey("title.view.frameMain"), sizes.getFrame_view_mainFrame_frameWidth(), sizes.getFrame_view_mainFrame_frameHeight(), null, true);
        frameMain.add(panelMain);

    }
}
