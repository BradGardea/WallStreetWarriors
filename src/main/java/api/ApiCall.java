package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCall implements IApiCall {

    public static String getClosePrice(String ticker, String apiKey) {

            try {
                // Specify the URL for the HTTP request
                String urlString = "https://api.finazon.io/latest/time_series?dataset=us_stocks_essential&ticker=" + ticker + "&interval=1m&apikey=" + apiKey;

                // Create OkHttpClient instance
                OkHttpClient client = new OkHttpClient();

                // Create Request object
                Request request = new Request.Builder()
                        .url(urlString)
                        .build();

                // Execute the request
                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    // Parse the response as a JsonNode using Jackson
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(response.body().byteStream());

                    // Output the JsonNode
                    String closePrice = jsonNode.get("data").get(0).get("c").toString();
//                    System.out.println(jsonNode.get("data").get(0).get("c"))
                    return closePrice;
                } else {
                    // Handle the error
                    System.out.println(String.format("Error %s: %s", ticker, response.code()));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    public static void main(String[] args) {
        String msftClosePrice = getClosePrice("CS", Credentials.apiKey);
        System.out.println(msftClosePrice);
    }

}
