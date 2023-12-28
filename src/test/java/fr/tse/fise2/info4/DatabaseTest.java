package fr.tse.fise2.info4;

import fr.tse.fise2.info4.Classes.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void testConnect() {
        assertThat(Database.connect()).isNotNull();
    }

    @Test
    void testGetAllUsers() {
        List<String> testList = new ArrayList<>();
        testList.add("Boris");
        testList.add("Romain");
        testList.add("Yassin");
        testList.add("Ewan");
        testList.add("Eglantine");
        testList.add("Taha");
        testList.add("Cloris");
        List<String> listofUsers = Database.getAllUsers();
        assertThat(listofUsers)
                .isNotNull()
                .isEqualTo(testList);
    }

    @Test
    void testGetUser() {
        Database.getUser("Boris");
        List<Allergen> l_allergen = new ArrayList<>();
        List<Integer> l_favRecipes = new ArrayList<>();
        ShoppingList testShoppingList = new ShoppingList();
        testShoppingList.addIngredient(new Ingredient(null, "eggs", null,null));
        l_allergen.add(new Allergen("Dairy"));
        l_favRecipes.add(633852);
        User testUser = new User(1,"Boris",l_allergen,l_favRecipes,testShoppingList);
        Ingredient testIngredient = new Ingredient("29-11-2024", "eggs", 3.0,"gram");
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        l_testIngredients.add(testIngredient);
        testUser.setFridge((new Fridge(l_testIngredients)));
        assertThat(Database.getUser("Boris"))
                .isNotNull()
                .isEqualToComparingFieldByField(testUser);

    }

    @Test
    void testAddorUpdateIngredient() {
        Ingredient testIngredient = new Ingredient("29-11-2024", "tomatoes", 3.0,"gram");
        Ingredient testIngredient2 = new Ingredient("29-11-2024", "tomatoes", 6.0,"gram");
        Database.AddorUpdateIngredient(1, testIngredient);
        assertThat(Database.getUser("Boris").getFridge().findItemFromName("tomatoes"))
                .isNotNull()
                .isEqualTo(testIngredient);
        Database.AddorUpdateIngredient(1, testIngredient);
        assertThat(Database.getUser("Boris").getFridge().findItemFromName("tomatoes"))
                .isNotNull()
                .isEqualTo(testIngredient2);
        Database.SupressIngredient(1, testIngredient2);
    }
    @Test
    void testSupressIngredient() {
        Ingredient testIngredient = new Ingredient("29-11-2024", "tomatoes", 3.0,"gram");
        Database.AddorUpdateIngredient(1, testIngredient);
        Database.SupressIngredient(1, testIngredient);
        assertThat(Database.getUser("Boris").getFridge().findItemFromName("tomatoes"))
                .isNull();
        Database.AddorUpdateIngredient(1, testIngredient);
        Database.AddorUpdateIngredient(1, testIngredient);
        Database.SupressIngredient(1, testIngredient);
        assertThat(Database.getUser("Boris").getFridge().findItemFromName("tomatoes"))
                .isNotNull()
                .isEqualTo(testIngredient);
        Database.SupressIngredient(1, testIngredient);
    }

    @Test
    void testGetAllergensbyUser(){
        Allergen Dairy = new Allergen("Dairy");
        List<Allergen> testList = new ArrayList<>();
        testList.add(Dairy);
        assertThat(Database.getAllergensbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
    }
    @Test
    void testAddAllergen(){
        Allergen egg = new Allergen("Egg");
        Database.AddAllergen(1, egg);
        assertTrue(Database.getUser("Boris").getAllergies().contains(egg));
        Database.RemoveAllergen(1, egg);

    }
    @Test
    void testRemoveAllergen(){
        Allergen egg = new Allergen("Egg");
        Database.AddAllergen(1, egg);
        Database.RemoveAllergen(1, egg);
        assertFalse(Database.getUser("Boris").getAllergies().contains(egg));

    }

    @Test
    void testgetFavRecipesbyUser(){
        List<Integer> testList = new ArrayList<>();
        testList.add(633852);
        assertThat(Database.getFavRecipesbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
    }

    @Test
    void testAddFavRecipe(){
        Database.AddFavoriteRecipe(1, 7888);
        List<Integer> testList = new ArrayList<>();
        testList.add(633852);
        testList.add(7888);
        assertThat(Database.getFavRecipesbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
        Database.RemoveFavoriteRecipe(1, 7888);
    }

    @Test
    void testRemoveFavRecipe(){
        Database.AddFavoriteRecipe(1, 7888);
        List<Integer> testList = new ArrayList<>();
        testList.add(633852);
        testList.add(7888);
        assertThat(Database.getFavRecipesbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
        Database.RemoveFavoriteRecipe(1, 7888);
        testList.remove(1);
        assertThat(Database.getFavRecipesbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
    }

    @Test
    void testGetShoppingListbyUser(){
        Ingredient testIngredient = new Ingredient(null, "eggs", null,null);
        ShoppingList testList = new ShoppingList();
        testList.addIngredient(testIngredient);
        assertThat(Database.getShoppingListbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
    }

    @Test
    void testAddIngredienttoShoppingList(){
        Ingredient testIngredient = new Ingredient(null, "tomatoes", null,null);
        Ingredient testIngredient2 = new Ingredient(null, "eggs", null,null);
        ShoppingList testList = new ShoppingList();
        testList.addIngredient(testIngredient2);
        assertThat(Database.getShoppingListbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
        Database.addIngredientToShoppingList(1, testIngredient);
        testList.addIngredient(testIngredient);
        assertThat(Database.getShoppingListbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
        Database.removeIngredientFromShoppingList(1, testIngredient);
    }

    @Test
    void testRemoveIngredientfromShoppingList(){
        Ingredient testIngredient = new Ingredient(null, "chicken", null,null);
        Ingredient testIngredient2 = new Ingredient(null, "eggs", null,null);
        ShoppingList testList = new ShoppingList();
        testList.addIngredient(testIngredient2);
        Database.addIngredientToShoppingList(1, testIngredient);
        testList.addIngredient(testIngredient);
        Database.removeIngredientFromShoppingList(1, testIngredient);
        testList.removeItem(testIngredient);
        assertThat(Database.getShoppingListbyUser(1))
                .isNotNull()
                .isEqualTo(testList);
    }

}