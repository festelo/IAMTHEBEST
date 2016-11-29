package mainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static mainApp.AccountData.WordBase;
import static mainApp.MainApp.ACCOUNT;


public class Controller implements Initializable
{

    MainApp Main;

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

    //Открытие окошка с тестами
    public void testBtn(ActionEvent actionEvent) throws Exception
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Testing");
        alert.setHeaderText("Choose test");

        //Создание самих кнопок
        ButtonType buttonTypeOne = new ButtonType("First");
        ButtonType buttonTypeTwo = new ButtonType("Second");
        ButtonType buttonTypeThree = new ButtonType("Third");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne)
        {
            new tests.Tests(Main, 0);
        } else if (result.get() == buttonTypeTwo)
        {
            new tests.Tests(Main, 1);
        } else if (result.get() == buttonTypeThree)
        {
            new tests.Tests(Main, 2);
        }
    }

    //Конструктор
    public Controller(MainApp app)
    {
        Main = app;
        app.Controller = this;
    }


    //Добавление изученный, изучаемых и неизученных слов в таблицы
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        LearnedTableWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        LearnedTableTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));

        UnLearnedTableWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        UnLearnedTableTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));

        InLearningTableWord.setCellValueFactory(new PropertyValueFactory<TableData, String>("Word"));
        InLearningTableTranslate.setCellValueFactory(new PropertyValueFactory<TableData, String>("Translates"));
        ACCOUNT.UnLearned = getUnlearned();
        refresh();
    }

    public void refresh()
    {
        InLearningTable.setItems(TableData.ToTableData.ToTableDataListFromInLearning(MainApp.ACCOUNT.InLearning));
        LearnedTable.setItems(TableData.ToTableData.ToTableDataList(MainApp.ACCOUNT.Learned));
        UnLearnedTable.setItems(TableData.ToTableData.ToTableDataList(MainApp.ACCOUNT.UnLearned));
    }


    //Открытие настроек
    public void settingsBtn(ActionEvent actionEvent) throws IOException
    {
        new settings.MainSettings(Main.primaryStageMain);
    }

    //Получение невыученных слов
    public List<String> getUnlearned()
    {
        List<String> returnStr = new ArrayList<>();
        for(String s : WordBase.keySet())
        {
            if(ACCOUNT.Learned.contains(s) || ACCOUNT.InLearning.contains(s)) continue;
            returnStr.add(s);
        }
        return  returnStr;
    }

    //Кнопка обновления
    public void refrBtn(ActionEvent actionEvent) throws TransformerException, FileNotFoundException
    {
        ACCOUNT.removeFromInLearning(ACCOUNT.InLearning);
        List<String> AddIn = ACCOUNT.getRandomUnLearnedWords(ACCOUNT.Settings.Words);
        ACCOUNT.addIn(AddIn, "InLearning");
        refresh();
    }

    //Открытие статистики
    public void stataBtn(ActionEvent actionEvent) throws IOException
    {
        new Stata.MainStata(Main.primaryStageMain);
    }


}