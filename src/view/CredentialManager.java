package view;
import controller.PopupMessageController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Saving and loading User Credentials.
 *
 * @author Daniel, Lorenz, Luca, Valentin, Marius
 */
public class CredentialManager {
    PopupMessageController popupMessageController = new PopupMessageController();
        /**
        * Saving User Credentials in /Credentials/credentials.txt
        *
        * @author Lorenz
        */
        public void saveCredentials(UserCredentials credentials, String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String line = credentials.getHostname() + "," + credentials.getPortname() + "," + credentials.getDbname() + "," + credentials.getUsername() + "," + credentials.getPassword() + "," + credentials.getSelectedDB()+ ","+ credentials.getspeichern()+"\n";
                writer.write(line);
                writer.newLine(); // Eine neue Zeile hinzufügen, um den nächsten Eintrag von dem vorherigen zu trennen
            } catch (IOException e) {
                popupMessageController.showErrorMessage(e);
            }
        }
    /**
     * Loading User Credentials as List from /Credentials/credentials.txt
     * @return credentialsList
     * @author Lorenz
     */
    public List<UserCredentials> loadCredentials(String filePath) {
        List<UserCredentials> credentialsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 7) {
                    String hostname = tokens[0];
                    String portname = tokens[1];
                    String dbname   = tokens[2];
                    String username = tokens[3];
                    String password = tokens[4];
                    String selecteddb = tokens[5];
                    String speichern = tokens [6];

                    UserCredentials credentials = new UserCredentials(hostname, portname, dbname, username, password, selecteddb, speichern);
                    credentialsList.add(credentials);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            popupMessageController.showErrorMessage(e);
        }
        return credentialsList;
    }

}

