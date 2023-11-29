package Classes;

import java.util.List;

public class Recipe {
    private String linkToImage;
    private String title;
    private List<Ingredients> l_ingredients;
    private List<Integer> l_quantityIngredients;
    private int cookingTime;

    // Constructeur avec paramètres
    public Recipe(String linkToImage, String title, List<Ingredients> l_ingredients,
                  List<Integer> l_quantityIngredients, int cookingTime) {
        this.linkToImage = linkToImage;
        this.title = title;
        this.l_ingredients = l_ingredients;
        this.l_quantityIngredients = l_quantityIngredients;
        this.cookingTime = cookingTime;
    }

}

