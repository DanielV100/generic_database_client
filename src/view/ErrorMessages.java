package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ErrorMessages {
    File file = new File(getClass().getClassLoader().getResource("resources/gdc_logo.png").getFile());
    String filePath = file.getAbsolutePath();
    ImageIcon backgroundIcon = new ImageIcon(filePath);
    Image img = backgroundIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);


    public void showErrorMessage(Exception ex) {
        backgroundIcon = new ImageIcon(img);
        String errorMessage = "Something went wrong: \n" + ex.getMessage();
        //Message when username or password is wrong 
        if(ex.getMessage().contains("Access denied")) {
            errorMessage += "\nUsername or Password is wrong.";
        }
        //Message when localhost or port is wrong
        else if (ex.getMessage().contains("Failed to parse the host") || ex.getMessage().contains("Communications link failure")) {
            errorMessage += "\nHostname or port is wrong.";
        }
        JOptionPane.showMessageDialog(null, errorMessage, "Error occurred", JOptionPane.ERROR_MESSAGE);
    }
}
