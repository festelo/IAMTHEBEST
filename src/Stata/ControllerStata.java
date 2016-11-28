package Stata;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mainApp.AccountData;
import mainApp.MainApp;
import mainApp.TableData;
import sample.*;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.*;

import static mainApp.MainApp.ACCOUNT;

public class ControllerStata implements Initializable {
    static Map<String, List<String>> WordBase = new HashMap<>();

    @FXML
    public TableView<TableData> rightAnswers;

    @FXML
    public TableView<TableData> wrongAnswers;

    @FXML
    public TableColumn rightAnswersWord;
    @FXML
    public TableColumn rightAnswersTranslate;
    @FXML
    public TableColumn wrongAnswersWord;
    @FXML
    public TableColumn wrongAnswersTranslate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rightAnswersWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        rightAnswersTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));

        wrongAnswersWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        wrongAnswersTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));
        refresh(0);
    }

    public void refresh(int NUMofTest) {
        rightAnswers.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.SuccessfulList[NUMofTest]));
        wrongAnswers.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.UnSuccessfulList[NUMofTest]));
    }
}