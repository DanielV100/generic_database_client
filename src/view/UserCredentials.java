package view;
public class UserCredentials {
    private String hostname;
    private String portname;
    private String dbname;
    private String username;
    private String password;
    private String selectedDB;

    public UserCredentials(String hostname, String portname, String dbname, String username, String password, String selcetedDB) {
        this.hostname = hostname;
        this.portname = portname;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        this.selectedDB = selcetedDB;
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
}
