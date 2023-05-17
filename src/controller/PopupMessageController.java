package controller;

import view.PopupMessages;

/**
 * Class for calling PopupMessages class from view and getting error/success popups (--> MVC).
 * @see PopupMessages
 * @author Daniel
 */
public class PopupMessageController {
    PopupMessages popupMessages = new PopupMessages();
    public void showErrorMessage(Exception ex) {
        popupMessages.showErrorMessage(ex);
    }
    public void showSuccessMessage(String message) {
        popupMessages.showSuccessMessage(message);
    }
}
