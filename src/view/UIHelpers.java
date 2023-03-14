package view;

import javax.swing.*;
import java.awt.*;

public class UIHelpers {
    public UIHelpers() {
    }
    //Frames can be created by calling this method
    protected static JFrame createJFrame(JFrame frame, String frameTitle, int frameWidth, int frameHeight, LayoutManager frameLayout, boolean frameVisibility){
        frame = new JFrame(frameTitle);
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(frameLayout);
        frame.setVisible(frameVisibility);
        return frame;
    }

    //Buttons can be created by calling this method
    protected static JButton createJButton(JButton button, int buttonX, int buttonY, int buttonWidth, int buttonHeight, String buttonLabel, Color buttonColor){
        button = new JButton(buttonLabel);
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        button.setBackground(buttonColor);
        return button;
    }
}
