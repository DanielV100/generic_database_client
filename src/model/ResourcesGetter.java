package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//Properties of generic_database_client_text.properties will be parsed here
public class ResourcesGetter {
    Properties appProperties = new Properties();
    public Properties getAppProperties() throws IOException {
        appProperties.load(getClass().getClassLoader().getResourceAsStream("resources/generic_database_client_text.properties"));
        return appProperties;
    }
    public String getAppPropertiesWithKey(String key) throws IOException {
        return getAppProperties().getProperty(key);
    }

}
