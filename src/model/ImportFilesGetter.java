package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class ImportFilesGetter {
    String csvFile = "/Users/daniel/Desktop/generic_database_client/generic_database_client/src/resources/samp.csv";

Importer Einfügen --> Ein Fenster mit großem Textfeld, was ; separierte Zeilen importiert
    public List<List<String>> getColumnsAndRowsFromCSV() throws IOException {
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
    }
}
