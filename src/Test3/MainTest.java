package Test3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTest extends Application {

    public MainTest(Stage primaryStageTest3) throws IOException {
        Parent rootTest3 = FXMLLoader.load(getClass().getResource("test.fxml"));
        primaryStageTest3.setTitle("Third Test");
        primaryStageTest3.setScene(new Scene(rootTest3));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageTest3) {

    }
}