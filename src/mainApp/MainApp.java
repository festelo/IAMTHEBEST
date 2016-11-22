package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp {
    public MainApp(Stage primaryStageMain) throws Exception{
        Parent rootMain = FXMLLoader.load(getClass().getResource("mainApp.fxml"));
        primaryStageMain.setTitle("Learn English");
        primaryStageMain.setScene(new Scene(rootMain));
    }
}