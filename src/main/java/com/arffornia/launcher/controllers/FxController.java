package com.arffornia.launcher.controllers;

import com.arffornia.launcher.Launcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FxController {
    @FXML private Button authBtn;
    @FXML private Button playBtn;

    @FXML protected void onAuthBtnClick() {
        Launcher.getApp().getLauncherController().getMsAuth().authByIds();
    }

    @FXML protected void onPlayBtnClick() {
        Launcher.getApp().getLauncherController().launchGameSequence();
    }
}