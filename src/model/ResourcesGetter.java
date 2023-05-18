package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Getting all types of (external) resources here. Properties of resources/generic_database_client_text.properties will be parsed here too.
 * @author Daniel
 */
public class ResourcesGetter {
    Properties appProperties = new Properties();

    /**
     * Loading all properties from resources/generic_database_client_text.properties and return it
     * @return Properties (from resources/generic_database_client_text.properties)
     * @throws IOException
     * @author Daniel
     */
    public Properties getAppProperties() throws IOException {
        //stream is needed here (otherwise it wouldn't work in the .jar
        appProperties.load(getClass().getClassLoader().getResourceAsStream("resources/generic_database_client_text.properties"));
        return appProperties;
    }

    /**
     * Getting properties calling method getAppProperties() and get particular property (key is needed)
     * @param key (of the property)
     * @return Property value as String
     * @throws IOException
     * @author Daniel
     */
    public String getAppPropertiesWithKey(String key) throws IOException {
        return getAppProperties().getProperty(key);
    }
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


    public List<List<String>> getColumnsAndRowsFromCSV() throws IOException {
        String csvFile = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose CSV file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV files", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            csvFile = fileChooser.getSelectedFile().getPath();
            List<List<String>> rowsFromCSV = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line.replace('"', ' ');
                    String[] values = line.split(";");
                    System.out.println(line);
                    rowsFromCSV.add(Arrays.asList(values));
                }
            }
            return rowsFromCSV;
            // do something with the file
        } else {
            System.out.println("No file selected.");
        }
        return null;
    }
}
