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

public class ControllerStata implements Initializable {
    static AccountData ACCOUNT;
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

    }

    public void click(ActionEvent actionEvent) {
        rightAnswersWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        rightAnswersTranslate.setCellFactory(new PropertyValueFactory<TableData, String>("Translate"));

        wrongAnswersWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        wrongAnswersTranslate.setCellFactory(new PropertyValueFactory<TableData, String>("Translate"));
    }


    public void refresh(int NUMofTest) {
        rightAnswers.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.SuccessfulList[NUMofTest]));
        wrongAnswers.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.UnSuccessfulList[NUMofTest]));
    }
}