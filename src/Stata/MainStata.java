package Stata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class MainStata extends Application {

    public MainStata(Stage primaryStageStata) throws IOException {
        Parent rootStata = FXMLLoader.load(getClass().getResource("stata.fxml"));
        primaryStageStata.setTitle("Statistics");
        primaryStageStata.setScene(new Scene(rootStata));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageStata) {

    }
}