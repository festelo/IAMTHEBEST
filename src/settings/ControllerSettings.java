package settings;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

import mainApp.MainApp;

public class ControllerSettings implements Initializable
{

    public TextField TestsTF;
    public TextField WordsTF;
    MainSettings mainSettings;

    public ControllerSettings(MainSettings mainSettings)
    {
       this.mainSettings = mainSettings;
    }

    //Инициализация кнопок
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        TestsTF.setText(Integer.toString(MainApp.ACCOUNT.Settings.Tests));
        WordsTF.setText(Integer.toString(MainApp.ACCOUNT.Settings.Words));
    }

    //Сохранение настроек пользователя
    public void save(ActionEvent actionEvent) throws TransformerException, FileNotFoundException
    {
        int Words = Integer.parseInt(WordsTF.getText());
        int Tests = Integer.parseInt(TestsTF.getText());
        MainApp.ACCOUNT.Settings.Save(Tests, Words);
        mainSettings.Close();
    }
}