package Tests.Test2;

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
    public static int Test;
    public MainTest(Stage primaryStageMain, String Word, List<String> Translates, int Test) throws Exception {
        this.Test = Test;
        primaryStageMain.hide();
        this.primaryStageMain = primaryStageMain;
        ControllerTest.Translates.clear();
        ControllerTest.Translates.addAll(Translates);
        ControllerTest.Word = Word;

        result = false;
        thisStage = new Stage();
        if(Test == 2) thisStage.setTitle("Second Test");
        else thisStage.setTitle("Third Test");
        Parent rootMain = FXMLLoader.load(getClass().getResource("test.fxml"));
        thisStage.setScene(new Scene(rootMain));
        thisStage.setOnCloseRequest(event -> onClosing(primaryStageMain));
        thisStage.showAndWait();
    }
    public static void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }
}
