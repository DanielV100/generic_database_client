package model;

import java.io.IOException;
import java.util.Properties;

/**
 * Properties of resources/generic_database_client_text.properties will be parsed here.
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

}
