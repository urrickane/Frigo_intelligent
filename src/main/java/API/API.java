package API;

import Classes.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class API {
    private final HttpClient client;

    public String getAPIkey() {
        return APIkey;
    }

    public void setAPIkey(String APIkey) {
        this.APIkey = APIkey;
    }

    private String APIkey;
    private String Baseurl;
    public API(){
        client = getDefaultApi();
        Baseurl = "https://api.spoonacular.com/";
        APIkey = "338284d8348d4e2bb344a1747cb00005";
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
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                List<Recipe> recipies = new ArrayList<>();
                for(int i=0;i<jsonNode.get("results").size();i++){
                    List<Ingredient> l_ing = new ArrayList<>();
                    List<Double> l_quantity = new ArrayList<>();
                    JsonNode jsonRecipe = jsonNode.get("results").get(i);

                    for(int j=0;j<jsonRecipe.get("extendedIngredients").size();j++){
                        Ingredient ingredient = new Ingredient(null,jsonRecipe.get("extendedIngredients").get(j).get("name").asText(),false);
                        double quantity = jsonRecipe.get("extendedIngredients").get(j).get("amount").asDouble();
                        l_ing.add(ingredient);
                        l_quantity.add(quantity);
                    }
                    int cookingTime = jsonRecipe.get("readyInMinutes").asInt();
                    String srcimg = jsonRecipe.get("image").asText();
                    String title = jsonRecipe.get("title").asText();
                    Recipe recipe = new Recipe(srcimg,title,l_ing,l_quantity,cookingTime);
                    recipies.add(recipe);
                }
                return recipies;
            } else {
                throw new RuntimeException("Error: " + statusCode);
            }

        } catch (InterruptedException | IOException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
        public JsonNode GetRecipeInformation(List<Integer> l_id,boolean includeNutrition) throws IOException, InterruptedException {
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
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readTree(response.body());
                } else {
                    throw new RuntimeException("Error: " + statusCode);
                }

            } catch (InterruptedException | IOException | RuntimeException e) {
                e.printStackTrace();
                return null;
            }


    }



}
