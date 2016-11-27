package tests.Test2;

import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import mainApp.AccountData;
import mainApp.MainApp;

import java.net.URL;
import java.util.*;

public class ControllerTest implements Initializable {
    public ListView<String> listView;
    public Label label;
    MainTest Main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(label.getText() + Main.Word);
        listView.setItems(Main.Translates);
    }
    public ControllerTest(MainTest main)
    {
        Main = main;
    }

    public void clickOK(ActionEvent ev) throws Exception {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if(Main.Test == 1) {
            if (MainApp.ACCOUNT.WordBase.get(Main.Word).contains(selectedItem)) {
                MainApp.ACCOUNT.InLearningGet(selectedItem).upStage(false);
                for(AccountData.InLearning s : MainApp.ACCOUNT.InLearningGet(Main.Translates))
                {
                    s.upStage(false);
                }
                MainApp.ACCOUNT.Nodes.saveXML();
                MainApp.ACCOUNT.SuccessfulList[1].add(Main.Word);
                Main.result = true;
                Main.Close();
            }
            else
                {
                    Main.result = false;
                    Main.Close();
                }
        }
        else{
            if(MainApp.ACCOUNT.WordBase.get(selectedItem).contains(Main.Word))
            {
                MainApp.ACCOUNT.InLearningGet(selectedItem).upStage(false);
                for(AccountData.InLearning s : MainApp.ACCOUNT.InLearningGet(Main.Translates))
                {
                    s.upStage(false);
                }
                MainApp.ACCOUNT.Nodes.saveXML();
                MainApp.ACCOUNT.SuccessfulList[2].add(Main.Word);
                Main.result = true;
                Main.Close();
            }
            else
                {
                    Main.result = false;
                    Main.Close();
                }
        }
    }
}