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
    static public Map<String, List<String>> WordBase = new HashMap<>();

    public MainApp(Stage primaryStageMain, Node DataNode, Node DictionaryNode) throws Exception{
        NodeList nodeList = DictionaryNode.getChildNodes();
        for (int i = 0; i<nodeList.getLength(); i++)
        {
            if(nodeList.item(i).getNodeName().equals("Word"))
            {
                Node node = nodeList.item(i);
                String wordName = node.getAttributes().getNamedItem("Original").getNodeValue();
                List<String> wordTranslates = new ArrayList<>();
                NodeList subsubNodeList = node.getChildNodes();
                for (int a = 0; a< subsubNodeList.getLength(); a++)
                    if(subsubNodeList.item(a).getNodeName().equals("Translate"))
                    {
                        String value = subsubNodeList.item(a).getFirstChild().getNodeValue();
                        wordTranslates.add(value);
                    }
                WordBase.put(wordName, wordTranslates);
            }
        }

        NamedNodeMap nmap = DataNode.getAttributes();
        ACCOUNT = new AccountData();
        ACCOUNT.FirstName = nmap.getNamedItem("FirstName").getNodeValue();
        ACCOUNT.LastName = nmap.getNamedItem("LastName").getNodeValue();

        nodeList = DataNode.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++)
        {
            switch (nodeList.item(i).getNodeName())
            {
                case "Learned":
                {
                    NodeList subNodeList = nodeList.item(i).getChildNodes();
                    for (int j = 0; j< subNodeList.getLength(); j++)
                    {
                        if (subNodeList.item(j).getNodeName().equals("Word"))
                        {
                            ACCOUNT.Learned.add(subNodeList.item(j).getFirstChild().getNodeValue());
                        }
                    }
                    break;
                }
                case "InLearning":
                {
                    ACCOUNT.LearnStage = Integer.parseInt(nodeList.item(i).getAttributes().getNamedItem("Stage").getNodeValue());
                    NodeList subNodeList = nodeList.item(i).getChildNodes();
                    for (int j = 0; j< subNodeList.getLength(); j++)
                    {
                        if (subNodeList.item(j).getNodeName().equals("Word"))
                        {
                            ACCOUNT.InLearning.add(subNodeList.item(j).getFirstChild().getNodeValue());
                        }
                    }
                    break;
                }
            }
        }

        Parent rootMain = FXMLLoader.load(getClass().getResource("mainApp.fxml"));
        primaryStageMain.setTitle("Learn English");
        primaryStageMain.setScene(new Scene(rootMain));
    }
}