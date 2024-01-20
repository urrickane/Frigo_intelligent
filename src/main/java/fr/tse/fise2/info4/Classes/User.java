package fr.tse.fise2.info4.Classes;

import fr.tse.fise2.info4.Database;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String userName;
    private List<Allergen> l_allergies;
    private List<Integer> l_favoriteRecipes;

    private ShoppingList shoppingList;

    private Fridge fridge;


    // Constructeur
    public User(Integer id,String name, List<Allergen> l_allergies, List<Integer> l_favRecipes, ShoppingList shoppingList) {
        this.id = id;
        this.fridge = new Fridge();
        this.userName = name;
        this.l_allergies = l_allergies;
        this.l_favoriteRecipes = l_favRecipes;
        this.shoppingList = shoppingList;
    }

    public void copy(User user) {
        this.id = user.id;
        this.fridge = user.fridge;
        this.userName = user.userName;
        this.l_allergies = user.l_allergies;
        this.l_favoriteRecipes = user.l_favoriteRecipes;
        this.shoppingList = user.shoppingList;
    }


    public ShoppingList getShoppingList() {
        return this.shoppingList;
    }
    public Integer getId() {
        return id;
    }
    // Méthode pour obtenir le nom de l'utilisateur
    public String getName() {
        return this.userName;
    }
    
    // Méthode pour obtenir la liste des allergies de l'utilisateur
    public List<Allergen> getAllergies() {
        return this.l_allergies;
    }

    // Méthode pour obtenir la liste des recettes favorites de l'utilisateur
    public List<Integer> getFavRecipes() {
        return this.l_favoriteRecipes;
    }

    // Méthode pour obtenir le réfrigérateur de l'utilisateur
    public Fridge getFridge() { return fridge;}

    // Méthode pour définir le réfrigérateur de l'utilisateur
    public void setFridge(Fridge fridge) { this.fridge = fridge;}

    public void AddIngredient(Ingredient ingredient) {
        Database.AddorUpdateIngredient(this.id, ingredient);
        this.fridge.addOrUpdateIngredients(ingredient);
    }

    public void ModifyIngredientAmount(Ingredient ingredient, double newAmount) {
        Database.ModifyIngredient(this.id, ingredient, newAmount);
        this.fridge.ModifyIngredientAmount(ingredient, newAmount);
    }

    public void RemoveIngredient(Ingredient ingredient) {
        Database.SupressIngredient(this.id, ingredient);
        this.fridge.removeOrUpdateItem(ingredient);
    }

    // Méthode pour ajouter une allergie à la liste
    public void addAllergy(Allergen allergen) {
        Database.AddAllergen(this.id, allergen);
        this.l_allergies.add(allergen);
    }
    public void  removeAllergy(Allergen allergen) {
        Database.RemoveAllergen(this.id, allergen);
        this.l_allergies.remove(allergen);
    }

    public void addIngredientToShoppingList(Ingredient ingredient) {
        Database.addIngredientToShoppingList(this.id, ingredient);
        this.shoppingList.addIngredient(ingredient);
    }

    public void removeIngredientFromShoppingList(Ingredient ingredient) {
        Database.removeIngredientFromShoppingList(this.id, ingredient);
        this.shoppingList.removeItem(ingredient);
    }

    public void addRecipeToFavorites(Integer recipe) {
        Database.AddFavoriteRecipe(this.id, recipe);
        this.l_favoriteRecipes.add(recipe);
    }

    public void removeRecipeFromFavorites(Integer recipe) {
        Database.RemoveFavoriteRecipe(this.id, recipe);
        this.l_favoriteRecipes.remove(recipe);
    }
    public void clearShoppingList() {
        Database.clearShoppingList(this.id);
        this.shoppingList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(l_allergies, user.l_allergies) && Objects.equals(l_favoriteRecipes, user.l_favoriteRecipes) && Objects.equals(fridge, user.fridge);
    }
}

