import model.ImportFilesGetter;
import view.View;

import java.io.IOException;

/*
Here is just the start of the application - no more or less!
 */
public class Main {
    public static void main(String[] args) throws IOException {
      // View view = new View();
        ImportFilesGetter importFilesGetter = new ImportFilesGetter();
        importFilesGetter.getRowsFromCSV();
    }
}