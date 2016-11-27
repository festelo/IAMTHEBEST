package mainApp;

import org.w3c.dom.*;

import javax.xml.stream.events.Attribute;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

import static mainApp.MainApp.ACCOUNT;

public class AccountData {
    private AccountData THIS = this;
    public class Settings
    {
        public int Tests;
        public int Words;
        public Nodes Nodes = new Nodes();

        public void Save(int Tests, int Words) throws TransformerException, FileNotFoundException {
            this.Tests = Tests;
            this.Words = Words;
            Nodes.Save(Tests, Words);
        }

        public class Nodes {
            public Node TestsNode;
            public Node WordsNode;
            public void Parse(Document document) {
                NodeList settings = document.getElementsByTagName("Settings").item(0).getChildNodes();
                for(int i = 0; i<settings.getLength();i++)
                {
                    switch (settings.item(i).getNodeName())
                    {
                        case("Tests"):
                        {
                            TestsNode = settings.item(i);
                            Tests = Integer.parseInt(TestsNode.getFirstChild().getNodeValue());
                            break;
                        }

                        case("Words"):
                        {
                            WordsNode = settings.item(i);
                            Words = Integer.parseInt(WordsNode.getFirstChild().getNodeValue());
                        }
                    }
                }
            }
            public void Save(int TestsSave, int WordsSave) throws TransformerException, FileNotFoundException {
                TestsNode.getFirstChild().setNodeValue(Integer.toString(TestsSave));
                WordsNode.getFirstChild().setNodeValue(Integer.toString(WordsSave));
                THIS.Nodes.saveXML();
            }
        }
    }
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

        public void Parse(Document Document, int ID)
        {
            document = Document;
            Settings.Nodes.Parse(document);
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
                        NodeList subNodeList = nodeList.item(i).getChildNodes();
                        for (int j = 0; j< subNodeList.getLength(); j++)
                        {
                            if (subNodeList.item(j).getNodeName().equals("Word"))
                            {
                                InLearning inlearn = new InLearning();
                                inlearn.Nodes.Parse(subNodeList.item(j));
                                InLearning.add(inlearn);
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
                Word.setAttribute("Stage", "0");
                node.appendChild(Word);
            }
            saveXML();
        }
        public void RemoveFromInLearning(List<InLearning> inLearning) throws TransformerException, FileNotFoundException {
            for(InLearning s : inLearning)
                InLearningNode.removeChild(s.Nodes.node);
            saveXML();
        }
    }
    public void AddIn(List<String> Words, String TypeName) throws TransformerException, FileNotFoundException {
        Node node = null;
        if(TypeName == "InLearning")
        {
            node = Nodes.InLearningNode;
            InLearning.addAll(Words.stream().map(s -> new InLearning(s)).collect(Collectors.toList()));
            Nodes.AddIn(Words, node);
        }
        else if (TypeName == "Learned")
        {
            node = Nodes.LearnedNode;
            Learned.addAll(Words);
            Nodes.AddIn(Words, node);
        }
    }
    public void RemoveFromInLearning(List<InLearning> inLearning) throws TransformerException, FileNotFoundException {
        for(InLearning i : InLearning)
            InLearningMap.remove(i.Value);
        Nodes.RemoveFromInLearning(inLearning);
        ACCOUNT.InLearning.removeAll(InLearning);
    }
    public List<String> GetRandomUnLearnedWords(int size)
    {
        List<String> UnLearned = this.UnLearned;
        List<String> AddInLearning = new ArrayList<>();
        Collections.shuffle(UnLearned);
        for(int i = 0; i < size && i < UnLearned.size(); i++)
        {
            AddInLearning.add(UnLearned.get(i));
        }
        return  AddInLearning;
    }
    public class InLearning
    {
        public int Stage;
        private String Value;
        public String getValue() {return  Value;}
        public void setValue(String newValue) {InLearningMap.remove(Value); Value = newValue; InLearningMap.put(Value, this);}
        public InLearning(String Word) { Value = Word; InLearningMap.put(Value, THISil);}
        public InLearning(){}
        public Nodes Nodes = new Nodes();
        private InLearning THISil = this;

        public void upStage() throws TransformerException, FileNotFoundException {
            this.Stage++;
            Nodes.SaveStage(Stage);
        }
        public int getStage() { return Stage; }

        public class Nodes
        {
            public Node node;

            public void SaveStage(int Stage) throws TransformerException, FileNotFoundException {
                node.getAttributes().getNamedItem("Stage").setNodeValue(Integer.toString(Stage));
                THIS.Nodes.saveXML();
            }
            public void Parse(Node node)
            {
                this.node = node;
                Stage = Integer.parseInt(node.getAttributes().getNamedItem("Stage").getNodeValue());
                Value = node.getFirstChild().getNodeValue();
                InLearningMap.put(Value, THISil);
            }
        }
    }
    public InLearning InLearningGet(String Word)
    {
        return InLearningMap.get(Word);
    }
    public static String Path = "a.xml";
    public Settings Settings = new Settings();
    public Nodes Nodes = new Nodes();
    public String FirstName;
    public String LastName;
    public List<String> Learned = new ArrayList<>();
    public List<String> UnLearned = new ArrayList<>();
    public List<InLearning> InLearning = new ArrayList<>();
    private Map<String, InLearning> InLearningMap = new HashMap<>();
    static public Map<String, List<String>> WordBase = new HashMap<>();
}