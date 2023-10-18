package ma.youcode.easybank.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.enterprise.inject.Produces;

public class DataBaseAccessLayer {
    
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/easybank";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    private static Connection connection;

    private DataBaseAccessLayer() {}

    @Produces
    public static Connection getConnection()  {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                } catch (ClassNotFoundException e) {
                    throw new SQLException("Database driver not found.", e);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection()  {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
                e.printStackTrace(); 
        }
        
    }
}