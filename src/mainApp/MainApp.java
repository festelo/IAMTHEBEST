package mainApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MainApp {
    public MainApp(Stage primaryStageMain, Node DataNode) throws Exception{
        Parent rootMain = FXMLLoader.load(getClass().getResource("mainApp.fxml"));
        primaryStageMain.setTitle("Learn English");
        primaryStageMain.setScene(new Scene(rootMain));

        NamedNodeMap nmap = DataNode.getAttributes();
        Controller.Data = new AccountData();
        Controller.Data.FirstName = nmap.getNamedItem("FirstName").getNodeValue();
        Controller.Data.LastName = nmap.getNamedItem("LastName").getNodeValue();

        NodeList nodeList = DataNode.getChildNodes();
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
                            AccountData.Word word = new AccountData.Word();
                            word.original = subNodeList.item(j).getAttributes().getNamedItem("Original").getNodeValue();
                            NodeList subsubNodeList = subNodeList.item(j).getChildNodes();
                            for (int a = 0; a< subsubNodeList.getLength(); a++)
                                if(subsubNodeList.item(a).getNodeName().equals("Translate"))
                                {
                                    String value = subsubNodeList.item(a).getFirstChild().getNodeValue();
                                    word.translates.add(value);
                                }
                            Controller.Data.Learned.add(word);
                        }
                    }
                    break;
                }
                case "InLearning":
                {
                    NodeList subNodeList = nodeList.item(i).getChildNodes();
                    for (int j = 0; j< subNodeList.getLength(); j++)
                    {
                        if (subNodeList.item(j).getNodeName().equals("Word"))
                        {
                            AccountData.Word word = new AccountData.Word();
                            word.original = subNodeList.item(j).getAttributes().getNamedItem("Original").getNodeValue();
                            NodeList subsubNodeList = subNodeList.item(j).getChildNodes();
                            for (int a = 0; a< subsubNodeList.getLength(); a++)
                                if(subsubNodeList.item(a).getNodeName().equals("Translate"))
                                {
                                    String value = subsubNodeList.item(a).getFirstChild().getNodeValue();
                                    word.translates.add(value);
                                }
                            Controller.Data.InLearning.add(word);
                        }
                    }
                    break;
                }
            }
        }
        System.out.print("123");
    }
}