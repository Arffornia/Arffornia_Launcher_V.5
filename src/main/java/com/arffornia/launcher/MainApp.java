package com.arffornia.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main.fxml"));

        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/Crafting_Table.png")));
        stage.setTitle("Arffornia Launcher");

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("css/style.css")));
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}