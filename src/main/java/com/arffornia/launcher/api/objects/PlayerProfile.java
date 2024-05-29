package com.arffornia.launcher.api.objects;

import com.arffornia.launcher.api.ArfforniaApi;
import com.arffornia.launcher.errors.LauncherError;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

public class PlayerProfile {
    private static final String REQUEST_URL = "profile/{playerName}";

    private String name;
    private String uuid;
    private int money;
    private int voteCount;

    private PlayerProfile(String name, String uuid, int money, int voteCount) {
        this.name = name;
        this.uuid = uuid;
        this.money = money;
        this.voteCount = voteCount;
    }

    @Nullable
    public static PlayerProfile getPlayerProfile(String playerName) {
        JsonElement jsonElement = ArfforniaApi.getResponse(REQUEST_URL.replace("{playerName}", playerName));

        if(jsonElement == null) { return null; }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if(!(   jsonObject.has("uuid")
                && jsonObject.has("money")
                && jsonObject.has("vote_count"))) {
            new LauncherError("Api response error", "Invalid player profile struct.");
            return null;
        }

        return new PlayerProfile(
                playerName,
                jsonObject.getAsJsonPrimitive("uuid").getAsString(),
                jsonObject.getAsJsonPrimitive("money").getAsInt(),
                jsonObject.getAsJsonPrimitive("vote_count").getAsInt()
        );
    }

    public String toString() {
        return String.format("name: %s, money: %d, voteCount: %d", name, money, voteCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public String getUuid() {

        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
