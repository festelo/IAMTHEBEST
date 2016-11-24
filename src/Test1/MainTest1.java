package Test1;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mainApp.MainApp;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainTest1 {
    public MainTest1(Stage primaryStageMain, List<String> Words, List<String> Translates, Scene scene) throws Exception {

        primaryStageMain.hide();
        ControllerTest1.Translates.addAll(Translates);
        ControllerTest1.Words.addAll(Words);

        Stage StageMain = new Stage();
        Parent rootMain = FXMLLoader.load(getClass().getResource("test1.fxml"));
        StageMain.setTitle("First Test");
        StageMain.setScene(new Scene(rootMain));
        StageMain.setOnCloseRequest(event -> onClosing(primaryStageMain));
        StageMain.show();
    }
    public void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }
}