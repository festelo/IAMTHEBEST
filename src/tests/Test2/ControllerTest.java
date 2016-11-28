package tests.Test2;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;

import mainApp.AccountData;
import mainApp.MainApp;

public class ControllerTest implements Initializable
{

    MainTest Main;

    public ListView<String> listView;
    public Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        label.setText(label.getText() + Main.Word);
        listView.setItems(Main.Translates);
    }

    //Нажатие на кнопку NEXT во втором или третьем тесте
    public void clickOK(ActionEvent ev) throws Exception
    {

        String selectedItem = listView.getSelectionModel().getSelectedItem();

        //Если тест второй
        if(Main.Test == 1) {
            if (MainApp.ACCOUNT.WordBase.get(Main.Word).contains(selectedItem))
            {
                MainApp.ACCOUNT.InLearningGet(Main.Word).upStage(false);
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
                MainApp.ACCOUNT.UnSuccessfulList[1].add(Main.Word);
                Main.result = false;
                Main.Close();
            }
        }

        //Если тест третий
        else
        {
            if(MainApp.ACCOUNT.WordBase.get(selectedItem).contains(Main.Word))
            {
                MainApp.ACCOUNT.InLearningGet(selectedItem).upStage(false);
                for(AccountData.InLearning s : MainApp.ACCOUNT.InLearningGet(Main.Translates))
                {
                    s.upStage(false);
                }
                MainApp.ACCOUNT.Nodes.saveXML();
                MainApp.ACCOUNT.SuccessfulList[2].add(selectedItem);
                Main.result = true;
                Main.Close();
            }
            else
            {
                MainApp.ACCOUNT.UnSuccessfulList[2].add(selectedItem);
                Main.result = false;
                Main.Close();
            }
        }

    }

    public ControllerTest(MainTest main)
    {
        Main = main;
    }


}