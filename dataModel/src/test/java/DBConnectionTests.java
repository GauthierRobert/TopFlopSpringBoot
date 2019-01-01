import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTests {

    @Test
    public void ConnectionTest(){
        String databaseURL = "jdbc:mysql://topflopdatabaseinstance.cvzudmjp9i63.eu-west-3.rds.amazonaws.com:3306/topflop";
        String user = "gauthier";
        String password = "Aldebaran63!";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
