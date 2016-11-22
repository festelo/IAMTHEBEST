package sample;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import mainApp.*;

import javafx.stage.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    public static Stage STAGE;

    @FXML
    private Button btn;

    @FXML
    private TextField PasswText;

    @FXML
    private TextField LoginTEXT;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.print("123");
        // TODO (don't really need to do anything here).

    }

    public void click(ActionEvent actionEvent) throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = documentBuilder.parse("a.xml");
        NodeList nodes = document.getElementsByTagName("ACCOUNT");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i<nodes.getLength(); i++) {
            String log = nodes.item(i).getAttributes().getNamedItem("Login").getNodeValue();
            String pass = nodes.item(i).getAttributes().getNamedItem("Pass").getNodeValue();
            map.put(log, pass);
        }
        if( map.get(LoginTEXT.getText()) != null && map.get(LoginTEXT.getText()).equals(PasswText.getText())) {
            startMainWindow();
        }
        else showDialog();
    }

    private void showDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bad login");
        alert.setHeaderText("Error");
        alert.setContentText("Context");

        alert.showAndWait();
    }


    private void startMainWindow() throws Exception {
        new MainApp(STAGE);
    }
}