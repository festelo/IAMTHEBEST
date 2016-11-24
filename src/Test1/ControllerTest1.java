package Test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mainApp.Controller;
import mainApp.TableData;

import java.net.URL;
import java.util.*;

public class ControllerTest1 implements Initializable {
    static public ObservableList<String> Words = FXCollections.observableArrayList();
    static public ObservableList<String> Translates = FXCollections.observableArrayList();
    static public ObservableList<TableData> Result = FXCollections.observableArrayList();
    public TableColumn tableViewWord;
    public TableColumn tableViewTranslate;
    public TableView tableView;
    public ListView<String> ListViewTranslates;
    public ListView<String> ListViewWords;
    public String selectedWord;
    public String selectedTranslate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListViewWords.setItems(Words);
        ListViewTranslates.setItems(Translates);

        tableViewWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        tableViewTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));
        tableView.setItems(Result);
        // TODO (don't really need to do anything here).

    }
    public void ListViewClicked(MouseEvent mouseEvent)
    {
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
        System.out.print(123);
    }
}