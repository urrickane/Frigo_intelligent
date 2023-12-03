package Classes;

import java.util.List;

public class Recipe {
    private String linkToImage;
    private String title;
    private List<Ingredient> l_ingredients;

    public String getLinkToImage() {
        return linkToImage;
    }

    public String getTitle() {
        return title;
    }

    public List<Ingredient> getL_ingredients() {
        return l_ingredients;
    }

    public List<Double> getL_quantityIngredients() {
        return l_quantityIngredients;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    private List<Double> l_quantityIngredients;
    private int cookingTime;

    // Constructeur avec paramètres
    public Recipe(String linkToImage, String title, List<Ingredient> l_ingredients,
                  List<Double> l_quantityIngredients, int cookingTime) {
        this.linkToImage = linkToImage;
        this.title = title;
        this.l_ingredients = l_ingredients;
        this.l_quantityIngredients = l_quantityIngredients;
        this.cookingTime = cookingTime;
    }

}

