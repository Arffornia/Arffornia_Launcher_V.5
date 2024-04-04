package com.arffornia.launcher;

import com.arffornia.launcher.auth.MsAuth;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hello world!");
        new MsAuth().authenticateMS();
        //new McUpdater().update();
    }
}