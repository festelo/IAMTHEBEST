package Stata;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import mainApp.MainApp;
import mainApp.TableData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static tests.Test1.MainTest.onClosing;

public class MainStata extends Application {

    @FXML
    public ListView<String> rightAnswers;

    @FXML
    public ListView<String> wrongAnswers;

    @FXML
    public ListView<String> rightAnswersWord;
    @FXML
    public ListView<String> rightAnswersTranslate;
    @FXML
    public ListView<String> wrongAnswersWord;
    @FXML
    public ListView<String> wrongAnswersTranslate;

    public ListView<String> asd;
    public ListView<String> dsa;

    private Stage currentStage;
    private Stage primaryStageMain;

    public MainStata(Stage primaryStageMain) throws IOException {
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

        drawStata();
    }

    public  void Close() {
        currentStage.close();
        primaryStageMain.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void drawStata() {
        System.out.println(MainApp.ACCOUNT.UnSuccessfulList[0]);
        //rightAnswers.setCellFactory(new PropertyValueFactory<MainApp.ACCOUNT.UnSuccessfulList[0], MainApp.ACCOUNT.UnSuccessfulList[0]>);
        rightAnswersWord.setCellValueFactory();
    }

    public void initialize(URL location, ResourceBundle resources) {
    }
}