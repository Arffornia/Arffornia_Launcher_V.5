package com.arffornia.launcher.utils;

import javafx.scene.image.Image;
import org.jetbrains.annotations.Nullable;


public class PlayerSkin {
    private static final String BASE_URL = "https://minotar.net/avatar/";

    @Nullable
    public static Image getPlayerHead(String playerName, int size) {
        Image playerHeadImg = new Image(String.format("%s%s/%d", BASE_URL, playerName, size));

        if(playerName.isEmpty()) {
            return null;
        }

        return playerHeadImg;
    }
}
