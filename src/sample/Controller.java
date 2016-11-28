package sample;
import mainApp.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable
{

    public Main main;

    @FXML
    private TextField LoginText;

    @FXML
    private TextField PasswordText;

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

    //Проверка логина и пароля
    public void checkLoginAndPassword(ActionEvent actionEvent) throws Exception
    {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(AccountData.Path);
        NodeList nodes = document.getElementsByTagName("ACCOUNT");
        Map<String, mapData> map = new HashMap<>();
        String login = LoginText.getText();

        for (int i = 0; i<nodes.getLength(); i++)
        {
            String log = nodes.item(i).getAttributes().getNamedItem("Login").getNodeValue();
            String pass = nodes.item(i).getAttributes().getNamedItem("Pass").getNodeValue();
            map.put(log, new mapData(pass, i));
        }

        if( map.get(login) != null && map.get(login).password.equals(PasswordText.getText()))
        {
            main.primaryStage.close();
            new MainApp(document, map.get(login).id);
        }
        else showDialogWrongLogin();
    }

    //Регистрация
    public void register(ActionEvent actionEvent) throws Exception
    {
        if(PasswordText.getText() == "" || LoginText.getText() == "") return;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(AccountData.Path);
        NodeList Accounts = document.getElementsByTagName("ACCOUNT");

        //Если пользователь уже зарегистрирован
        for (int i = 0; i<Accounts.getLength(); i++)
        {
            if(Accounts.item(i).getAttributes().getNamedItem("Login").getNodeValue().equals(LoginText.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Login used");
                alert.show();
                return;
            }
        }

        //Если пользователь не был зарегистрирован, то его надо добавить в BD.xml
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

    //Если логин и пароль неверны
    private void showDialogWrongLogin()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bad login");
        alert.setHeaderText("Error");
        alert.setContentText("Bad login or password");

        alert.showAndWait();
    }

    public Controller(Main main)
    {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }


}