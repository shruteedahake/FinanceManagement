package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection(String connectionString) {
        Connection connection = null;
        try {
//            connection = DriverManager.getConnection(connectionString);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fin_mng","root","shrutee@123");            
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
        return connection;
    }
}