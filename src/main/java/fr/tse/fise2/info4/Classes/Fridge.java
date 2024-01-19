package fr.tse.fise2.info4.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fridge {
    private List<Ingredient> ingredients;

    // Constructeur avec paramètres
    public Fridge(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // Constructeur par défaut
    public Fridge() {
        this.ingredients = new ArrayList<Ingredient>();
    }

    // Méthode pour ajouter un ingrédient au réfrigérateur
    public void addOrUpdateIngredients(Ingredient ingredient) {
        Ingredient toFind = findItemFromName(ingredient.getName());
        if(toFind != null) {
            toFind.setQuantity(toFind.getQuantity() + ingredient.getQuantity());
            toFind.setExpDate(ingredient.getExpDate());
            toFind.setUnit(ingredient.getUnit());
        }
        else
        {
            this.ingredients.add(ingredient);
        }
    }

    public void ModifyIngredientAmount(Ingredient ingredient, double newAmount) {
    	Ingredient toFind = findItemFromName(ingredient.getName());
    	if(toFind != null) {
    		toFind.setQuantity(newAmount);
    	}
    }

    // Méthode pour supprimer un ingrédient du réfrigérateur
    public boolean removeOrUpdateItem(Ingredient toRemove) {
        Ingredient toFind = findItemFromName(toRemove.getName());
        if(toFind != null && toFind.getQuantity() > toRemove.getQuantity()) {
            toFind.setQuantity(toFind.getQuantity() - toRemove.getQuantity());
            toFind.setExpDate(toRemove.getExpDate());
            toFind.setUnit(toRemove.getUnit());
            return true;
        }
        else
        {
            return this.ingredients.remove(toRemove);
        }
    }

    // Méthode pour trouver un ingrédient par son nom
    public Ingredient findItemFromName(String toFind) {
        for (Ingredient ingredient : this.ingredients) {
            if (ingredient.getName().equals(toFind)) {
                return ingredient;
            }
        }
        return null; // Retourne null si l'ingrédient n'est pas trouvé
    }

    // Méthode pour obtenir la liste des ingrédients dans le réfrigérateur
    public List<Ingredient> getInventory() {
        return this.ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fridge fridge = (Fridge) o;
        return Objects.equals(ingredients, fridge.ingredients);
    }

}

