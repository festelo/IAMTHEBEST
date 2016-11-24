package mainApp;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountData {
    public void setStage(int Stage)
    {
        this.Stage = Stage;
        Nodes.SaveStage(Stage);
    }
    public int getStage() { return Stage; }
    public class Nodes
    {
        public Document document;
        public Node InLearningNode;
        public Node LearnedNode;
        public Node AccountNode;

        public void SaveStage(int Stage)
        {
            InLearningNode.getAttributes().getNamedItem("Stage").setNodeValue(Integer.toString(Stage));

        }

        public void Parse(Document Document, int ID)
        {
            document = Document;
            Node DataNode = Document.getElementsByTagName("ACCOUNT").item(ID);
            Node DictionaryNode = Document.getElementsByTagName("GlobalDictionary").item(0);
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
            AccountNode = DataNode;
            NamedNodeMap nmap = DataNode.getAttributes();
            FirstName = nmap.getNamedItem("FirstName").getNodeValue();
            LastName = nmap.getNamedItem("LastName").getNodeValue();

            nodeList = DataNode.getChildNodes();
            for(int i = 0; i<nodeList.getLength(); i++)
            {
                switch (nodeList.item(i).getNodeName())
                {
                    case "Learned":
                    {
                        LearnedNode = nodeList.item(i);
                        NodeList subNodeList = nodeList.item(i).getChildNodes();
                        for (int j = 0; j< subNodeList.getLength(); j++)
                        {
                            if (subNodeList.item(j).getNodeName().equals("Word"))
                            {
                                Learned.add(subNodeList.item(j).getFirstChild().getNodeValue());
                            }
                        }
                        break;
                    }
                    case "InLearning":
                    {
                        InLearningNode = nodeList.item(i);
                        Stage = Integer.parseInt(nodeList.item(i).getAttributes().getNamedItem("Stage").getNodeValue());
                        NodeList subNodeList = nodeList.item(i).getChildNodes();
                        for (int j = 0; j< subNodeList.getLength(); j++)
                        {
                            if (subNodeList.item(j).getNodeName().equals("Word"))
                            {
                                InLearning.add(subNodeList.item(j).getFirstChild().getNodeValue());
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    public static String Path = "a.xml";
    public Nodes Nodes = new Nodes();
    public String FirstName;
    public String LastName;
    public List<String> Learned = new ArrayList<>();
    public List<String> UnLearned = new ArrayList<>();
    public List<String> InLearning = new ArrayList<>();
    static public Map<String, List<String>> WordBase = new HashMap<>();
    private int Stage;
}

