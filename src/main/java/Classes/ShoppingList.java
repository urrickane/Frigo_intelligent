package Classes;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<Ingredients> l_shopList;

    // Constructeur par défaut
    public ShoppingList() {
        this.l_shopList = new ArrayList<>();
    }

    // Méthode pour ajouter un ingrédient à la liste d'achats
    public void addIngredient(Ingredients ingredient) {
        this.l_shopList.add(ingredient);
    }

    // Méthode pour supprimer un ingrédient de la liste d'achats
    public void removeItem(Ingredients toRemove) {
         this.l_shopList.remove(toRemove);
    }

    // Méthode pour obtenir la liste des ingrédients dans la liste d'achats
    public List<Ingredients> getShoppingList() {
        return this.l_shopList;
    }
    // Méthode pour vider la liste d'achats
    public void clear() {
        this.l_shopList.clear();
    }


}
