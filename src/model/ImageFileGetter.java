package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for getting all types of images.
 * @author Daniel
 */
public class ImageFileGetter {
    /**
     * Method for getting image icon. Therefore, a imageStream is opened.
     * @param path (to image)
     * @return ImageIcon
     * @throws IOException
     * @author Daniel
     */
    public ImageIcon getImageIconFromResources(String path) throws IOException {
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(path);
        ImageIcon imageIcon = new ImageIcon(ImageIO.read(imageStream));
        return imageIcon;
    }
}
