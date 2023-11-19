package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");
        HttpClient client = getDefaultApi();
        String Baseurl = "https://api.spoonacular.com/";
        List<String> l_ingredients = new ArrayList<String>();
        l_ingredients.add("egg");
        StringBuilder ingredients = new StringBuilder();
        for(int i=0;i<l_ingredients.size();i++){
            ingredients.append(l_ingredients.get(i));
            if(i!=l_ingredients.size()-1) ingredients.append(",");
        }


        // create a request
        var request = HttpRequest.newBuilder(
                        URI.create(Baseurl+"recipes/complexSearch?includeIngredients="+ingredients+"&number=2&sort=max-used-ingredients&addRecipeInformation=true&apiKey=dc10b74fe415423ebe608bad9c004691"))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());
        
        // Extract title of first recipe
        String titleOfRecipe1 = jsonNode.get("results").get(0).get("title").asText();
        
        // Extract all titles or recipes from JSON data 
        JsonNode resultsArray = jsonNode.get("results");
        List<String> l_recipeTitle = new ArrayList<String>();
        if (resultsArray != null && resultsArray.isArray() && resultsArray.size() > 0) {
            // Go through results
            for (JsonNode result : resultsArray) {
                // Extract title for each
                String title = result.get("title").asText();
                l_recipeTitle.add(title);
            }
        } else {
            System.out.println("No results");
        }
        
        //result =
        // the response
        System.out.println(response);
        // JSON data response from the API
        System.out.println(jsonNode.get("results"));
        // Extracted title of first recipe
        System.out.println(titleOfRecipe1);
        // All titles from recipes
        for(int i=0; i<l_recipeTitle.size(); i++) {
        	System.out.println(l_recipeTitle.get(i));
        }
        
    }


    private static HttpClient getDefaultApi() {
        // create a client
        var client = HttpClient.newHttpClient();
        return client;
    }


}