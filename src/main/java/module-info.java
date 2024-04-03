module com.arffornia.launcher {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.arffornia.launcher to javafx.fxml;
    exports com.arffornia.launcher;
}