package controller;

import model.ImageFileGetter;
import model.ResourcesGetter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * This controller class  is the communicator between view and model. So the view never interacts with the model directly.
 * @author Luca
 */
public class Controller {
    ImageFileGetter imageFileGetter = new ImageFileGetter();
    ResourcesGetter resourcesGetter = new ResourcesGetter();

    /**
     * Getting text from properties file (key of property is needed!)
     * @param key (from key value pair in properties file)
     * @return String from properties file
     * @throws IOException
     * @author Luca
     */
    public String getAppPropertiesWithKey(String key) throws IOException {
        return resourcesGetter.getAppPropertiesWithKey(key);
    }

    /**
     * Getting image icon from path.
     * @param path
     * @return ImageIcon
     * @throws IOException
     * @author Daniel
     */
    public ImageIcon getImageIconFromResources(String path) throws IOException {
        return imageFileGetter.getImageIconFromResources(path);
    }
}
