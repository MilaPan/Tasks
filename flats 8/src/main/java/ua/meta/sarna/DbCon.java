package ua.meta.sarna;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbCon {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public DbCon() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbUrl = properties.getProperty("db.url");
        dbUser = properties.getProperty("db.user");
        dbPassword = properties.getProperty("db.password");

    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
