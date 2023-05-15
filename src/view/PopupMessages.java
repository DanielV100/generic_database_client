package view;

import javax.swing.*;
import java.awt.*;

public class PopupMessages {
    Color successPopup = Color.GREEN.darker();
    Color errorPopup = Color.RED.darker();
    Dimension popupDimension = new Dimension(200, 100);
    public void showErrorMessage(Exception ex) {
        String errorMessage = "Something went wrong: \n" + ex.getMessage();
        //Message when username or password is wrong 
        if(ex.getMessage().contains("Access denied")) {
            errorMessage += "\nUsername or Password is wrong.";
        }
        //Message when localhost or port is wrong
        else if (ex.getMessage().contains("Failed to parse the host") || ex.getMessage().contains("Communications link failure")) {
            errorMessage += "\nThis could be 3 reaseons: (1) Hostname and/or (2) port is wrong or server isn't running.";
        }
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", errorPopup);
        UI.put("Panel.background", errorPopup);
        JOptionPane.showMessageDialog(null, errorMessage, "Error occurred", JOptionPane.ERROR_MESSAGE);
        resetUIColors();
    }
    public void showSuccessMessage(String message) {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", successPopup);
        UI.put("Panel.background", successPopup);
        JOptionPane.showMessageDialog(null, message, "Everything is alright", JOptionPane.INFORMATION_MESSAGE);
        resetUIColors();
    }
    private void resetUIColors() {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.WHITE);
        UI.put("Panel.background", Color.WHITE);
    }
}
