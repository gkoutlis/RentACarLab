import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfing {
    private static final String URL = "jdbc:postgresql://localhost:5432/rentacarlab";
    private static final String USER  = "rentacarlab";
    private static final String PASSWORD = "rentacarlab";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Σύνδεση με τη βάση επιτυχής!!!");
            return conn;
        } catch (Exception e){
            System.out.println("Σφάλμα σύνδεσης: " + e.getMessage());
            return null;
        }
    }
}