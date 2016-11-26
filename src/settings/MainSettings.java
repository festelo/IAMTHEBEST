package settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static tests.Test1.MainTest.onClosing;

public class MainSettings extends Application {


    private Stage currentStage;

    public MainSettings(Stage primaryStageMain) throws IOException {
        primaryStageMain.hide();
        currentStage = new Stage();
        Parent rootSettings = FXMLLoader.load(getClass().getResource("settings.fxml"));
        currentStage.setTitle("Settings");
        currentStage.setScene(new Scene(rootSettings));
        currentStage.setOnCloseRequest(event -> onClosing(primaryStageMain));
        currentStage.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageSettings) {

    }
}