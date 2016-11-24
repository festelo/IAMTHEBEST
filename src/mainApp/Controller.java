package mainApp;

import Test1.MainTest1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import static mainApp.MainApp.ACCOUNT;
import static mainApp.MainApp.WordBase;
import static sample.Controller.STAGE;
import Test1.*;
import settings.MainSettings;


public class Controller implements Initializable {
    public void testBtn(ActionEvent actionEvent) throws Exception {
        List<String> Words = ACCOUNT.InLearning;
        List<String> Translates = new ArrayList<>();
        Random rand = new Random();
        for (String s: Words) {
            List<String>  listTEMP = WordBase.get(s);
            Translates.add(listTEMP.get(rand.nextInt(listTEMP.size())));
        }

        Collections.shuffle(Translates);
        Collections.shuffle(Words);
        new MainTest1(sample.Controller.STAGE, Words, Translates);
    }


    @FXML
    private TableView<TableData> LearnedTable;
    @FXML
    private TableColumn LearnedTableWord;
    @FXML
    private TableColumn LearnedTableTranslate;

    @FXML
    private TableView<TableData> UnLearnedTable;
    @FXML
    private TableColumn UnLearnedTableWord;
    @FXML
    private TableColumn UnLearnedTableTranslate;


    @FXML
    private TableView<TableData> InLearningTable;
    @FXML
    private TableColumn InLearningTableWord;
    @FXML
    private TableColumn InLearningTableTranslate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LearnedTableWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        LearnedTableTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));

        UnLearnedTableWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        UnLearnedTableTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));

        InLearningTableWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        InLearningTableTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));

        InLearningTable.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.InLearning));
        LearnedTable.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.Learned));
        ACCOUNT.UnLearned = GETUnlearned();
        UnLearnedTable.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.UnLearned));
    }

    public List<String> GETUnlearned()
    {
        List<String> returnStr = new ArrayList<>();
        for(String s : WordBase.keySet())
        {
            if(ACCOUNT.Learned.contains(s) || ACCOUNT.InLearning.contains(s)) continue;
            returnStr.add(s);
        }
        return  returnStr;
    }
}