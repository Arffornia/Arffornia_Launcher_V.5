package com.arffornia.launcher.api.objects;

import com.arffornia.launcher.api.ArfforniaApi;
import com.arffornia.launcher.errors.LauncherError;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class BestPlayerVote {
    private static final String REQUEST_URL = "best_player_vote/{size}";

    List<PlayerVote> playerVoteList;

    private BestPlayerVote() {
        this.playerVoteList = new ArrayList<>();
    }

    public static BestPlayerVote getBestPlayerVote(int size) {
        JsonElement jsonElement = ArfforniaApi.getResponse(REQUEST_URL.replace("{size}", String.valueOf(size)));

        if (jsonElement == null) {
            return null;
        }

        BestPlayerVote bestPlayerVote = new BestPlayerVote();

        if (!jsonElement.isJsonArray()) {
            new LauncherError("Api response error", "Invalid best player vote struct.");
            return null;
        }

        JsonArray array = jsonElement.getAsJsonArray();

        for (JsonElement elem : array) {
            if (elem.isJsonObject()) {
                JsonObject playerInfoObj = elem.getAsJsonObject();

                if (!(playerInfoObj.has("name") && playerInfoObj.has("vote_count"))) {
                    new LauncherError("Api response error", "Invalid player vote struct.");
                    return null;
                }

                String name = playerInfoObj.getAsJsonPrimitive("name").getAsString();
                int voteCount = playerInfoObj.getAsJsonPrimitive("vote_count").getAsInt();

                PlayerVote playerVote = new PlayerVote(name, voteCount);
                bestPlayerVote.playerVoteList.add(playerVote);
            }
        }

        return bestPlayerVote;
    }

    public List<PlayerVote> getPlayerVoteList() {
        return playerVoteList;
    }
}
