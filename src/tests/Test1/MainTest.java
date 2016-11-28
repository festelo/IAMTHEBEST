package tests.Test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class MainTest {

    public ObservableList<String> Words = FXCollections.observableArrayList();
    public ObservableList<String> Translates = FXCollections.observableArrayList();
    public boolean result = false;

    Stage primaryStageMain;
    Stage thisStage;

    //Создание окна
    public MainTest(Stage primaryStageMain, List<String> Words, List<String> Translates) throws Exception {
        this.primaryStageMain = primaryStageMain;
        primaryStageMain.hide();

        this.Translates.addAll(Translates);
        this.Words.addAll(Words);

        ControllerTest test = new ControllerTest(this);

        result = false;
        thisStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        loader.setController(test);
        Parent rootMain = loader.load();
        thisStage.setTitle("First Test");
        thisStage.setScene(new Scene(rootMain));
        thisStage.setOnCloseRequest(event -> onClosing(primaryStageMain));
        thisStage.showAndWait();
    }

    //Закрытие окна
    public static void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }

    public void Close()
    {
        onClosing(primaryStageMain);
        thisStage.close();
    }
}