package controller;

import model.ResourcesGetter;

import java.io.IOException;

public class Controller {
    ResourcesGetter resourcesGetter = new ResourcesGetter();
    public String getAppPropertiesWithKey(String key) throws IOException {
        return resourcesGetter.getAppPropertiesWithKey(key);
    }
}
