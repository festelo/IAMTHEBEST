package Stata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStata extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageTest1) throws IOException{
        Parent rootTest1 = FXMLLoader.load(getClass().getResource("stata.fxml"));
        primaryStageTest1.setTitle("Statistics");
        primaryStageTest1.setScene(new Scene(rootTest1));
    }
}