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
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.IOException;
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
    private TextField LoginText;

    @FXML
    private TextField PasswordText;
    public Main main;

    public Controller(Main main)
    {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO (don't really need to do anything here).

    }

    public void click(ActionEvent actionEvent) throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(AccountData.Path);
        NodeList nodes = document.getElementsByTagName("ACCOUNT");
        Map<String, mapData> map = new HashMap<>();
        for (int i = 0; i<nodes.getLength(); i++) {
            String log = nodes.item(i).getAttributes().getNamedItem("Login").getNodeValue();
            String pass = nodes.item(i).getAttributes().getNamedItem("Pass").getNodeValue();
            map.put(log, new mapData(pass, i));
        }
        String login = LoginText.getText();
        if( map.get(login) != null && map.get(login).password.equals(PasswordText.getText())) {
            main.primaryStage.close();
            new MainApp(document, map.get(login).id);
        }
        else showDialog();
    }

    public void register(ActionEvent actionEvent) throws Exception {
        if(PasswordText.getText() == "" || LoginText.getText() == "") return;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(AccountData.Path);
        NodeList Accounts = document.getElementsByTagName("ACCOUNT");
        for (int i = 0; i<Accounts.getLength(); i++) {
            if(Accounts.item(i).getAttributes().getNamedItem("Login").getNodeValue().equals(LoginText.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Login used");
                alert.show();
                return;
            }
        }
        Element node = document.createElement("ACCOUNT");
        node.setAttribute("Login", LoginText.getText());
        node.setAttribute("Pass", PasswordText.getText());
        Element InLearningNode = document.createElement("InLearning");
        Element LearnedNode = document.createElement("Learned");
        node.appendChild(InLearningNode);
        node.appendChild(LearnedNode);
        document.getFirstChild().appendChild(node);
        Transformer t= TransformerFactory.newInstance().newTransformer();
        //t.setOutputProperty(OutputKeys.METHOD, "xml");
        //t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(AccountData.Path)));
        new MainApp(document, Accounts.getLength()-1);
    }

    private void showDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bad login");
        alert.setHeaderText("Error");
        alert.setContentText("Bad login or password");

        alert.showAndWait();
    }
}