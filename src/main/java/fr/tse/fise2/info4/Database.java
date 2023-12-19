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

import javax.xml.crypto.Data;

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



    public static Integer getAnIngredientID(String ingredientName) {
    	Integer id = null;
    	try (Connection connection = connect()) {
            if (connection != null) {
                // Request all users from table User
                String query = "SELECT ID FROM Ingredients WHERE ingredient = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	statement.setString(1, ingredientName);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();

                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        id = resultSet.getInt("ID");
                    }
                }
            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return id;
    }

	public static List<String> getAllUsers() {
        // Establish connection to db
        List<String> l_users = new ArrayList<>();
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
                        l_users.add(username);
                    }
                }
            } else {
                System.out.println("Failed to connect to db");
            }
            return l_users;
        } catch (SQLException e) {
            e.printStackTrace();
            return l_users;
        }
    }
	
	
	public static User getUser(String userName) {
        // Establish connection to db
		int id = 0;
		List<Allergen> l_allergies = new ArrayList<>();
		List<Integer> l_favoriteRecipes = new ArrayList<>();
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
                
                l_allergies = getAllergensbyUser(id);
                
                // Preping fridge of user
                query = "SELECT i.ingredient, f.EXPDATE, f.AMOUNT, u.UNIT_NAME FROM Fridge AS f JOIN Unit AS u ON f.UNITID = u.ID JOIN ingredients AS i ON f.INGREDIENTID=i.ID   WHERE USERID = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	statement.setInt(1, id);
                    // Execute request

                    ResultSet resultSet = statement.executeQuery();
                    
                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        String name = resultSet.getString("ingredient");
                        String expDate = resultSet.getString("EXPDATE");
                        Double amount = resultSet.getDouble("AMOUNT");
                        String unit = resultSet.getString("UNIT_NAME");
                        Ingredient t_ingredient = new Ingredient(expDate, name, amount, unit);
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
        User user = new User(id,userName, l_allergies, l_favoriteRecipes);
        user.setFridge(fridge);
		return user;
    }

    public static void addUser(String username) {
        // Establish connection to db
        try (Connection connection = connect()) {
            if (connection != null) {
                // add a user to table User
                String query = "INSERT INTO User (USERNAME) VALUES (?)";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	statement.setString(1, username);
                    // Execute request
                    statement.executeUpdate();
                }
            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddorUpdateIngredient(Integer userID, Ingredient ingredient){
        int id;
        Double amount = null;
        int unitID;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection != null){
                id = getAnIngredientID(ingredient.getName());
                String query = "SELECT AMOUNT FROM Fridge WHERE INGREDIENTID = ? AND USERID = ?";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, id);
                    statement.setInt(2, userID);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        amount = resultSet.getDouble("AMOUNT");
                    }
                }
                if(amount != null){
                    query = "UPDATE Fridge SET AMOUNT = ? WHERE INGREDIENTID = ? AND USERID = ?";
                    try(PreparedStatement statement = connection.prepareStatement(query)){
                        statement.setDouble(1, amount + ingredient.getQuantity());
                        statement.setInt(2, id);
                        statement.setInt(3, userID);
                        // Execute request
                        statement.executeUpdate();
                    }
                }
                else{
                    query = "INSERT INTO Fridge (INGREDIENTID, USERID, AMOUNT, UNITID, EXPDATE) VALUES (?, ?, ?, ?, ?)";
                    try(PreparedStatement statement = connection.prepareStatement(query)){
                        statement.setInt(1, id);
                        statement.setInt(2, userID);
                        statement.setDouble(3, ingredient.getQuantity());
                        statement.setInt(4, 1);
                        statement.setString(5, ingredient.getExpDate());
                        statement.executeUpdate();
                    }
                }
            }
            else{
                System.out.println("Failed to connect to db");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddAllergen(Integer userID, Allergen allergen){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = getAnAllergenID(allergen.getaName());
                String query = "INSERT INTO Allergens (USER_ID, ID_NAME) VALUES (?, ?)";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, userID);
                    statement.setInt(2, id);
                    // Execute request
                    statement.executeUpdate();
                }

            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Integer getAnAllergenID(String AllergenName) {
        Integer id = null;
        try (Connection connection = connect()) {
            if (connection != null) {
                // Request all users from table User
                String query = "SELECT ID FROM Allergen_Types WHERE NAME = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, AllergenName);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();

                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        id = resultSet.getInt("ID");
                    }
                }
            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return id;
    }

    public static void AddFavoriteRecipe(Integer userID, Recipe recipe){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = recipe.getId();
                String query = "INSERT INTO Favorite_Recipes (USER_ID, RECIPE_ID) VALUES (?, ?)";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, userID);
                    statement.setInt(2, id);
                    // Execute request
                    statement.executeUpdate();
                }

            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void RemoveFavoriteRecipe(Integer userID, Recipe recipe){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = recipe.getId();
                String query = "DELETE FROM Favorite_Recipes WHERE USER_ID = ? AND RECIPE_ID = ?";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, userID);
                    statement.setInt(2, id);
                    // Execute request
                    statement.executeUpdate();
                }

            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void RemoveAllergen(Integer userID, Allergen allergen){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = getAnAllergenID(allergen.getaName());
                String query = "DELETE FROM Allergens WHERE USER_ID = ? AND ID_NAME = ?";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, userID);
                    statement.setInt(2, id);
                    // Execute request
                    statement.executeUpdate();
                }

            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void SupressIngredient(Integer userID, Ingredient ingredient){
        int id;
        Double amount = null;
        // Establish connection to db
        try (Connection connection = connect()) {
            if (connection != null) {
                id = getAnIngredientID(ingredient.getName());
                String query = "SELECT AMOUNT FROM Fridge WHERE INGREDIENTID = ? AND USERID = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, id);
                    statement.setInt(2, userID);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        amount = resultSet.getDouble("AMOUNT");
                    }
                }

                if(amount > ingredient.getQuantity()) {
                	query = "UPDATE Fridge SET AMOUNT = ? WHERE INGREDIENTID = ? AND USERID = ?";

                	try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setDouble(1, amount - ingredient.getQuantity());
                        statement.setInt(2, id);
                        statement.setInt(3, userID);
                        // Execute request
                        statement.executeUpdate();
                    }
                }
                else if(amount != null){
                	query = "DELETE FROM Fridge WHERE INGREDIENTID = ? AND USERID = ?";

                	try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setInt(1, id);
                        statement.setInt(2, userID);
                        // Execute request
                        statement.executeUpdate();
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
            // Testing the getAllUsers test method
            List<String> users = Database.getAllUsers();
            for (String user : users) {
                System.out.println(user);
            }
            User Boris = Database.getUser("Boris");
            System.out.println(Boris.getName());
            List<Ingredient> l_ingredients = Boris.getFridge().getInventory();
            
            for(int i=0; i<l_ingredients.size(); i++) {
            	System.out.println(l_ingredients.get(i).getName());
            }

	}

    public static List<Allergen> getAllergensbyUser(Integer User_Id){
        List<Allergen> l_allergies = new ArrayList<>();
        try (Connection connection = connect()) {
            if (connection != null) {
                // Request all users from table User
                String query = "SELECT a.ID_NAME,at.NAME FROM Allergens as a  JOIN Allergen_Types as at  ON ID_NAME = at.ID WHERE USER_ID = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, User_Id);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();

                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID_NAME");
                        String name = resultSet.getString("NAME");
                        Allergen t_allergen = new Allergen(name);
                        l_allergies.add(t_allergen);
                    }
                }
            } else {
                System.out.println("Failed to connect to db");
            }
            return l_allergies;
        } catch (SQLException e) {
            e.printStackTrace();
            return l_allergies;
        }
    }

}
