package Stata;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static tests.Test1.MainTest.onClosing;

public class MainStata
{

    private Stage currentStage;
    private Stage primaryStageMain;

    public MainStata(Stage primaryStageMain) throws IOException
    {
        this.primaryStageMain = primaryStageMain;
        primaryStageMain.hide();
        currentStage = new Stage();
        currentStage.setTitle("Statistic");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("stata.fxml"));
        Parent rootMain = loader.load();
        currentStage.setScene(new Scene(rootMain));
        currentStage.setOnCloseRequest(event -> onClosing(primaryStageMain));
        currentStage.setResizable(false);
        currentStage.showAndWait();
    }


}