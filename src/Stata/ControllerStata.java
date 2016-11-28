package Stata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mainApp.TableData;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.*;

import static mainApp.MainApp.ACCOUNT;

public class ControllerStata implements Initializable
{

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
    @FXML
    public ComboBox combo;

    //Слова для выбора теста, у которого нужно посмотреть статистику
    String[] ComboNames = { "Первый тест", "Второй тест", "Третий тест"};

    //Создание статистики
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> LIST = FXCollections.observableArrayList();
        LIST.addAll(ComboNames);
        combo.setItems(LIST);
        combo.getSelectionModel().selectFirst();

        rightAnswersWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        rightAnswersTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));

        wrongAnswersWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        wrongAnswersTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));
        refresh(0);
    }

    //Обновление
    public void refresh(int NUMofTest)
    {
        rightAnswers.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.SuccessfulList[NUMofTest]));
        wrongAnswers.setItems(TableData.ToTableData.ToTableDataList(ACCOUNT.UnSuccessfulList[NUMofTest]));
    }

    public void makeCombinations(ActionEvent actionEvent)
    {
        if(combo.getValue() == ComboNames[0]) refresh(0);
        else if(combo.getValue() == ComboNames[1]) refresh(1);
        else if(combo.getValue() == ComboNames[2]) refresh(2);
    }
}