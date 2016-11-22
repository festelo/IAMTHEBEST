package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import mainApp.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private TextField PasswText;

    @FXML
    private TextField LoginTEXT;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    public void click(ActionEvent actionEvent) throws ParserConfigurationException, IOException, SAXException {
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
            btn.setText("JOINED");
            createMainGUI();
        }
        else btn.setText("NO");
    }

    private void createMainGUI() {
        try {
            MainApp mainApp = new MainApp();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}