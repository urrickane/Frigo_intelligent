package fr.tse.fise2.info4.Database;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.tse.fise2.info4.Classes.*;

public class Database {
	//URL to connect to db
	private static final String URL = "jdbc:sqlite:src/main/resources/data.db";
	
	// use to connect to db
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




// return a list with all usernames
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
	
	// return a User with his username
	public static User getUser(String userName) {
        // Establish connection to db
		int id = 0;
		List<Allergen> l_allergies = new ArrayList<>();
		List<Integer> l_favoriteRecipes = new ArrayList<>();
		List<Ingredient> l_ingredients = new ArrayList<>();
        ShoppingList shoppingList = new ShoppingList();
		
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
                l_favoriteRecipes = getFavRecipesbyUser(id);
                shoppingList = getShoppingListbyUser(id);
                
                // Preping fridge of user
                query = "SELECT i.ingredient_name, f.EXPDATE, f.AMOUNT, u.UNIT_NAME FROM Fridge AS f JOIN Unit AS u ON f.UNIT_ID = u.ID JOIN Ingredients AS i ON f.INGREDIENT_ID=i.ID   WHERE USER_ID = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                	statement.setInt(1, id);
                    // Execute request

                    ResultSet resultSet = statement.executeQuery();
                    
                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        String name = resultSet.getString("ingredient_name");
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
        User user = new User(id,userName, l_allergies, l_favoriteRecipes, shoppingList);
        user.setFridge(fridge);
		return user;
    }

    // add an user
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

