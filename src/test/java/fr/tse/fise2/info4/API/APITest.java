package fr.tse.fise2.info4.API;

import fr.tse.fise2.info4.API.API;
import fr.tse.fise2.info4.Classes.Fridge;
import fr.tse.fise2.info4.Classes.Ingredient;
import fr.tse.fise2.info4.Classes.Recipe;
import fr.tse.fise2.info4.Classes.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class APITest {

    @org.junit.jupiter.api.Test
    void TestComplexSearch() throws JsonProcessingException {
        API api = API.getAPI();
        Ingredient ingredient1 = new Ingredient("2023-01-01", "Tomato", null,null);
        Ingredient ingredient2 = new Ingredient("2023-02-15", "Milk", null,null);
        List<Ingredient> l_ingredients = new ArrayList<Ingredient>();
        l_ingredients.add(ingredient1);
        l_ingredients.add(ingredient2);
        Fridge fridge = new Fridge(l_ingredients);
        User user = new User(0,"test",null,null,null);
        user.setFridge(fridge);
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testUsedIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testMissingIngredients = new ArrayList<Ingredient>();
        List<String> l_steps = new ArrayList<String>();
        l_steps.add("Preheat your oven to 450F.");
        l_steps.add("Mix goat cheese, parmesan cheese and basil in a small bowl.");
        l_steps.add("Cut tomatoes in half, drizzle each half with olive oil and sprinkle with salt & pepper.");
        l_steps.add("Top each tomato half with the cheese mix, equally dividing the mixture between the four halves.");
        l_steps.add("Bake the tomatoes for about 20 minutes, or until softened and slightly browned.");
        l_steps.add("Serve hot or at room temperature.");
        Ingredient testIngredient1 = new Ingredient(null, "tomatoes", 2.0,"");
        Ingredient testIngredient2 = new Ingredient(null, "unripened goat's milk cheese", 61.0,"ml");
        Ingredient testIngredient3 = new Ingredient(null, "parmesan", 2.0,"Tbsps");
        Ingredient testIngredient4 = new Ingredient(null, "basil", 1.0,"Tbsp");
        Ingredient testIngredient5 = new Ingredient(null, "basil", 1.0,"Tbsp");
        Ingredient testIngredient6 = new Ingredient(null, "salt & pepper", 1.0,"pinch");
        Ingredient testIngredient7 = new Ingredient(null, "olive oil", 4.0,"servings");
        Ingredient testUsedIngredient1 = new Ingredient(null, "tomatoes", 2.0,"");
        Ingredient testUsedIngredient2 = new Ingredient(null, "unripened goat's milk cheese", 0.25,"cup");
        Ingredient testMissingIngredient1 = new Ingredient(null, "parmesan", 2.0,"tbsp");
        Ingredient testMissingIngredient2 = new Ingredient(null, "basil", 1.0,"tbsp");
        l_testIngredients.add(testIngredient1);
        l_testIngredients.add(testIngredient2);
        l_testIngredients.add(testIngredient3);
        l_testIngredients.add(testIngredient4);
        l_testIngredients.add(testIngredient5);
        l_testIngredients.add(testIngredient6);
        l_testIngredients.add(testIngredient7);
        l_testUsedIngredients.add(testUsedIngredient1);
        l_testUsedIngredients.add(testUsedIngredient2);
        l_testMissingIngredients.add(testMissingIngredient1);
        l_testMissingIngredients.add(testMissingIngredient2);

        List<Recipe> testRecipies = new ArrayList<>();
        testRecipies.add(new Recipe(
                633852,
                "https://spoonacular.com/recipeImages/633852-312x231.jpg",
                "baked tomatoes",
                l_testIngredients,
                4,
                l_steps,
                l_testUsedIngredients,
                l_testMissingIngredients,45,6));


        try {
            List<Recipe> response = api.ComplexSearch(user,1,"max-used-ingredients",true,true,false);
            assertThat(response).isNotNull();
            for(Recipe aRecipe: response){
                assertThat(testRecipies.get(0)).isEqualToComparingFieldByField(aRecipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void TestGetRecipeInformation() throws JsonProcessingException {
        API api = API.getAPI();
        Ingredient ingredient1 = new Ingredient("2023-01-01", "Tomato", null,null);
        Ingredient ingredient2 = new Ingredient("2023-02-15", "Milk", null,null);
        List<Integer> l_id = new ArrayList<Integer>();
        l_id.add(715538);
        User user = new User(0,"test",null,l_id,null);
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testUsedIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testMissingIngredients = new ArrayList<Ingredient>();
        List<String> l_steps = new ArrayList<String>();
        l_steps.add("wash and rinse pork chops and place into the skillet.cut them into bite sized pieces and add half of the Basil Garlic simmer sauce.boil your water and start working on cooking your bow-tie pasta.when you have finished with boiling and draining your pasta, add it to the pork along with the rest of the Basil Garlic Simmering Sauce, mixing lightly.Next you will top with the Chunky Bruschetta Finishing Sauce, cover with Parmesan, and cover. Cooking on low heat 2 to 3 minutes or until heated through.");
        Ingredient testIngredient1 = new Ingredient(null, "bow tie pasta", 180.0,"g");
        Ingredient testIngredient2 = new Ingredient(null, "parmigiano reggiano", 50.0,"g");
        Ingredient testIngredient3 = new Ingredient(null, "recipe makers chicken bruschetta pasta", 5.0,"servings");
        Ingredient testIngredient4 = new Ingredient(null, "pork chops", 680.389,"g");
        l_testIngredients.add(testIngredient1);
        l_testIngredients.add(testIngredient2);
        l_testIngredients.add(testIngredient3);
        l_testIngredients.add(testIngredient4);



        List<Recipe> testRecipies = new ArrayList<>();
        testRecipies.add(new Recipe(
                715538,
                "https://spoonacular.com/recipeImages/715538-556x370.jpg",
                "What to make for dinner tonight?? Bruschetta Style Pork & Pasta",
                l_testIngredients,
                5,
                l_steps,
                l_testUsedIngredients,
                l_testMissingIngredients,35,27));
        try {
            List<Recipe> response = api.GetRecipeInformation(user,false);
            System.out.println(response);
            assertThat(response).isNotNull();
            for(Recipe aRecipe: response){
                assertThat(testRecipies.get(0)).isEqualToComparingFieldByField(aRecipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void testSort(){

    }
}