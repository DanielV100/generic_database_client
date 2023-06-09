package controller;

import model.CSVExporter;
import model.ResourcesGetter;

import javax.swing.*;
import java.io.IOException;

/**
 * This controller class  is the communicator between view and DBWorker. So the view never interacts with the DBWorker directly.
 *
 * @author Luca, Daniel
 */
public class Controller {
    CSVExporter csvExporter = new CSVExporter();
    ResourcesGetter resourcesGetter = new ResourcesGetter();

    /**
     * Getting text from properties file (key of property is needed!)
     *
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
     *
     * @param path
     * @return ImageIcon
     * @throws IOException
     * @author Daniel
     */
    public ImageIcon getImageIconFromResources(String path) throws IOException {
        return resourcesGetter.getImageIconFromResources(path);
    }

    /**
     * Method for exporting table - communication between model and view.
     *
     * @param table
     * @throws IOException
     * @author Daniel
     * @see CSVExporter
     */
    public void exportToCSV(JTable table) throws IOException {
        csvExporter.exportToCSV(table);
    }
}
