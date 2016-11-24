package Test1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class MainTest1 {

    public static Stage stageTest1;
    public static Stage primaryStageMain;

    public MainTest1(Stage primaryStageMain, List<String> Words, List<String> Translates) throws Exception {
        this.primaryStageMain = primaryStageMain;
        primaryStageMain.hide();
        ControllerTest1.Translates.addAll(Translates);
        ControllerTest1.Words.addAll(Words);

        stageTest1 = new Stage();
        Parent rootMain = FXMLLoader.load(getClass().getResource("test1.fxml"));
        stageTest1.setTitle("First Test");
        stageTest1.setScene(new Scene(rootMain));
        stageTest1.setOnCloseRequest(event -> onClosing(primaryStageMain));
        stageTest1.show();
    }
    public void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }
}