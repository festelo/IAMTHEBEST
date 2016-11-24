package Test2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTest2 extends Application {

    public MainTest2(Stage primaryStageMain) throws IOException{
        primaryStageMain.hide();
        Stage StageTest2 = new Stage();
        Parent rootMain = FXMLLoader.load(getClass().getResource("test2.fxml"));
        StageTest2.setTitle("Second Test");
        StageTest2.setScene(new Scene(rootMain));
        StageTest2.setOnCloseRequest(event -> onClosing(primaryStageMain));
        StageTest2.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStageTest2) { }
    public void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }
}