package com.arffornia.launcher.controllers;

import com.arffornia.launcher.auth.MsAuth;
import com.arffornia.launcher.errors.LauncherError;

public class LauncherController {
    private final MsAuth msAuth;

    public LauncherController() {
        this.msAuth = new MsAuth();
    }

    public void onStartupEvent() {
        // Check auth by token
        if(this.msAuth.tryToAuthByToken()) {
            // TODO load login view
        }
    }

    public void launchGameSequence() {
        // check auth
        if(!this.msAuth.getIsAuth()) {
            new LauncherError("Auth error", "Please login with your Minecraft account.");
            return;
        }

        // update game
        McController mcController = new McController();
        try {
            mcController.update();
        } catch (Exception e) {
            new LauncherError("Mc updater error", e.toString());
            return;
        }

        // launch game
        try {
            mcController.launch();
        } catch (Exception e) {
            new LauncherError("Mc launch error", e.toString());
        }
    }

    public MsAuth getMsAuth() {
        return msAuth;
    }
}
