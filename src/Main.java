import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ξεκινάει ο έλεγχος...");
        Connection conn = DBConfing.connect();

        if (conn != null){
            System.out.println("Συγχαρητήρια !!! Η JAVA μιλάει με την POSTGRESQL.0");
        } else{
            System.out.println("Κάτι πήγε στραβά. Τσέκαρε τον Driver ή το Password.");
        }
    }
}
