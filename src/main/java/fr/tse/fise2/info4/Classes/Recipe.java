package fr.tse.fise2.info4.Classes;

import java.util.List;
import java.util.Objects;

public class Recipe {
    private final String linkToImage;
    private final String title;
    private final List<Ingredient> l_ingredients;

    private final String summary;

    private List<String> l_steps;

    private Integer nbPeople;

    public String getSummary() {
        return summary;
    }

    public List<String> getL_steps() {
        return l_steps;
    }

    public Integer getNbPeople() {
        return nbPeople;
    }

    public List<Ingredient> getL_missingIngredients() {
        return l_missingIngredients;
    }

    public List<Ingredient> getL_usedIngredients() {
        return l_usedIngredients;
    }

    private List<Ingredient> l_missingIngredients;

    private List<Ingredient> l_usedIngredients;

    public String getLinkToImage() {
        return linkToImage;
    }

    public String getTitle() {
        return title;
    }

    public List<Ingredient> getL_ingredients() {
        return l_ingredients;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    private int cookingTime;

    private int id;

    public void setL_steps(List<String> l_steps) {
        this.l_steps = l_steps;
    }

    public void setNbPeople(Integer nbPeople) {
        this.nbPeople = nbPeople;
    }

    public void setL_missingIngredients(List<Ingredient> l_missingIngredients) {
        this.l_missingIngredients = l_missingIngredients;
    }

    public void setL_usedIngredients(List<Ingredient> l_usedIngredients) {
        this.l_usedIngredients = l_usedIngredients;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    private int healthScore;

    // Constructeur avec paramètres
    public Recipe(int id,String linkToImage, String title, List<Ingredient> l_ingredients,String summary,int nbPeople,List<String> l_steps,List<Ingredient> l_usedIngredients,List<Ingredient> l_missingIngredients, int cookingTime,int healthScore) {
        this.id = id;
        this.linkToImage = linkToImage;
        this.title = title;
        this.summary = summary;
        this.nbPeople = nbPeople;
        this.l_steps = l_steps;
        this.l_missingIngredients = l_missingIngredients;
        this.l_usedIngredients = l_usedIngredients;
        this.l_ingredients = l_ingredients;
        this.cookingTime = cookingTime;
        this.healthScore = healthScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(healthScore, recipe.healthScore) && Objects.equals(cookingTime, recipe.cookingTime) && Objects.equals(linkToImage, recipe.linkToImage) && Objects.equals(title, recipe.title) && Objects.equals(l_ingredients, recipe.l_ingredients) && Objects.equals(summary, recipe.summary) && Objects.equals(l_steps, recipe.l_steps) && Objects.equals(nbPeople, recipe.nbPeople) && Objects.equals(l_missingIngredients, recipe.l_missingIngredients) && Objects.equals(l_usedIngredients, recipe.l_usedIngredients);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

