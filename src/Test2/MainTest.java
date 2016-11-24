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
    public MainTest(Stage primaryStageMain, String Word, List<String> Translates) throws Exception {
        primaryStageMain.hide();
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
    public void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }
}
