package sample;
import mainApp.*;

import javafx.stage.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.net.URL;
import java.util.*;


public class Controller implements Initializable {

    public class mapData
    {
        public String password;
        public Integer id;
        public mapData(String password, int id)
        {
            this.id = id;
            this.password = password;
        }
    }

    @FXML
    public static Stage STAGE;

    @FXML
    private Button btn;

    @FXML
    private TextField LoginText;

    @FXML
    private TextField PasswordText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO (don't really need to do anything here).

    }

    public void click(ActionEvent actionEvent) throws Exception {
        System.out.println("clicked");
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = documentBuilder.parse("a.xml");
        NodeList nodes = document.getElementsByTagName("ACCOUNT");
        Map<String, mapData> map = new HashMap<>();
        for (int i = 0; i<nodes.getLength(); i++) {
            String log = nodes.item(i).getAttributes().getNamedItem("Login").getNodeValue();
            String pass = nodes.item(i).getAttributes().getNamedItem("Pass").getNodeValue();
            map.put(log, new mapData(pass, i));
        }
        String login = LoginText.getText();
        if( map.get(login) != null && map.get(login).password.equals(PasswordText.getText()))   
            new MainApp(STAGE, nodes.item(map.get(login).id), document.getElementsByTagName("GlobalDictionary").item(0));
        else showDialog();
    }

    private void showDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bad login");
        alert.setHeaderText("Error");
        alert.setContentText("Bad login or password");

        alert.showAndWait();
    }
}