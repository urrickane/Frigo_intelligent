package Classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class InteractionBackFrontTest {

	@Test
	void testActualisationRecetteEtapes() {
		Ingredient ingredient1 = new Ingredient("2023-01-01", "Tomato", null,null);
        Ingredient ingredient2 = new Ingredient("2023-02-15", "Milk", null,null);
        
        List<Ingredient> l_ingredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testUsedIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testMissingIngredients = new ArrayList<Ingredient>();
        List<String> l_steps = new ArrayList<String>();
        
        l_steps.add("Preheat your oven to 450F.");
        l_steps.add("Mix goat cheese, parmesan cheese and basil in a small bowl.");
        l_steps.add("Cut tomatoes in half, drizzle each half with olive oil and sprinkle with salt & pepper.");
        //l_steps.add("Top each tomato half with the cheese mix, equally dividing the mixture between the four halves.");
        //l_steps.add("Bake the tomatoes for about 20 minutes, or until softened and slightly browned.");
        //l_steps.add("Serve hot or at room temperature.");
        
        Ingredient testIngredient1 = new Ingredient(null, "tomatoes", 2.0,"");
        Ingredient testIngredient2 = new Ingredient(null, "unripened goat's milk cheese", 61.0,"ml");
        Ingredient testIngredient3 = new Ingredient(null, "parmesan", 2.0,"Tbsps");
        Ingredient testIngredient5 = new Ingredient(null, "basil", 1.0,"Tbsp");
        Ingredient testIngredient6 = new Ingredient(null, "salt & pepper", 1.0,"pinch");
        Ingredient testIngredient7 = new Ingredient(null, "olive oil", 4.0,"servings");
        
        Ingredient testUsedIngredient1 = new Ingredient(null, "tomatoes", 2.0,"");
        Ingredient testUsedIngredient2 = new Ingredient(null, "unripened goat's milk cheese", 0.25,"cup");
        Ingredient testMissingIngredient1 = new Ingredient(null, "parmesan", 2.0,"tbsp");
        Ingredient testMissingIngredient2 = new Ingredient(null, "basil", 1.0,"tbsp");
        
        l_ingredients.add(ingredient1);
        l_ingredients.add(ingredient2);
        
        l_testIngredients.add(testIngredient1);
        l_testIngredients.add(testIngredient2);
        l_testIngredients.add(testIngredient3);
        l_testIngredients.add(testIngredient5);
        l_testIngredients.add(testIngredient6);
        l_testIngredients.add(testIngredient7);
        
        l_testUsedIngredients.add(testUsedIngredient1);
        l_testUsedIngredients.add(testUsedIngredient2);
        
        l_testMissingIngredients.add(testMissingIngredient1);
        l_testMissingIngredients.add(testMissingIngredient2);
		
		Recipe recette = new Recipe("https://spoonacular.com/recipeImages/633852-312x231.jpg", 
				"baked tomatoes",
				l_testIngredients,
                "Baked tomatoes is a <b>gluten free and primal</b> side dish. This recipe serves 4.",
                4,
                l_steps,
                l_testUsedIngredients,
                l_testMissingIngredients,45);
	}

}
