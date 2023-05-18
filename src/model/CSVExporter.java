package model;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;

/**
 * Class for exporting data to csv.
 *
 * @author Daniel
 */
public class CSVExporter {
    /**
     * Method for exporting table to csv.
     *
     * @param table (as JTable)
     * @throws IOException
     * @author Daniel
     */
    public void exportToCSV(JTable table) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save CSV file");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath() + ".csv";
            FileWriter csvWriter = new FileWriter(filePath);
            //write the table header to the CSV file
            for (int i = 0; i < table.getColumnCount(); i++) {
                csvWriter.write(table.getColumnName(i));
                if (i < table.getColumnCount() - 1) {
                    csvWriter.write(";");
                }
            }
            csvWriter.write("\n");
            //write the table data to the CSV file
            for (int i = 0; i < table.getRowCount() - 1; i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Object cellValue = table.getValueAt(i, j);
                    String value = cellValue != null ? cellValue.toString() : "";
                    csvWriter.write(value);
                    if (j < table.getColumnCount() - 1) {
                        csvWriter.write(";");
                    }
                }
                csvWriter.write("\n");
            }
            csvWriter.close();
        }
    }
}
