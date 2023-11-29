package Classes;

import java.util.List;

public class User {
    private String userName;
    private List<Allergen> l_allergies;
    private List<Recipe> l_favoriteRecipes;

    // Constructeur
    public User(String name, List<Allergen> l_allergies, List<Recipe> l_favRecipes) {
        this.userName = name;
        this.l_allergies = l_allergies;
        this.l_favoriteRecipes = l_favRecipes;
    }

    // Méthode pour définir le nom de l'utilisateur
    public void setName(String name) {
        this.userName = name;
    }

    // Méthode pour définir les allergies de l'utilisateur
    public void setAllergies(List<Allergen> l_allergens) {
        this.l_allergies = l_allergens;
    }

    // Méthode pour définir les recettes favorites de l'utilisateur
    public void setFavRecipes(List<Recipe> l_recipes) {
        this.l_favoriteRecipes = l_recipes;
    }

    // Méthode pour ajouter une allergie à la liste
    public void addAllergy(Allergen allergen) {
        this.l_allergies.add(allergen);
    }

    // Méthode pour ajouter une recette favorite à la liste
    public void addFavRecipe(Recipe recipe) {
        this.l_favoriteRecipes.add(recipe);
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
    public List<Recipe> getFavRecipes() {
        return this.l_favoriteRecipes;
    }
}

