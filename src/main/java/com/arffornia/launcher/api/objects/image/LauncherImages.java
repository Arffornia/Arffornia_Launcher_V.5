package com.arffornia.launcher.api.objects.image;

import com.arffornia.launcher.api.ArfforniaApi;
import com.arffornia.launcher.errors.LauncherError;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LauncherImages {
    private static final String REQUEST_URL = "launcherImages";

    private final List<LauncherImage> launcherImages;

    public LauncherImages() {
        this.launcherImages = new ArrayList<>();
    }

    @Nullable
    public static LauncherImages listImages() {
        JsonElement jsonElement = ArfforniaApi.getResponse(REQUEST_URL);

        if(jsonElement == null) { return null; }
        if(!jsonElement.isJsonArray()) {
            new LauncherError("Api response error", "Invalid launcher images struct.");
            return null;
        }

        JsonArray imgUrlArray = jsonElement.getAsJsonArray();
        LauncherImages launcherImages = new LauncherImages();

        for(JsonElement element : imgUrlArray) {
            try {
                launcherImages.launcherImages.add(new LauncherImage(new URL(element.getAsString())));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        return launcherImages;
    }

    public List<LauncherImage> getLauncherImages() {
        return launcherImages;
    }
}
