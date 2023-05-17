package view;

import resources.Colors;
import javax.swing.*;
import java.awt.*;

/**
 * Class for central popup messages handling (as JOptionPanes).
 *
 * @author Daniel
 */
public class PopupMessages {
    Colors colors = new Colors();
    UIManager UI = new UIManager();

    /**
     * Method is used for creating error messages.
     *
     * @param ex (exception as type Exception)
     * @author Daniel
     */
    public void showErrorMessage(Exception ex) {
        //initial error message
        String errorMessage = "Something went wrong: \n" + ex.getMessage();
        //message when username or password is wrong
        if (ex.getMessage().contains("Access denied")) {
            errorMessage += "\nUsername or Password is wrong.";
        }
        //Message when localhost or port is wrong
        else if (ex.getMessage().contains("Failed to parse the host") || ex.getMessage().contains("Communications link failure")) {
            errorMessage += "\nThis could be 3 reasons: (1) Hostname and/or (2) port is wrong or server isn't running.";
        }
        setUI(colors.getPopupMessageError());
        JOptionPane.showMessageDialog(null, errorMessage, "Error occurred", JOptionPane.ERROR_MESSAGE);
        setUI(colors.getUiStandard());
        System.out.println("Showed error message");
    }

    /**
     * Method is used for creating success messages.
     *
     * @param message (as String)
     * @author Daniel
     */
    public void showSuccessMessage(String message) {
        setUI(colors.getPopupMessageSuccess());
        JOptionPane.showMessageDialog(null, message, "Everything is alright", JOptionPane.INFORMATION_MESSAGE);
        setUI(colors.getUiStandard());
        System.out.println("Showed success message");
    }

    /**
     * Method is used for setting color of the message panes.
     *
     * @param color (as type Color)
     * @author Daniel
     * @see Colors
     */
    private void setUI(Color color) {
        UI.put("OptionPane.background", color);
        UI.put("Panel.background", color);
    }
}
