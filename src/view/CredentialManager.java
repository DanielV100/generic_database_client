package view;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class CredentialManager {

    public static void saveCredentials(UserCredentials credentials, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(credentials.getHostname() + "," + credentials.getPortname() + "," + credentials.getDbname() + "," + credentials.getUsername() + "," + credentials.getPassword());
            //System.out.println("Credentials saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static UserCredentials loadCredentials(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                String[] tokens = line.split(",");
                String hostname = tokens[0];
                String portname = tokens[1];
                String dbname   = tokens[2];
                String username = tokens[3];
                String password = tokens[4];
                return new UserCredentials(hostname, portname, dbname, username, password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

