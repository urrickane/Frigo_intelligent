package fr.tse.fise2.info4;

import Classes.Allergen;
import Classes.Fridge;
import Classes.Ingredient;
import Classes.User;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
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
        l_allergen.add(new Allergen("Dairy"));
        User testUser = new User(1,"Boris",l_allergen,new ArrayList<>());
        Ingredient testIngredient = new Ingredient("29-11-2024", "eggs", 1.0,"gram");
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        l_testIngredients.add(testIngredient);
        testUser.setFridge((new Fridge(l_testIngredients)));
        assertThat(Database.getUser("Boris"))
                .isNotNull()
                .isEqualToComparingFieldByField(testUser);

    }

    @Test
    void testAddorUpdateIngredient() {
        Ingredient testIngredient = new Ingredient("29-11-2024", "eggs", 1.0,"gram");
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        l_testIngredients.add(testIngredient);
        Fridge testFridge = new Fridge(l_testIngredients);
        User testUser = new User(1,"Boris",new ArrayList<>(),new ArrayList<>());
        testUser.setFridge(testFridge);
        Database.AddorUpdateIngredient(1, testIngredient);
        assertThat(Database.getUser("Boris").getFridge().getInventory())
                .isNotNull()
                .isEqualTo(testUser.getFridge().getInventory());
        Database.AddorUpdateIngredient(1, testIngredient);
        testFridge.addOrUpdateIngredients(testIngredient);
        assertThat(Database.getUser("Boris").getFridge().getInventory())
                .isNotNull()
                .isEqualTo(testUser.getFridge().getInventory());
        Database.SupressIngredient(1, testIngredient);
    }
    @Test
    void supressIngredient() {
        Ingredient testIngredient = new Ingredient("29-11-2024", "eggs", 1.0,"gram");
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        l_testIngredients.add(testIngredient);
        Fridge testFridge = new Fridge(l_testIngredients);
        User testUser = new User(1,"Boris",new ArrayList<>(),new ArrayList<>());
        testUser.setFridge(testFridge);
        Database.AddorUpdateIngredient(1, testIngredient);
        Database.SupressIngredient(1, testIngredient);
        assertThat(Database.getUser("Boris").getFridge().getInventory())
                .isNotNull()
                .isEmpty();
        Database.AddorUpdateIngredient(1, testIngredient);
        Database.AddorUpdateIngredient(1, testIngredient);
        Database.SupressIngredient(1, testIngredient);
        assertThat(Database.getUser("Boris").getFridge().getInventory())
                .isNotNull()
                .isEqualTo(testUser.getFridge().getInventory());
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
        User testUser = Database.getUser("Boris");
        testUser.addAllergy(egg);
        Database.AddAllergen(1, egg);
        assertThat(Database.getUser("Boris").getAllergies())
                .isNotNull()
                .isEqualTo(testUser.getAllergies());
        Database.RemoveAllergen(1, egg);

    }
    @Test
    void testRemoveAllergen(){
        Allergen egg = new Allergen("Egg");
        User testUser = Database.getUser("Boris");
        testUser.addAllergy(egg);
        Database.AddAllergen(1, egg);
        Database.RemoveAllergen(1, egg);
        testUser.RemoveAllergy(egg);
        assertThat(Database.getUser("Boris").getAllergies())
                .isNotNull()
                .isEqualTo(testUser.getAllergies());

    }

}