    // give an ingredient id from name
    public static Integer getAnIngredientID(String ingredientName) {
        Integer id = null;
        try (Connection connection = connect()) {
            if (connection != null) {
                // Request all users from table User
                String query = "SELECT ID FROM Ingredients WHERE ingredient_name = ?";

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

    // Add or update quantity of an element
    public static void AddorUpdateIngredient(Integer userID, Ingredient ingredient){
        int id;
        Double amount = null;
        int unitID;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection != null){
                id = getAnIngredientID(ingredient.getName());
                String query = "SELECT AMOUNT FROM Fridge WHERE INGREDIENT_ID = ? AND USER_ID = ?";
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
                    query = "UPDATE Fridge SET AMOUNT = ? WHERE INGREDIENT_ID = ? AND USER_ID = ?";
                    try(PreparedStatement statement = connection.prepareStatement(query)){
                        statement.setDouble(1, amount + ingredient.getQuantity());
                        statement.setInt(2, id);
                        statement.setInt(3, userID);
                        // Execute request
                        statement.executeUpdate();
                    }
                }
                else{
                    query = "INSERT INTO Fridge (INGREDIENT_ID, USER_ID, AMOUNT, UNIT_ID, EXPDATE) VALUES (?, ?, ?, ?, ?)";
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

    // suppres an ingredient from the fridge
    public static void SupressIngredient(Integer userID, Ingredient ingredient){
        int id;
        Double amount = null;
        // Establish connection to db
        try (Connection connection = connect()) {
            if (connection != null) {
                id = getAnIngredientID(ingredient.getName());
                System.out.println(id);
                String query = "SELECT AMOUNT FROM Fridge WHERE INGREDIENT_ID = ? AND USER_ID = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, id);
                    statement.setInt(2, userID);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        amount = resultSet.getDouble("AMOUNT");
                    }
                }
                if(amount != null){
                    if(amount > ingredient.getQuantity()) {
                        query = "UPDATE Fridge SET AMOUNT = ? WHERE INGREDIENT_ID = ? AND USER_ID = ?";

                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setDouble(1, amount - ingredient.getQuantity());
                            statement.setInt(2, id);
                            statement.setInt(3, userID);
                            // Execute request
                            statement.executeUpdate();
                        }
                }
                else{
                    query = "DELETE FROM Fridge WHERE INGREDIENT_ID = ? AND USER_ID = ?";

                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setInt(1, id);
                        statement.setInt(2, userID);
                        // Execute request
                        statement.executeUpdate();
                    }
                }}
            } else {
                System.out.println("Failed to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // modify an ingredient from the fridge
    public static void ModifyIngredient(Integer userID, Ingredient ingredient, double newAmount){
        int id;
        Double amount = null;
        // Establish connection to db
        try (Connection connection = connect()) {
            if (connection != null) {
                id = getAnIngredientID(ingredient.getName());
                System.out.println(id);
                String query = "SELECT AMOUNT FROM Fridge WHERE INGREDIENT_ID = ? AND USER_ID = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, id);
                    statement.setInt(2, userID);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        amount = resultSet.getDouble("AMOUNT");
                    }
                }
                if(amount != null){
                    query = "UPDATE Fridge SET AMOUNT = ? WHERE INGREDIENT_ID = ? AND USER_ID = ?";

                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setDouble(1, newAmount);
                        statement.setInt(2, id);
                        statement.setInt(3, userID);
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

    //add an allergen
    public static void AddAllergen(Integer userID, Allergen allergen){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = getAnAllergenID(allergen.getaName());
                String query = "INSERT INTO Allergens (USER_ID, NAME_ID) VALUES (?, ?)";
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

    //remove an allergen
    public static void RemoveAllergen(Integer userID, Allergen allergen){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = getAnAllergenID(allergen.getaName());
                String query = "DELETE FROM Allergens WHERE USER_ID = ? AND NAME_ID = ?";
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

    // get the id of an allergen type
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

    // add a fav recipe
    public static void AddFavoriteRecipe(Integer userID, Integer recipe_id){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = recipe_id;
                String query = "INSERT INTO Favorites (USER_ID, RECIPE_ID) VALUES (?, ?)";
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

    // suppres a fav recipe
    public static void RemoveFavoriteRecipe(Integer userID, Integer recipe_id){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = recipe_id;
                String query = "DELETE FROM Favorites WHERE USER_ID = ? AND RECIPE_ID = ?";
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




    // get the allergens of an user
    public static List<Allergen> getAllergensbyUser(Integer User_Id){
        List<Allergen> l_allergies = new ArrayList<>();
        try (Connection connection = connect()) {
            if (connection != null) {
                // Request all users from table User
                String query = "SELECT a.NAME_ID,at.NAME FROM Allergens as a  JOIN Allergen_Types as at  ON NAME_ID = at.ID WHERE USER_ID = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, User_Id);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();

                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        int id = resultSet.getInt("NAME_ID");
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

    // get the fav recipes of an user
    public static List<Integer> getFavRecipesbyUser(Integer User_Id){
        List<Integer> l_favRecipes = new ArrayList<>();
        try (Connection connection = connect()) {
            if (connection != null) {
                // Request all users from table User
                String query = "SELECT RECIPE_ID FROM Favorites WHERE USER_ID = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, User_Id);
                    // Execute request
                    ResultSet resultSet = statement.executeQuery();

                    // Go through the results and display them on console
                    while (resultSet.next()) {
                        int id = resultSet.getInt("RECIPE_ID");
                        l_favRecipes.add(id);
                    }
                }
            } else {
                System.out.println("Failed to connect to db");
            }
            return l_favRecipes;
        } catch (SQLException e) {
            e.printStackTrace();
            return l_favRecipes;
        }
    }

    // get the shopping list of an user
    public static ShoppingList getShoppingListbyUser(Integer user_id){
        	ShoppingList shopList = new ShoppingList();
        	try (Connection connection = connect()) {
                if (connection != null) {
                    // Request all users from table User
                    String query = "SELECT i.ingredient_name FROM Shopping_List AS s JOIN Ingredients AS i ON s.INGREDIENT_ID=i.ID   WHERE USER_ID = ?";

                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                    	statement.setInt(1, user_id);
                        // Execute request

                        ResultSet resultSet = statement.executeQuery();

                        // Go through the results and display them on console
                        while (resultSet.next()) {
                            String name = resultSet.getString("ingredient_name");
                            Ingredient t_ingredient = new Ingredient(null, name, null, null);
                            shopList.addIngredient(t_ingredient);
                        }

                    }
                } else {
                    System.out.println("Failed to connect to db");
                }
                return shopList;
            } catch (SQLException e) {
                e.printStackTrace();
                return shopList;
            }


    }

    // add an ingredient to the shopping list
    public static void addIngredientToShoppingList(Integer userID, Ingredient ingredient){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = getAnIngredientID(ingredient.getName());
                String query = "INSERT INTO Shopping_List (INGREDIENT_ID, USER_ID) VALUES (?, ?)";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, id);
                    statement.setInt(2, userID);
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

    // remove an ingredient from the shopping list
    public static void removeIngredientFromShoppingList(Integer userID, Ingredient ingredient){
        int id;
        // Establish connection to db
        try(Connection connection = connect()){
            if(connection!=null){
                id = getAnIngredientID(ingredient.getName());
                String query = "DELETE FROM Shopping_List WHERE INGREDIENT_ID = ? AND USER_ID = ?";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, id);
                    statement.setInt(2, userID);
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

    // clear the shopping list
    public static boolean clearShoppingList(Integer userID) {
    	try(Connection connection = connect()){
            if(connection!=null){
                String query = "DELETE FROM Shopping_List WHERE USER_ID = ?";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, userID);
                    // Execute request
                    statement.executeUpdate();
                }
                return true;
            } else {
                System.out.println("Failed to connect to db");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
