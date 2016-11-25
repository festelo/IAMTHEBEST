package Test2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by me on 24.11.2016.
 */
public class MainTest {

    public static boolean result;
    public static Stage thisStage;
    public static Stage primaryStageMain;
    public MainTest(Stage primaryStageMain, String Word, List<String> Translates) throws Exception {
        primaryStageMain.hide();
        this.primaryStageMain = primaryStageMain;
        ControllerTest.Translates.clear();
        ControllerTest.Translates.addAll(Translates);
        ControllerTest.Word = Word;

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
