package com.arffornia.launcher.listeners;

import javafx.scene.Scene;

public class WindowFocusListener {
    public static void addListener(Scene scene) {
        scene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
            if (newWindow != null) {
                newWindow.focusedProperty().addListener((obs2, oldFocus, newFocus) -> {
                    if (newFocus) {
                        // TODO Refresh value of mc account
                    }
                });
            }
        });
    }
}