package tests.Test2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class MainTest {

    public boolean result;
    public int Test;

    public String Word;
    public ObservableList<String> Translates = FXCollections.observableArrayList();
    private Stage thisStage;
    private Stage primaryStageMain;
    public MainTest(Stage primaryStageMain, String Word, List<String> Translates, int Test) throws Exception {
        this.Test = Test;
        primaryStageMain.hide();
        this.primaryStageMain = primaryStageMain;
        this.Translates.addAll(Translates);
        this.Word = Word;

        result = false;
        thisStage = new Stage();
        if(Test == 1) thisStage.setTitle("Second Test");
        else if(Test == 2) thisStage.setTitle("Third Test");
        ControllerTest test = new ControllerTest(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        loader.setController(test);
        Parent rootMain = loader.load();
        thisStage.setScene(new Scene(rootMain));
        thisStage.setOnCloseRequest(event -> onClosing(primaryStageMain));
        thisStage.showAndWait();
    }
    public void onClosing(Stage primaryStageMain)
    {
        primaryStageMain.show();
    }


    public void Close()
    {
        onClosing(primaryStageMain);
        thisStage.close();
    }


}
