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
    String csvFile = "/Users/daniel/Desktop/generic_database_client/generic_database_client/src/resources/sample.csv";
    BufferedReader bufferedReader = null;
    String firstLine = "";

    public List<String> getColumnsFromCSV() throws IOException {
        List<String> columnsFromCSV = new ArrayList<>();
        bufferedReader = new BufferedReader(new FileReader(csvFile));
        firstLine = bufferedReader.readLine(); // read the first line
        // parse the line using a StringTokenizer
        StringTokenizer st = new StringTokenizer(firstLine, ";");
        while (st.hasMoreTokens()) {
            columnsFromCSV.add(st.nextToken());
        }
        return columnsFromCSV;
    }
    public List<List<String>> getRowsFromCSV() throws IOException {
        List<List<String>> rowsFromCSV = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                rowsFromCSV.add(Arrays.asList(values));
            }
        }
            System.out.println(rowsFromCSV.size());
        return rowsFromCSV;
    }
}
