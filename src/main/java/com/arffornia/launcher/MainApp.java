package com.arffornia.launcher;

import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class MainApp extends Application {
    private static final String GAME_DIR_NAME = "Arffornia_V.5";
    private static ILogger logger;
    private static Path gameDir;
    private static Saver saver;

    public static void main(String[] args) {
        launch();
    }

    public MainApp() {
        this.initGameDir();
        this.initLogger();
        this.initSaver();
    }

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

    private void initGameDir() {
        gameDir = GameDirGenerator.createGameDir(GAME_DIR_NAME, true);
    }

    private void initLogger() {
        logger = new Logger("[Arffornia_Launcher]", Path.of(gameDir.toString(), "launcher.log"));
        if (!gameDir.toFile().exists()) {
            if (!gameDir.toFile().mkdir()) {
                logger.err("Unable to create game folder");
            }
        }
    }

    public static Saver getSaver() {
        return saver;
    }

    public static Path getGameDir() {
        return gameDir;
    }

    private void initSaver() {
        saver = new Saver(gameDir.resolve("launcher.properties"));
        saver.load();
    }

    public static ILogger getLogger() {
        return logger;
    }
}