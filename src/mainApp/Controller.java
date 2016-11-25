package mainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

import static mainApp.AccountData.WordBase;
import static mainApp.MainApp.ACCOUNT;


public class Controller implements Initializable {
    public void testBtn(ActionEvent actionEvent) throws Exception {
        new tests.Tests(Main.primaryStageMain);
    }
    MainApp Main;
    public Controller(MainApp app)
    {
        Main = app;
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

        InLearningTable.setItems(TableData.ToTableData.ToTableDataList(MainApp.ACCOUNT.InLearning));
        LearnedTable.setItems(TableData.ToTableData.ToTableDataList(MainApp.ACCOUNT.Learned));
        ACCOUNT.UnLearned = GETUnlearned();
        UnLearnedTable.setItems(TableData.ToTableData.ToTableDataList(MainApp.ACCOUNT.UnLearned));
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