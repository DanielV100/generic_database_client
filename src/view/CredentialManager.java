package view;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class CredentialManager {
    static ErrorMessages errorMessages = new ErrorMessages();

        public static void saveCredentials(UserCredentials credentials, String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String line = credentials.getHostname() + "," + credentials.getPortname() + "," + credentials.getDbname() + "," + credentials.getUsername() + "," + credentials.getPassword() + "," + credentials.getSelectedDB()+"\n";
                writer.write(line);
                writer.newLine(); // Eine neue Zeile hinzufügen, um den nächsten Eintrag von dem vorherigen zu trennen
            } catch (IOException e) {
                errorMessages.showErrorMessage(e);
            }
        }
    public static List<UserCredentials> loadCredentials(String filePath) {
        List<UserCredentials> credentialsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 6) {
                    String hostname = tokens[0];
                    String portname = tokens[1];
                    String dbname   = tokens[2];
                    String username = tokens[3];
                    String password = tokens[4];
                    String selecteddb = tokens[5];

                    UserCredentials credentials = new UserCredentials(hostname, portname, dbname, username, password, selecteddb);
                    credentialsList.add(credentials);
                } else {
                    // Handle invalid line
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            errorMessages.showErrorMessage(e);
        }
        return credentialsList;
    }

}

