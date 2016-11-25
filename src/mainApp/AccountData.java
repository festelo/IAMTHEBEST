package mainApp;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountData {
    public void setStage(int Stage) throws TransformerException, FileNotFoundException {
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

        private void saveXML() throws TransformerException, FileNotFoundException {
            Transformer t= TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(Path)));
        }
        public void SaveStage(int Stage) throws TransformerException, FileNotFoundException {
            InLearningNode.getAttributes().getNamedItem("Stage").setNodeValue(Integer.toString(Stage));
            saveXML();
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
        public void AddIn(List<String> Words, Node node) throws TransformerException, FileNotFoundException {
            for(String s: Words) {
                Element Word = document.createElement("Word");
                Word.appendChild(document.createTextNode(s));
                node.appendChild(Word);
            }
            saveXML();
        }
        public void ClearInLearning() throws TransformerException, FileNotFoundException {
            AccountNode.removeChild(InLearningNode);
            InLearningNode = document.createElement("InLearning");
            Element element = (Element) InLearningNode;
            element.setAttribute("Stage", "0");
            AccountNode.appendChild(InLearningNode);
            saveXML();
        }
    }
    public void AddIn(List<String> Words, Node node) throws TransformerException, FileNotFoundException {
        InLearning.addAll(Words);
        Nodes.AddIn(Words, node);
    }
    public void ClearInLearning() throws TransformerException, FileNotFoundException {
        Nodes.ClearInLearning();
        InLearning.clear();
        Stage = 0;
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

