package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ResourcesGetter {
    String pathToResourceFile = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "resources/generic_database_client_text.properties";
    Properties appProperties = new Properties();
    public Properties getAppProperties() throws IOException {
        appProperties.load(new FileInputStream(pathToResourceFile));
        return appProperties;
    }
    public String getAppPropertiesWithKey(String key) throws IOException {
        return getAppProperties().getProperty(key);
    }

}
