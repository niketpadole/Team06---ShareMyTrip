package com.axis.team6.coderiders.sharemytrip.farecalculationservice.service;

import com.axis.team6.coderiders.sharemytrip.farecalculationservice.exception.InvalidLocationException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GoogleMapsService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";
    private static final Logger LOGGER = Logger.getLogger(GoogleMapsService.class.getName());
    private static final String GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/json";

    public float getDistance(String fromCoordinates, String toCoordinates) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?origins=%s&destinations=%s&key=%s&mode=driving",
                BASE_URL, fromCoordinates, toCoordinates, apiKey);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            LOGGER.log(Level.INFO, "Google Maps API response: {0}", responseBody);

            JSONObject jsonObject = new JSONObject(responseBody);

            try {
                if (!jsonObject.has("rows")) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONArray rows = jsonObject.getJSONArray("rows");
                if (rows.length() == 0) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONObject elementsObject = rows.getJSONObject(0);
                if (!elementsObject.has("elements")) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONArray elements = elementsObject.getJSONArray("elements");
                if (elements.length() == 0) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONObject distanceObject = elements.getJSONObject(0).getJSONObject("distance");
                double distanceInMeters = distanceObject.getDouble("value");

                return (float) (distanceInMeters / 1000); // Convert meters to kilometers
            } catch (org.json.JSONException e) {
                throw new InvalidLocationException("Enter correct locations");
            }
        }
    }

    public String getCoordinates(String location) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?address=%s&key=%s", GEOCODE_URL, location, apiKey);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            LOGGER.log(Level.INFO, "Google Geocoding API response: {0}", responseBody);

            JSONObject jsonObject = new JSONObject(responseBody);

            try {
                if (!jsonObject.has("results")) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONArray results = jsonObject.getJSONArray("results");
                if (results.length() == 0) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONObject locationObject = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                double lat = locationObject.getDouble("lat");
                double lng = locationObject.getDouble("lng");

                return lat + "," + lng;
            } catch (org.json.JSONException e) {
                throw new InvalidLocationException("Enter correct locations");
            }
        }
    }

    public String getDuration(String fromCoordinates, String toCoordinates) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?origins=%s&destinations=%s&key=%s&mode=driving",
                BASE_URL, fromCoordinates, toCoordinates, apiKey);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            LOGGER.log(Level.INFO, "Google Maps API response: {0}", responseBody);

            JSONObject jsonObject = new JSONObject(responseBody);

            try {
                if (!jsonObject.has("rows")) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONArray rows = jsonObject.getJSONArray("rows");
                if (rows.length() == 0) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONObject elementsObject = rows.getJSONObject(0);
                if (!elementsObject.has("elements")) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONArray elements = elementsObject.getJSONArray("elements");
                if (elements.length() == 0) {
                    throw new InvalidLocationException("Enter correct locations");
                }

                JSONObject durationObject = elements.getJSONObject(0).getJSONObject("duration");
                double durationInSeconds = durationObject.getDouble("value");

                long totalHours = (long)durationInSeconds / 3600;
                long minutes = (long) ((durationInSeconds % 3600) / 60);
                long seconds = (long) (durationInSeconds % 60);

                return String.format("%d:%02d:%02d", totalHours, minutes, seconds);
            } catch (org.json.JSONException e) {
                throw new InvalidLocationException("Enter correct locations");
            }
        }
    }
}
