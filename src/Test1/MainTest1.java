package Test1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainTest1 {
    public MainTest1(Stage primaryStageMain, List<String> Words, List<String> Translates) throws Exception {

        ControllerTest1.Translates.addAll(Translates);
        ControllerTest1.Words.addAll(Words);

        Parent rootMain = FXMLLoader.load(getClass().getResource("test1.fxml"));
        primaryStageMain.setTitle("First Test");
        primaryStageMain.setScene(new Scene(rootMain));

        addWords(Words);
    }

    public void addWords(List<String> Words) {

    }
}