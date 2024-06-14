package com.arffornia.launcher;

import com.arffornia.launcher.controllers.FxController;
import com.arffornia.launcher.controllers.LauncherController;
import com.arffornia.launcher.listeners.WindowFocusListener;
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
import java.util.Objects;

public class Launcher extends Application {
    private static final String GAME_DIR_NAME = "Arffornia_V.5";

    private static Launcher app;

    private ILogger logger;
    private Path gameDir;
    private Saver saver;
    private final LauncherController launcherController;

    public Launcher() {
        app = this;

        this.launcherController = new LauncherController();

        this.initGameDir();
        this.initLogger();
        this.initSaver();

        this.launcherController.onStartupEvent();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("main.fxml"));

        stage.setTitle("Arffornia Launcher");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("img/Crafting_Table.png"))));

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("css/style.css")));

        WindowFocusListener.addListener(scene);

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

    private void initSaver() {
        saver = new Saver(gameDir.resolve("launcher.properties"));
        saver.load();
    }

    public static Launcher getApp() {
        return app;
    }

    public Saver getSaver() {
        return saver;
    }

    public Path getGameDir() {
        return gameDir;
    }

    public ILogger getLogger() {
        return logger;
    }

    public LauncherController getLauncherController() {
        return launcherController;
    }
}