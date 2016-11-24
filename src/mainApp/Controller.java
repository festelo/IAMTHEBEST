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

import static sample.Controller.STAGE;
import Test1.*;
import settings.MainSettings;

public class Controller implements Initializable {
    public void testBtn(ActionEvent actionEvent) throws Exception {
        List<String> Words = new ArrayList<>();
        List<String> Translates = new ArrayList<>();
        Words.add("Слово");
        Words.add("Перевод");
        Translates.add("Translate");
        Translates.add("Word");
        new MainTest1(sample.Controller.STAGE, Words, Translates);
    }

    public static class TableData
    {
        public static class ToTableData
        {
            public static ObservableList<TableData> ToTableDataList(List<String> list)
            {
                ObservableList<TableData> toReturn = FXCollections.observableArrayList();;
                for (String s: list)
                {
                    String TEMPString = "";
                    for(String a: WordBase.get(s))
                        TEMPString += a + ", ";
                    if(TEMPString != "")
                        TEMPString = TEMPString.substring(0, TEMPString.length()-2);
                    toReturn.add(new TableData(s, TEMPString));
                }
                return toReturn;
            }
        }

        private String Word;
        private String Translates;
        public void setWord(String Word){ this.Word = Word; }
        public String getWord() { return this.Word; }
        public void setTranslates(String Translates){ this.Translates = Translates; }
        public String getTranslates() { return this.Translates; }

        public TableData(String Word, String Translates)
        {
            this.Word = Word;
            this.Translates = Translates;
        }

        public TableData() {}
    }
    static AccountData ACCOUNT;
    static Map<String, List<String>> WordBase = new HashMap<>();


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

    public void clickTesting(ActionEvent actionEvent) throws IOException {
        System.out.println("clickedTesting");
        new MainTest1(STAGE);
    }

    public void clickSetting() throws IOException{
        System.out.println("clickSetting");
        new MainSettings(STAGE);
    }
}