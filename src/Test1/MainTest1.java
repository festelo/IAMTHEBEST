package Test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.List;

import static sample.Controller.STAGE;

public class MainTest1 {
    public MainTest1(Stage primaryStageMain, List<String> Words, List<String> Translates) throws Exception {
        ControllerTest1.Translates.addAll(Translates);
        ControllerTest1.Words.addAll(Words);

        Parent rootMain = FXMLLoader.load(getClass().getResource("test1.fxml"));
        primaryStageMain.setTitle("First test");
        primaryStageMain.setScene(new Scene(rootMain));
    }
}