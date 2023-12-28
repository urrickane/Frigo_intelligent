package fr.tse.fise2.info4.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingList {
    private List<Ingredient> l_shopList;

    // Constructeur par défaut
    public ShoppingList() {
        this.l_shopList = new ArrayList<>();
    }

    // Méthode pour ajouter un ingrédient à la liste d'achats
    public void addIngredient(Ingredient ingredient) {
        this.l_shopList.add(ingredient);
    }

    // Méthode pour supprimer un ingrédient de la liste d'achats
    public void removeItem(Ingredient toRemove) {
         this.l_shopList.remove(toRemove);
    }

    // Méthode pour obtenir la liste des ingrédients dans la liste d'achats
    public List<Ingredient> getShoppingList() {
        return this.l_shopList;
    }

    public Ingredient findItemFromName(String toFind) {
        for (Ingredient ingredient : this.l_shopList) {
            if (ingredient.getName().equals(toFind)) {
                return ingredient;
            }
        }
        return null; // Retourne null si l'ingrédient n'est pas trouvé
    }

    // Méthode pour vider la liste d'achats
    public void clear() {
        this.l_shopList.clear();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(l_shopList, that.l_shopList);
    }
}
