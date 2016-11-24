package Test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import mainApp.MainApp;
import mainApp.TableData;
import sample.Main;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
public class ControllerTest implements Initializable {
    static public ObservableList<String> Words = FXCollections.observableArrayList();
    static public ObservableList<String> Translates = FXCollections.observableArrayList();
    static public ObservableList<TableData> Result = FXCollections.observableArrayList();
    public TableColumn tableViewWord;
    public TableColumn tableViewTranslate;
    public TableView tableView;
    public ListView<String> ListViewTranslates;
    public ListView<String> ListViewWords;
    public Button OkBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ListViewWords.setItems(Words);
        ListViewTranslates.setItems(Translates);

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
            Words.remove(wordObj);
            Translates.remove(translateObj);
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
                    Words.add(td.getWord());
                    Translates.add(td.getTranslates());
                }
            }
        }
    }

    public void clickOK(ActionEvent actionEvent) throws IOException, TransformerException {
        if(Words.size() == 0 && Translates.size() == 0)
        {
            for (TableData td : Result)
            {
                if (!MainApp.ACCOUNT.WordBase.get(td.getWord()).contains(td.getTranslates()))
                {
                    OkBtn.setText("No!");
                    return;
                }
            }
            MainApp.ACCOUNT.setStage(1);
            MainTest.result = true;
            MainTest.onClosing(MainTest.primaryStageMain);
            MainTest.thisStage.close();
        }
        else
        {
            OkBtn.setText("Not all!");
        }
    }


}