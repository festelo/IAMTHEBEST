package settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSettings extends Application {

    public MainSettings(Stage primaryStageSettings) throws IOException {
        Parent rootSettings = FXMLLoader.load(getClass().getResource("settings.fxml"));
        primaryStageSettings.setTitle("Settings");
        primaryStageSettings.setScene(new Scene(rootSettings));

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageSettings) {

    }
}