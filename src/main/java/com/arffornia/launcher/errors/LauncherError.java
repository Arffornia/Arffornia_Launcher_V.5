package com.arffornia.launcher.errors;

import com.arffornia.launcher.Launcher;

public class LauncherError {
    public LauncherError(String title, String msg) {
        String formattedMsg = title + ": " + msg;
        System.err.println(formattedMsg);
        Launcher.getApp().getLogger().err(formattedMsg);

        // TODO add error notif / alert
    }
}
