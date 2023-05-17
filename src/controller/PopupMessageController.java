package controller;

import view.PopupMessages;

/**
 * Class for calling PopupMessages class from view and getting error/success popups (--> MVC).
 * @see PopupMessages
 * @author Daniel
 */
public class PopupMessageController {
    PopupMessages popupMessages = new PopupMessages();

    /**
     * @see PopupMessages
     * @param ex
     * @author Daniel
     */
    public void showErrorMessage(Exception ex) {
        popupMessages.showErrorMessage(ex);
    }

    /**
     * @see PopupMessages
     * @param message
     * @author Daniel
     */
    public void showSuccessMessage(String message) {
        popupMessages.showSuccessMessage(message);
    }
}
