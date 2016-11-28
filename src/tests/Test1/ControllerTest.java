package tests.Test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import mainApp.AccountData;
import mainApp.MainApp;
import mainApp.TableData;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
public class ControllerTest implements Initializable {
    public ObservableList<TableData> Result = FXCollections.observableArrayList();
    public TableColumn tableViewWord;
    public TableColumn tableViewTranslate;
    public TableView tableView;
    public ListView<String> ListViewTranslates;
    public ListView<String> ListViewWords;
    public Button OkBtn;
    MainTest Main;

    public ControllerTest(MainTest Main)
    {
        this.Main = Main;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ListViewWords.setItems(Main.Words);
        ListViewTranslates.setItems(Main.Translates);

        tableViewWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        tableViewTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));
        tableView.setItems(Result);

    }
    
    String selectedObj;
    public void ListViewClicked(MouseEvent mouseEvent)
    {
        ListView thisList = (ListView)mouseEvent.getSource();
        String nowSelectObj = (String)thisList.getSelectionModel().getSelectedItem();
        if(selectedObj == nowSelectObj) {
            thisList.getSelectionModel().clearSelection();
            selectedObj = null;
        }
        else
            selectedObj = (String) thisList.getSelectionModel().getSelectedItem();

        String wordObj = ListViewWords.getSelectionModel().getSelectedItem();
        String translateObj = ListViewTranslates.getSelectionModel().getSelectedItem();
        if(wordObj != null && translateObj != null)
        {
            Main.Words.remove(wordObj);
            Main.Translates.remove(translateObj);
            Result.add(new TableData(wordObj, translateObj));
            ListViewWords.getSelectionModel().clearSelection();
            ListViewTranslates.getSelectionModel().clearSelection();
        }
    }

    public void tableViewClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                TableData td = (TableData) tableView.getSelectionModel().getSelectedItem();
                if(td != null) {
                    Result.remove(td);
                    Main.Words.add(td.getWord());
                    Main.Translates.add(td.getTranslates());
                }
            }
        }
    }

    public void clickOK(ActionEvent actionEvent) throws IOException, TransformerException {


            for (TableData td : Result)
            {
                if (!MainApp.ACCOUNT.WordBase.get(td.getWord()).contains(td.getTranslates()))
                {
                    MainApp.ACCOUNT.UnSuccessfulList[0].add(td.getWord());
                }
                else {
                    MainApp.ACCOUNT.InLearningGet(td.getWord()).upStage(false);
                    MainApp.ACCOUNT.SuccessfulList[0].add(td.getWord());
                }
            }
            MainApp.ACCOUNT.Nodes.saveXML();
            Main.result = true;
            Main.Close();
    }


}