package mainApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApp {

    static public AccountData ACCOUNT;
    public Stage primaryStageMain;
    public MainApp(Document doc, int ID) throws Exception{
        ACCOUNT = new AccountData();
        ACCOUNT.Nodes.Parse(doc, ID);
        primaryStageMain = new Stage();
        Controller controller = new Controller(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainApp.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        primaryStageMain.setTitle("Learn English");
        primaryStageMain.setScene(new Scene(root));
        primaryStageMain.show();
    }
}