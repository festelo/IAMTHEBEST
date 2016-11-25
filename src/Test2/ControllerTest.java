package Test2;

import Test1.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import mainApp.MainApp;

import java.net.URL;
import java.util.*;

public class ControllerTest implements Initializable {
    public static String Word;
    static public ObservableList<String> Translates = FXCollections.observableArrayList();
    public ListView<String> listView;
    public Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(label.getText() + Word);
        listView.setItems(Translates);
    }

    public void clickOK(ActionEvent ev) throws Exception {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if(MainApp.ACCOUNT.WordBase.get(Word).contains(selectedItem))
        {
            MainApp.ACCOUNT.setStage(2);
            MainTest.result = true;
            MainTest.onClosing(MainTest.primaryStageMain);
            MainTest.thisStage.close();
        }
        else ((Button)ev.getSource()).setText("No!");
    }
}