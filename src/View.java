import javax.swing.*;

public class View {
    JFrame f = new JFrame();
    View(){
        createMainWindow(f);
    }
    private JFrame createMainWindow(JFrame mainWindow){
        mainWindow = new JFrame();
        JButton buttonStart = new JButton();
        mainWindow.add(createJButton(buttonStart, 130, 100, 100, 40, "Hello ON22!"));
        mainWindow.setSize(400, 500);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
        return mainWindow;
    }
    private JButton createJButton(JButton button, int buttonX, int buttonY, int buttonWidth, int buttonHeight, String buttonLabel){
        button = new JButton(buttonLabel);
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        return button;
    }
}
