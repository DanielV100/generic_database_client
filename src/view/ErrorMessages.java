package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ErrorMessages {
    public void showErrorMessage(Exception ex) {
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
