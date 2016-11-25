package Test1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class MainTest {
    public static boolean result;
    public static Stage thisStage;
    public static Stage primaryStageMain;
    public MainTest(Stage primaryStageMain, List<String> Words, List<String> Translates) throws Exception {
        this.primaryStageMain = primaryStageMain;
        primaryStageMain.hide();
        ControllerTest.Translates.clear();
        ControllerTest.Translates.addAll(Translates);
        ControllerTest.Words.clear();
        ControllerTest.Words.addAll(Words);

        result = false;
        thisStage = new Stage();
        Parent rootMain = FXMLLoader.load(getClass().getResource("test.fxml"));
        thisStage.setTitle("First Test");
        thisStage.setScene(new Scene(rootMain));
        thisStage.setOnCloseRequest(event -> onClosing(primaryStageMain));
        thisStage.showAndWait();
    }
    public static void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }
}