module com.arffornia.launcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.web;

    requires flowupdater;
    requires flowmultitools;
    requires openlauncherlib;
    requires openauth;
    requires org.jetbrains.annotations;
    requires com.google.gson;


    opens com.arffornia.launcher to javafx.fxml;
    exports com.arffornia.launcher;
    exports com.arffornia.launcher.controllers;
    opens com.arffornia.launcher.controllers to javafx.fxml;
    exports com.arffornia.launcher.api;
    opens com.arffornia.launcher.api to javafx.fxml;
}