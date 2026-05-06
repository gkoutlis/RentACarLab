package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConfig {
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Σφάλμα ανάγνωσης config: " + e.getMessage());
        }
    }

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Σύνδεση με τη βάση επιτυχής!");
            return conn;
        } catch (Exception e) {
            System.out.println("Σφάλμα σύνδεσης: " + e.getMessage());
            return null;
        }
    }
}