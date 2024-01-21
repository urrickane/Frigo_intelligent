package fr.tse.fise2.info4.Classes;

import fr.tse.fise2.info4.Database.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testAddIngredient() {
        User test = Database.getUser("Boris");
        Ingredient testIngredient = new Ingredient("29-11-2024", "tomatoes", 3.0,"gram");
        test.AddIngredient(testIngredient);
        assertEquals(test.getFridge().findItemFromName("tomatoes"), testIngredient);
        assertEquals(Database.getUser("Boris").getFridge().findItemFromName("tomatoes"), testIngredient);
        test.RemoveIngredient(testIngredient);
    }

    @Test
    void removeIngredient() {
        User test = Database.getUser("Boris");
        Ingredient testIngredient = new Ingredient("29-11-2024", "tomatoes", 3.0,"gram");
        test.AddIngredient(testIngredient);
        assertEquals(test.getFridge().findItemFromName("tomatoes"), testIngredient);
        assertEquals(Database.getUser("Boris").getFridge().findItemFromName("tomatoes"), testIngredient);
        test.RemoveIngredient(testIngredient);
        assertNull(test.getFridge().findItemFromName("tomatoes"));
        assertNull(Database.getUser("Boris").getFridge().findItemFromName("tomatoes"));
    }

    @Test
    void testaddAllergy() {
        User test = Database.getUser("Boris");
        Allergen testAllergen = new Allergen("Seafood");
        test.addAllergy(testAllergen);
        assertEquals(test.getAllergies().get(1), testAllergen);
        assertEquals(Database.getUser("Boris").getAllergies().get(1), testAllergen);
        test.removeAllergy(testAllergen);
    }

    @Test
    void testremoveAllergy() {
        User test = Database.getUser("Boris");
        Allergen testAllergen = new Allergen("Seafood");
        test.addAllergy(testAllergen);
        assertEquals(test.getAllergies().get(1), testAllergen);
        assertEquals(Database.getUser("Boris").getAllergies().get(1), testAllergen);
        test.removeAllergy(testAllergen);
        assertFalse(test.getAllergies().contains(testAllergen));
        assertFalse(Database.getUser("Boris").getAllergies().contains(testAllergen));
    }

    @Test
    void testAddIngredientToShoppingList() {
        User test = Database.getUser("Boris");
        Ingredient testIngredient = new Ingredient(null, "tomatoes", null,null);
        test.addIngredientToShoppingList(testIngredient);
        assertEquals(test.getShoppingList().findItemFromName("tomatoes"), testIngredient);
        assertEquals(Database.getUser("Boris").getShoppingList().findItemFromName("tomatoes"), testIngredient);
        test.removeIngredientFromShoppingList(testIngredient);
    }

    @Test
    void removeIngredientFromShoppingList() {
        User test = Database.getUser("Boris");
        Ingredient testIngredient = new Ingredient(null, "tomatoes", null,null);
        test.addIngredientToShoppingList(testIngredient);
        test.removeIngredientFromShoppingList(testIngredient);
        assertNull(test.getShoppingList().findItemFromName("tomatoes"));
        assertNull(Database.getUser("Boris").getShoppingList().findItemFromName("tomatoes"));
    }

    @Test
    void addRecipeToFavorites() {
        User test = Database.getUser("Boris");
        test.addRecipeToFavorites(1);
        assertTrue(test.getFavRecipes().contains(1));
        assertTrue(Database.getUser("Boris").getFavRecipes().contains(1));
        test.removeRecipeFromFavorites(1);
    }

    @Test
    void removeRecipeFromFavorites() {
        User test = Database.getUser("Boris");
        test.addRecipeToFavorites(1);
        test.removeRecipeFromFavorites(1);
        assertFalse(test.getFavRecipes().contains(1));
        assertFalse(Database.getUser("Boris").getFavRecipes().contains(1));
    }
}