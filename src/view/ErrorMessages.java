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
        JOptionPane.showMessageDialog(null, errorMessage, "Error occurred", JOptionPane.ERROR_MESSAGE);
    }
}
