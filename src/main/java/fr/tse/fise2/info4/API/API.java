package fr.tse.fise2.info4.API;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.tse.fise2.info4.Classes.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class API {
    private final HttpClient client;

    private static API instance = null;

    public String getAPIkey() {
        return APIkey;
    }

    public void setAPIkey(String APIkey) {
        this.APIkey = APIkey;
    }

    private String APIkey;
    private String Baseurl;
    private API(){
        client = getDefaultApi();
        Baseurl = "https://api.spoonacular.com/";
        APIkey = "338284d8348d4e2bb344a1747cb00005";
    }

    public static API getAPI(){
        if(instance == null){
            instance = new API();
        }
        return instance;
    }

    private static HttpClient getDefaultApi() {
        // create a client
            return HttpClient.newHttpClient();
    }

    /**
     * @return the Baseurl
     */
    public String getBaseurl() {
        return Baseurl;
    }

    /**
     * @param baseurl the baseurl to set
     */
    public void setBaseurl(String baseurl) {
        Baseurl = baseurl;
    }


    /**
     * @param user
     * @param number
     * @param sort
     * @param addRecipeInformation
     * @param fillIngredients
     * @param includeNutrition
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Recipe> ComplexSearch(User user, int number, String sort, boolean addRecipeInformation, boolean fillIngredients, boolean includeNutrition) {
        Fridge fridge = user.getFridge();
        List<Ingredient> l_ingredients = fridge.getInventory();
        List<Allergen> l_intolerances = user.getAllergies();
        StringBuilder ingredients = new StringBuilder();
        StringBuilder intolerances = new StringBuilder();

        if (l_ingredients != null) {
            for (int i = 0; i < l_ingredients.size(); i++) {
                ingredients.append(l_ingredients.get(i).getName());
                if (i != l_ingredients.size() - 1) ingredients.append(",");
            }
        }
        if (l_intolerances != null) {
            for (int i = 0; i < l_intolerances.size(); i++) {
                intolerances.append(l_intolerances.get(i).getaName());
                if (i != l_intolerances.size() - 1) intolerances.append(",");
            }
        }
        // create a request
        var request = HttpRequest.newBuilder(
                        URI.create(Baseurl + "recipes/complexSearch?includeIngredients=" + ingredients + "&fillIngredients=" + fillIngredients + "&number=" + number + "&sort=" + sort + "&addRecipeInformation=" + addRecipeInformation + "&includeNutrition=" + includeNutrition + "&intolerances=" + intolerances + "&apiKey=" + APIkey))
                .header("accept", "application/json")
                .build();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return getRecipeList(response);
            } else {
                throw new RuntimeException("Error: " + statusCode);
            }

        } catch (InterruptedException | IOException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Recipe> SortRecipies(List<Recipe> recipesToSort, String sortby){
        switch(sortby){
            case "max-used-ingredients":
                recipesToSort.sort((Recipe r1, Recipe r2) -> r2.getL_usedIngredients().size() - r1.getL_usedIngredients().size());
                break;
            case "max-missing-ingredients":
                recipesToSort.sort((Recipe r1, Recipe r2) -> r2.getL_missingIngredients().size() - r1.getL_missingIngredients().size());
                break;
            case "max-time":
                recipesToSort.sort((Recipe r1, Recipe r2) -> r2.getCookingTime() - r1.getCookingTime());
                break;
            case "healty":
                recipesToSort.sort((Recipe r1, Recipe r2) -> r2.getHealthScore() - r1.getHealthScore());
                break;
            default:
                break;
        }
        return recipesToSort;
    }

    private static List<Recipe> getRecipeList(HttpResponse<String> response) throws JsonProcessingException, NullPointerException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());
        List<Recipe> recipes = new ArrayList<>();
        JsonNode jsonResults = jsonNode.get("results");
        if(jsonNode.get("results") == null) jsonResults = jsonNode;
        for(JsonNode aRecipe : jsonResults){
            List<Ingredient> l_ing = new ArrayList<>();
            List<Ingredient> l_usedIng = new ArrayList<>();
            List<Ingredient> l_missIng = new ArrayList<>();
            List<String> l_steps = new ArrayList<>();
            System.out.println(aRecipe);
            JsonNode jsonSteps = aRecipe.get("analyzedInstructions");
            if(!aRecipe.get("analyzedInstructions").isEmpty()){
                jsonSteps = jsonSteps.get(0).get("steps");
            }
            else jsonSteps = aRecipe.get("instructions");
            for(JsonNode anIngredient :aRecipe.get("extendedIngredients")){
                Ingredient ingredient = new Ingredient(null,anIngredient.get("name").asText(),anIngredient.get("amount").asDouble(),anIngredient.get("unit").asText());
                l_ing.add(ingredient);
            }
            if(aRecipe.get("usedIngredients") != null) {
                for (JsonNode anUsedIngredient : aRecipe.get("usedIngredients")) {
                    Ingredient ingredient = new Ingredient(null, anUsedIngredient.get("name").asText(), anUsedIngredient.get("amount").asDouble(), anUsedIngredient.get("unit").asText());
                    l_usedIng.add(ingredient);
                }
            }
            if(aRecipe.get("missedIngredients") != null) {
                for (JsonNode anMissingIngredient : aRecipe.get("missedIngredients")) {
                    Ingredient ingredient = new Ingredient(null, anMissingIngredient.get("name").asText(), anMissingIngredient.get("amount").asDouble(), anMissingIngredient.get("unit").asText());
                    l_missIng.add(ingredient);
                }
            }
            if(jsonSteps == null) {
                for (JsonNode jsonStep : jsonSteps) {
                    String step = jsonStep.get("step").asText();
                    l_steps.add(step);
                }
            }
            int cookingTime = aRecipe.get("readyInMinutes").asInt();
            int nbPeople = aRecipe.get("servings").asInt();
            int id = aRecipe.get("id").asInt();
            String srcImg = aRecipe.get("image").asText();
            String title = aRecipe.get("title").asText();
            int healthScore = aRecipe.get("healthScore").asInt();
            Recipe recipe = new Recipe(id,srcImg,title,l_ing,nbPeople,l_steps,l_usedIng,l_missIng,cookingTime,healthScore);
            recipes.add(recipe);
        }
        return recipes;
    }

    public List<Recipe> GetRecipeInformation(User user,boolean includeNutrition) throws IOException, InterruptedException {
        List<Integer> l_id = user.getFavRecipes();
        StringBuilder id = new StringBuilder();
        for(int i=0;i<l_id.size();i++){
            id.append(l_id.get(i));
            if(i!=l_id.size()-1) id.append(",");
        }
        // create a request
        var request = HttpRequest.newBuilder(
                        URI.create(Baseurl+"recipes/informationBulk?ids="+id+"&includeNutrition="+includeNutrition+"&apiKey="+APIkey))
                .header("accept", "application/json")
                .build();
            try {
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                int statusCode = response.statusCode();
                if (statusCode == 200) {
                    return getRecipeList(response);
                } else {
                    throw new RuntimeException("Error: " + statusCode);
                }

            } catch (InterruptedException | IOException | RuntimeException e) {
                e.printStackTrace();
                return null;
            }


    }



}
