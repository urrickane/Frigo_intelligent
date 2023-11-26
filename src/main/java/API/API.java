package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
     * @param l_ingredients
     * @param l_intolerances
     * @param number
     * @param maybesort
     * @param addRecipeInformation
     * @param fillIngredients
     * @param add
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public JsonNode ComplexSearch(List<String> l_ingredients,List<String> l_intolerances, int number, String sort, boolean addRecipeInformation,boolean fillIngredients,boolean includeNutrition) {

        StringBuilder ingredients = new StringBuilder();
        StringBuilder intolerances = new StringBuilder();

        if (l_ingredients != null) {
            for (int i = 0; i < l_ingredients.size(); i++) {
                ingredients.append(l_ingredients.get(i));
                if (i != l_ingredients.size() - 1) ingredients.append(",");
            }
        }
        if (l_intolerances != null) {
            for (int i = 0; i < l_intolerances.size(); i++) {
                intolerances.append(l_intolerances.get(i));
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
                return jsonNode.get("results");
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
