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
        Boolean next = false;
        if(ACCOUNT.getStage() == 0) {
            List<String> Words = ACCOUNT.InLearning;
            List<String> Translates = new ArrayList<>();
            Random rand = new Random();
            for (String s : Words) {
                List<String> listTEMP = WordBase.get(s);
                Translates.add(listTEMP.get(rand.nextInt(listTEMP.size())));
            }

            Collections.shuffle(Translates);
            Collections.shuffle(Words);
            Test1.MainTest dialog =  new Test1.MainTest(sample.Controller.STAGE, Words, Translates);
            next = dialog.result;
        }
        if(ACCOUNT.getStage() == 1 || next){
            //new MainTest2(sample.Controller.STAGE, new Stage());
        }
        if(ACCOUNT.getStage() == 2 || next){
            new Test3.MainTest(sample.Controller.STAGE);
        }
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