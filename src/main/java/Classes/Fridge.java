package Classes;
import java.util.ArrayList;
import java.util.List;

public class Fridge {
    private List<Ingredients> ingredients;

    // Constructeur par défaut
    public Fridge() {
        this.ingredients = new ArrayList<>();
    }

    // Méthode pour ajouter un ingrédient au réfrigérateur
    public void addIngredients(Ingredients ingredient) {
        this.ingredients.add(ingredient);
    }

    // Méthode pour supprimer un ingrédient du réfrigérateur
    public boolean removeItem(Ingredients toRemove) {
        return this.ingredients.remove(toRemove);
    }

    // Méthode pour trouver un ingrédient par son nom
    public Ingredients findItemFromName(String toFind) {
        for (Ingredients ingredient : this.ingredients) {
            if (ingredient.getName().equals(toFind)) {
                return ingredient;
            }
        }
        return null; // Retourne null si l'ingrédient n'est pas trouvé
    }

    // Méthode pour obtenir la liste des ingrédients dans le réfrigérateur
    public List<Ingredients> getInventory() {
        return this.ingredients;
    }

}

