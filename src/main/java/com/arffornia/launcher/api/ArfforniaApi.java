package com.arffornia.launcher.api;

import com.arffornia.launcher.errors.LauncherError;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ArfforniaApi {
    private static final String API_URL = "http://localhost/api/";

    @Nullable
    public static JsonElement getResponse(String apiRequest) {
        try {
            URL url = new URL(API_URL + apiRequest);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                new LauncherError("Api request error", responseCode + " error code");
                return null;
            }

            try (Reader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                return JsonParser.parseReader(reader);
            }
        } catch (IOException e) {
            new LauncherError("Api request error", e.getMessage());
        }

        return null;
    }
}
