package fr.tse.fise2.info4;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.User;
import Classes.Allergen;
import Classes.Fridge;
import Classes.Ingredient;
import Classes.Recipe;

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
	
	
	public static User getUser(String userName) {
        // Establish connection to db
		int id;
		List<Allergen> l_allergies = new ArrayList<>();
		List<Recipe> l_favoriteRecipes = new ArrayList<>();
		List<Ingredient> l_ingredients = new ArrayList<>();
		
        try (Connection connection = connect()) {
            if (connection != null) {
            	
                // Finding id of user
                String query = "SELECT ID FROM User WHERE USERNAME = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	statement.setString(1, userName);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();
                    
                    id = resultSet.getInt("ID");
                    // Go through the results and display them on console
                }
                
                //Preping all allergens of user
                query = "SELECT NAME FROM Allergen WHERE USER_ID = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	statement.setInt(1, id);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();
                    
                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        String name = resultSet.getString("NAME");
                        Allergen t_allergen = new Allergen(name);
                        l_allergies.add(t_allergen);
                    }
                }
                
                // Preping fridge of user
                query = "SELECT i.NAME, i.EXPDATE, i.AMOUNT, u.UNIT_NAME FROM Ingredients AS i JOIN Unit AS u ON i.UNITID = u.ID  WHERE USERID = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	statement.setInt(1, id);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();
                    
                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        String name = resultSet.getString("NAME");
                        String expDate = resultSet.getString("EXPDATE");
                        Ingredient t_ingredient = new Ingredient(expDate, name, false);
                        l_ingredients.add(t_ingredient);
                    }

                }
            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Fridge fridge = new Fridge(l_ingredients);
        User user = new User(userName, l_allergies, l_favoriteRecipes);
        user.setFridge(fridge);
		return user;
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
            Database.getUser("Boris");
            User Boris = Database.getUser("Boris");
            System.out.println(Boris.getName());
            List<Ingredient> l_ingredients = Boris.getFridge().getInventory();
            
            for(int i=0; i<l_ingredients.size(); i++) {
            	System.out.println(l_ingredients.get(i).getName());
            }
            
            
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
