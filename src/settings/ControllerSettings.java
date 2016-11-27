package settings;
import javafx.scene.control.TextField;
import mainApp.AccountData;
import mainApp.MainApp;
import sample.*;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import tests.Tests;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class ControllerSettings implements Initializable {
    public TextField TestsTF;
    public TextField WordsTF;
    MainSettings mainSettings;

    public ControllerSettings(MainSettings mainSettings)
    {
       this.mainSettings = mainSettings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Save(ActionEvent actionEvent) throws TransformerException, FileNotFoundException {
        int Words = Integer.parseInt(WordsTF.getText());
        int Tests = Integer.parseInt(TestsTF.getText());
        MainApp.ACCOUNT.Settings.Save(Words, Tests);
        mainSettings.Close();
    }
}