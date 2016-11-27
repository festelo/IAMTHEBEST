package settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static tests.Test1.MainTest.onClosing;

public class MainSettings{


    private Stage currentStage;

    public MainSettings(Stage primaryStageMain) throws IOException {
        primaryStageMain.hide();
        currentStage = new Stage();
        currentStage.setTitle("Settings");
        ControllerSettings controllerSettings = new ControllerSettings(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        loader.setController(controllerSettings);
        Parent rootMain = loader.load();
        currentStage.setScene(new Scene(rootMain));
        currentStage.setOnCloseRequest(event -> onClosing(primaryStageMain));
        currentStage.setResizable(false);
        currentStage.showAndWait();

    }
    public  void Close() {
        currentStage.close();
    }
}