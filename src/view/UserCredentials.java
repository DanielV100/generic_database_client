package view;
public class UserCredentials {
    private String hostname;
    private String portname;
    private String dbname;
    private String username;
    private String password;

    public UserCredentials(String hostname, String portname, String dbname, String username, String password) {
        this.hostname = hostname;
        this.portname = portname;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
