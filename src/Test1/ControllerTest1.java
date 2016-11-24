package Test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

public class ControllerTest1 implements Initializable {
    static public ObservableList<String> Words = FXCollections.observableArrayList();
    static public ObservableList<String> Translates = FXCollections.observableArrayList();
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
        // TODO (don't really need to do anything here).

    }
    public void ListViewClicked(MouseEvent mouseEvent)
    {
        Boolean word = false;
        if(mouseEvent.getSource() == tableViewWord) {
            word = true;
        }
    }
}