package view;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class CredentialManager {

        public static void saveCredentials(UserCredentials credentials, String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String line = credentials.getHostname() + "," + credentials.getPortname() + "," + credentials.getDbname() + "," + credentials.getUsername() + "," + credentials.getPassword() + "," + credentials.getSelectedDB()+"\n";
                writer.write(line);
                writer.newLine(); // Eine neue Zeile hinzufügen, um den nächsten Eintrag von dem vorherigen zu trennen
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public static UserCredentials loadCredentials(String filePath, int index) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count == index) {
                    String[] tokens = line.split(",");
                    String hostname = tokens[0];
                    String portname = tokens[1];
                    String dbname = tokens[2];
                    String username = tokens[3];
                    String password = tokens[4];
                    String selecteddb = tokens[5];

                    return new UserCredentials(hostname, portname, dbname, username, password, selecteddb);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

