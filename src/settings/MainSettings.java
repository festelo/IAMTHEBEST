package settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSettings extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageTest1) throws IOException{
        Parent rootTest1 = FXMLLoader.load(getClass().getResource("settings.fxml"));
        primaryStageTest1.setTitle("Settings");
        primaryStageTest1.setScene(new Scene(rootTest1));
    }
}