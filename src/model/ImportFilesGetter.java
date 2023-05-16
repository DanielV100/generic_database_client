package model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class ImportFilesGetter {
    String csvFile = "";


//Importer Einfügen --> Ein Fenster mit großem Textfeld, was ; separierte Zeilen importiert
    public List<List<String>> getColumnsAndRowsFromCSV() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose CSV file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV files", "csv");
        fileChooser.setFileFilter(filter);
        //fileChooser.setAcceptAllFileFilterUsed(false);
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
