package com.arffornia.launcher.controllers;

import com.arffornia.launcher.Launcher;
import com.arffornia.launcher.ui.ImagesLoader;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.List;

public class FxController {
    @FXML public ImageView bgImgView;

    @FXML private Button authBtn;
    @FXML private Button playBtn;

    @FXML protected void onAuthBtnClick() {
        Launcher.getApp().getLauncherController().getMsAuth().authByIds();
    }

    @FXML protected void onPlayBtnClick() {
        Launcher.getApp().getLauncherController().launchGameSequence();
    }


    private List<Image> images;
    private int currentIndex = 0;
    private final Duration transitionDuration = Duration.seconds(1);
    private final Duration displayDuration = Duration.seconds(10);

    public void initialize() {
        images = ImagesLoader.loadImagesFromFolder("src/main/resources/com/arffornia/launcher/img/bg/");


    }
}