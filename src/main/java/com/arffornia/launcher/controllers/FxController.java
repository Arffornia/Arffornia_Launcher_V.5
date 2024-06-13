package com.arffornia.launcher.controllers;

import com.arffornia.launcher.Launcher;
import com.arffornia.launcher.ui.ImagesLoader;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

public class FxController {
    @FXML private Pane bgPane1;
    @FXML private Pane bgPane2;

    @FXML private Button authBtn;
    @FXML private Button playBtn;

    @FXML protected void onAuthBtnClick() {
        Launcher.getApp().getLauncherController().getMsAuth().authByIds();
    }

    @FXML protected void onPlayBtnClick() {
        Launcher.getApp().getLauncherController().launchGameSequence();
    }


    private List<Image> images;

    public void initialize() {
        images = ImagesLoader.loadImagesFromFolder("src/main/resources/com/arffornia/launcher/img/bg/");
        bgPane1.setStyle("-fx-background-image: url('" + images.get(0).getUrl() + "');");

        new Thread(() -> {
            try {
                bgTransition();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void bgTransition() throws InterruptedException {
        int index = 1;

        int transitionDuration = 5000;
        int transitionSleep = 30000;

        Pane pane1 = bgPane1;
        Pane pane2 = bgPane2;
        Pane tmp;

        while (true) {
            pane2.setStyle("-fx-background-image: url('" + images.get(index++).getUrl() + "');");

            if(index == images.size()) {
                index = 0;
            }

            FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(transitionDuration), pane1);
            FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(transitionDuration), pane2);

            bgFadeSwitcher(fadeTransition1, fadeTransition2);

            tmp = pane1;
            pane1 = pane2;
            pane2 = tmp;

            Thread.sleep(transitionSleep);
        }
    }

    private void bgFadeSwitcher(FadeTransition fade1, FadeTransition fade2) {
        fade1.setFromValue(1.0);
        fade1.setToValue(0.0);
        fade1.setCycleCount(1);

        fade2.setFromValue(0.0);
        fade2.setToValue(1.0);
        fade2.setCycleCount(1);

        ParallelTransition parallelTransition = new ParallelTransition(fade1, fade2);
        parallelTransition.play();
    }
}