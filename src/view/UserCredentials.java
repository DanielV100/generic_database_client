package view;
/**
 * Saving and loading User Credentials.
 *
 * @author Lorenz, Luca, Marius
 */
public class UserCredentials {
    private String hostname;
    private String portname;
    private String dbname;
    private String username;
    private String password;
    private String selectedDB;
    private String speichern;

    /**
     * Getting and setting User Credentials
     * @author Lorenz, Marius, Luca
     */
    public UserCredentials(String hostname, String portname, String dbname, String username, String password, String selcetedDB,String speichern) {
        this.hostname = hostname;
        this.portname = portname;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        this.selectedDB = selcetedDB;
        this.speichern = speichern;
    }

    public String getHostname() {
        return hostname;
    }
    public String getPortname() {
        return portname;
    }
    public String getDbname() {
        return dbname;
    }
    public String getUsername() {
        return username;
    }
    public String getSelectedDB() {
        return selectedDB;
    }
    public String getPassword() {
        return password;
    }
    public String getspeichern() {
        return speichern;
    }
}
