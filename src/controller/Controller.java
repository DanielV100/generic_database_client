package controller;

import model.ResourcesGetter;

import java.io.IOException;

public class Controller {
    ResourcesGetter resourcesGetter = new ResourcesGetter();
    //getting strings out of generic_database_client_text.properties - key is needed
    public String getAppPropertiesWithKey(String key) throws IOException {
        return resourcesGetter.getAppPropertiesWithKey(key);
    }
}
