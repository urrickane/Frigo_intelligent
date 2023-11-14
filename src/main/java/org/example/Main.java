package org.example;

import com.spoonacular.DefaultApi;
import com.spoonacular.RecipesApi;
import com.spoonacular.client.ApiClient;
import com.spoonacular.client.ApiException;
import com.spoonacular.client.Configuration;
import com.spoonacular.client.auth.*;
import com.spoonacular.client.model.GetAnalyzedRecipeInstructions200Response;
import com.spoonacular.client.model.GetIngredientInformation200Response;
import com.spoonacular.IngredientsApi;
import com.spoonacular.client.model.GetRandomRecipes200Response;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ApiClient apiInstance = getDefaultApi();


        //apiInstance.buildCall("https://api.spoonacular.com","/food/ingredients/9251/information",)
        RecipesApi RecipieapiInstance = new RecipesApi(apiInstance);
        Boolean limitLicense = true; // Boolean | Whether the recipes should have an open license that allows display with proper attribution.
        String tags = "pineapple"; // String | The tags (can be diets, meal types, cuisines, or intolerances) that the recipe must have.
        Integer number = 1; // Integer | The maximum number of items to return (between 1 and 100). Defaults to 10.
        try {
            GetRandomRecipes200Response result = RecipieapiInstance.getRandomRecipes(limitLicense, tags, number);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#analyzeRecipe");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }


    private static ApiClient getDefaultApi() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.spoonacular.com");

        // Configure API key authorization: apiKeyScheme
        ApiKeyAuth apiKeyScheme = (ApiKeyAuth) defaultClient.getAuthentication("apiKeyScheme");
        apiKeyScheme.setApiKey("338284d8348d4e2bb344a1747cb00005");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //apiKeyScheme.setApiKeyPrefix("Token");

        return defaultClient;
    }


}