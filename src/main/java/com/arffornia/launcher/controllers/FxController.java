package com.arffornia.launcher.controllers;

import com.arffornia.launcher.Launcher;
import com.arffornia.launcher.ui.ImagesLoader;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class FxController {
    @FXML private Pane bgPane1;
    @FXML private Pane bgPane2;

    //Pages:
    @FXML private AnchorPane profilePage;
    @FXML private AnchorPane mcPage;
    @FXML private AnchorPane photoPage;
    @FXML private AnchorPane notifPage;
    @FXML private AnchorPane settingsPage;

    //Scroll Pane
    @FXML private ScrollPane notifScrollPane;

    @FXML private Button authBtn;
    @FXML private Button playBtn;

    @FXML protected void onAuthBtnClick() {
        Launcher.getApp().getLauncherController().getMsAuth().authByIds();
    }

    @FXML protected void onPlayBtnClick() {
        Launcher.getApp().getLauncherController().launchGameSequence();
    }

    public void initialize() {
        this.initImagesCarousel();
        this.initScrollPaneSettings();
    }

    public void shutdown() {
        System.out.println("Stop thread");

        if(bgThread != null) {
            bgThread.interrupt();
        }
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

    private List<Image> images;
    private Thread bgThread = null;

    private void initImagesCarousel() {
        // Image carousel (need to fix zombie thread)
        images = ImagesLoader.loadImagesFromFolder(Launcher.getApp().getGameDir().resolve("bgImages"));

        // No images case
        if(images.isEmpty()) {
            URL defaultImgUrl = this.getClass().getResource("/com/arffornia/launcher/img/bg/mainBG.png");
            bgPane1.setStyle("-fx-background-image: url('" + defaultImgUrl + "');");
            return;
        }

        bgPane1.setStyle("-fx-background-image: url('" + images.get(0).getUrl() + "');");

        bgThread = new Thread(() -> {
            try {
                bgTransition();
            } catch (InterruptedException ignored) {
            }
        });

        bgThread.start();
    }

    private void initScrollPaneSettings() {
        final double SPEED = 0.00075;

        notifScrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            notifScrollPane.setVvalue(notifScrollPane.getVvalue() - deltaY);
        });
    }

    private void pageSwitcher(int pageIndex) {
        AnchorPane[] pages = new AnchorPane[] { profilePage, mcPage, photoPage, notifPage, settingsPage };
        for(int i = 0; i < pages.length; i++) {
            if(i == pageIndex) {
                pages[i].setVisible(true);
            } else {
                pages[i].setVisible(false);
            }
        }
    }

    @FXML
    private void goToProfilePage() {
        this.pageSwitcher(0);
    }

    @FXML
    private void goToMcPage() {
        this.pageSwitcher(1);
    }

    @FXML
    private void goToPhotoPage() {
        this.pageSwitcher(2);
    }

    @FXML
    private void goToNotifPage() {
        this.pageSwitcher(3);
    }

    @FXML
    private void goToSettingsPage() {
        this.pageSwitcher(4);
    }
}