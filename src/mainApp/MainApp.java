package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStageMain) throws Exception{
        Parent rootMain = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStageMain.setTitle("Learn English");
        primaryStageMain.setScene(new Scene(rootMain, 250, 150));
        primaryStageMain.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}