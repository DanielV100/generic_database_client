package controller;

import model.ResourcesGetter;
import java.io.IOException;

/**
 * This controller class  is the communicator between view and model. So the view never interacts with the model directly.
 * @author Luca
 */
public class Controller {
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
}
