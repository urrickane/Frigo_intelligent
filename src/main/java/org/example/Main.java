package org.example;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        l_ingredients.add("chocolate");
        l_ingredients.add("milk");
        l_ingredients.add("apple");
        StringBuilder ingredients = new StringBuilder();
        for(int i=0;i<l_ingredients.size();i++){
            ingredients.append(l_ingredients.get(i));
            if(i!=l_ingredients.size()-1) ingredients.append(",");
        }


        // create a request
        var request = HttpRequest.newBuilder(
                        URI.create(Baseurl+"recipes/complexSearch?includeIngredients="+ingredients+"&fillIngredients=true&number=2&sort=max-used-ingredients&addRecipeInformation=true&apiKey=338284d8348d4e2bb344a1747cb00005"))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());
        //result =
        // the response:
        System.out.println(response);
        System.out.println(jsonNode.get("results"));
    }


    private static HttpClient getDefaultApi() {
        // create a client
        return HttpClient.newHttpClient();
    }


}