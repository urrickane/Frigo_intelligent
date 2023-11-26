package fr.tse.fise2.info4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	//URL to connect to db
	private static final String URL = "jdbc:sqlite:src/main/resources/data.db";
	
	//
	public static Connection connect() {
        try {
            // Loading the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Creating the connection to the db
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            // Handling exceptions for connection exceptions and not found exceptions 
            e.printStackTrace(); 
            return null; // Null if failed to connect
        }
    }

	public static void getAllUsers() {
        // Establish connection to db
        try (Connection connection = connect()) {
            if (connection != null) {
                // Request all users from table User
                String query = "SELECT ID, USERNAME FROM User";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();

                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String username = resultSet.getString("USERNAME");

                        System.out.println("ID: " + id + ", Username: " + username);
                    }
                }
            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	// Main not here to stay, meant for testing purposes only !
	public static void main(String[] args) {
		// Establishing connection to db
        Connection connection = Database.connect();

        // Verifying if it worked
        if (connection != null) {
            System.out.println("Successfully connected to db");
            
            // Testing the getAllUsers test method
            Database.getAllUsers();
            
            // Closing the connection after using it
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to db");
        }
	}

}
