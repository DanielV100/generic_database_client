package view;
import controller.Controller;
import resources.Sizes;
import javax.swing.*;
import java.io.IOException;


/**
 *This class builds the UI of the application's start-screen.
 *Therefore, Swing and AWT are used. Components such as frames, buttons and labels are created by calling methods from view.UIHelpers class.
 * @see UIHelpers
 * @author Lorenz
 */
public class View {
    Controller controller = new Controller();
    Sizes sizes = new Sizes();
    PanelDatabaseConnection panelDatabaseConnection = new PanelDatabaseConnection();
    public JFrame frameMain = new JFrame();

    /**
     * Initializing the start-screen ("frameMain")
     * @throws IOException
     * @author Lorenz
     */
    public View() throws IOException {
        frameMain = UIHelpers.createJFrame(frameMain, controller.getAppPropertiesWithKey("title.view.frameMain"), sizes.getFrame_view_mainFrame_frameWidth(), sizes.getFrame_view_mainFrame_frameHeight(), null, true);
        frameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameMain.add(panelDatabaseConnection.PanelDatabaseConnection());
        frameMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
