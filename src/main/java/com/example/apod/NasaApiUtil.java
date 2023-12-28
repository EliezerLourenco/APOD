package com.example.apod;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class providing methods to interact with NASA's API. It includes functionality to fetch data from the API
 * and parse JSON responses.
 */
public class NasaApiUtil {


    /**
     * Fetches data from the specified API URL.
     *
     * @param apiUrl The URL of the API endpoint.
     * @return A string containing the JSON response from the API.
     * @throws IOException If there is an issue with the network connection or server.
     */
    public static String fetchDataFromAPI(String apiUrl) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check for successful response code or throw error
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }

            // Read the response
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return json.toString();

        } finally {
            // Clean up
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Parses the given JSON string and creates an AstronomyPictureData object.
     *
     * @param json The JSON string to parse.
     * @return An AstronomyPictureData object containing the parsed data.
     */

    public static AstronomyPictureData parseJson(String json) {
        JSONObject jsonObject = new JSONObject(json);

        String url = jsonObject.getString("url");
        String title = jsonObject.getString("title");
        String explanation = jsonObject.getString("explanation");
        String date = jsonObject.optString("date", ""); // to avoid JSONException if the field is not present
        String mediaType = jsonObject.optString("media_type", "image"); //Default to image

        return new AstronomyPictureData(url, title, explanation, date, mediaType);
    }
}
