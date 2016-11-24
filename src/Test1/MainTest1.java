package Test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Controller.STAGE;

public class MainTest1 extends Application {

    public MainTest1(Stage primaryStageTest1) throws IOException {
        Parent rootTest1 = FXMLLoader.load(getClass().getResource("test1.fxml"));
        primaryStageTest1.setTitle("Test 1");
        primaryStageTest1.setScene(new Scene(rootTest1));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageTest1) {

    }
}