package Test2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTest2 extends Application {

    public MainTest2(Stage primaryStageTest2) throws IOException{
        Parent rootTest2 = FXMLLoader.load(getClass().getResource("test1.fxml"));
        primaryStageTest2.setTitle("Test 2");
        primaryStageTest2.setScene(new Scene(rootTest2));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageTest2) {

    }
}