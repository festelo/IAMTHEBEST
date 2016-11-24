package mainApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApp {

    static public AccountData ACCOUNT;

    public MainApp(Stage primaryStageMain, Node DataNode, Node DictionaryNode) throws Exception{
        ACCOUNT = new AccountData();
        ACCOUNT.Nodes.Parse(DataNode, DictionaryNode);
        Parent rootMain = FXMLLoader.load(getClass().getResource("mainApp.fxml"));
        primaryStageMain.setTitle("Learn English");
        primaryStageMain.setScene(new Scene(rootMain));
    }
}