package mainApp;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

import static mainApp.MainApp.ACCOUNT;

//Информация о пользователе
public class AccountData
{
    public class Learned
    {
        String Value;
        Nodes Nodes = new Nodes();
        public Learned(String Word, Node node)
        {
            this.Value = Word;
            this.Nodes.node = node;
        }
        public Learned(String Word)
        {
            this.Value = Word;
        }
        public void Remove() throws TransformerException, FileNotFoundException {
            THIS.Nodes.removeFromLearned(this);
            Learned.remove(this);
        }
        public class Nodes
        {
            Node node;
            public void addIn(Boolean SAVE) throws TransformerException, FileNotFoundException
            {
                Element s = THIS.Nodes.document.createElement("Word");
                s.appendChild(THIS.Nodes.document.createTextNode(Value));
                THIS.Nodes.InLearningNode.appendChild(s);
                if(SAVE)THIS.Nodes.saveXML();
                this.node = s;
            }
        }
    }
    public static String Path = "BD.xml";
    public Settings Settings = new Settings();
    public Nodes Nodes = new Nodes();
    public List<Learned> Learned = new ArrayList<>();
    public List<String> UnLearned = new ArrayList<>();
    public List<InLearning> InLearning = new ArrayList<>();
    private AccountData THIS = this;

    public List[] SuccessfulList = new List[3];
    public List[] UnSuccessfulList = new List[3];
    private Map<String, InLearning> InLearningMap = new HashMap<>();
    static public Map<String, List<String>> WordBase = new HashMap<>();

    //Его личные настройки
    public class Settings
    {
        public int Tests;
        public int Words;
        public Nodes Nodes = new Nodes();

        public void save(int Tests, int Words) throws TransformerException, FileNotFoundException
        {
            this.Tests = Tests;
            this.Words = Words;
            Nodes.save(Tests, Words);
        }

        public class Nodes
        {
            public Node TestsNode;
            public Node WordsNode;

            public void parse(Document document) {
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

            public void save(int TestsSave, int WordsSave) throws TransformerException, FileNotFoundException {
                TestsNode.getFirstChild().setNodeValue(Integer.toString(TestsSave));
                WordsNode.getFirstChild().setNodeValue(Integer.toString(WordsSave));
                THIS.Nodes.saveXML();
            }
        }
    }

    //Его выученные, изучаемые и невыученные слова
    public class Nodes
    {
        public Document document;
        public Node InLearningNode;
        public Node LearnedNode;
        public Node AccountNode;

        public void saveXML() throws TransformerException, FileNotFoundException
        {
            Transformer t= TransformerFactory.newInstance().newTransformer();
            //t.setOutputProperty(OutputKeys.METHOD, "xml");
            //t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(Path)));
        }

        //Парс слов из BD.xml
        public void parse(Document Document, int ID)
        {
            document = Document;
            Settings.Nodes.parse(document);
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
            //FirstName = nmap.getNamedItem("FirstName").getNodeValue();
            //LastName = nmap.getNamedItem("LastName").getNodeValue();

            nodeList = DataNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++)
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
                                Learned.add(new Learned(subNodeList.item(j).getFirstChild().getNodeValue(), subNodeList.item(j)));
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
                                inlearn.Nodes.parse(subNodeList.item(j));
                                InLearning.add(inlearn);
                            }
                        }
                        break;
                    }
                }
            }
        }

        //Обозначить слово как выученное

        //Убрать слово из изученных
        public void removeFromInLearning(List<InLearning> inLearning) throws TransformerException, FileNotFoundException
        {
            for(InLearning s : inLearning)
                InLearningNode.removeChild(s.Nodes.node);
            saveXML();
        }
        public void removeFromLearned(List<Learned> Learned) throws TransformerException, FileNotFoundException
        {
            for(Learned s : Learned)
                LearnedNode.removeChild(s.Nodes.node);
            saveXML();
        }

        public void removeFromLearned(Learned Learned) throws TransformerException, FileNotFoundException
        {
            LearnedNode.removeChild(Learned.Nodes.node);
            saveXML();
        }
    }


    public void addIn(List<String> Words, String TypeName) throws TransformerException, FileNotFoundException
    {
        if(TypeName == "InLearning")
        {
            List<InLearning> inLearning;
            inLearning = Words.stream().map(s -> new InLearning(s)).collect(Collectors.toList());
            for(InLearning s : inLearning)
            {
                InLearningMap.put(s.Value, s);
                s.Nodes.addIn(false);
            }
            InLearning.addAll(inLearning);
            Nodes.saveXML();
        }
        else if (TypeName == "Learned")
        {

            List<Learned> learned;
            learned = Words.stream().map(s -> new Learned(s)).collect(Collectors.toList());
            for(Learned s : learned)
            {
                s.Nodes.addIn(false);
            }
            Learned.addAll(learned);
            Nodes.saveXML();
        }
    }

    //Убрать слово из изучаемых
    public void removeFromInLearning(List<InLearning> inLearning) throws TransformerException, FileNotFoundException
    {
        for(InLearning i : inLearning)
            InLearningMap.remove(i.Value);
        Nodes.removeFromInLearning(inLearning);
        ACCOUNT.InLearning.removeAll(inLearning);
    }

    //Перемешивание слов
    public List<String> getRandomUnLearnedWords(int size)
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

        public void upStage() throws TransformerException, FileNotFoundException
        {
            this.Stage++;
            Nodes.saveStage(Stage, true);
        }

        public void upStage(boolean SAVE) throws TransformerException, FileNotFoundException
        {
            this.Stage++;
            Nodes.saveStage(Stage, SAVE);
        }

        public int getStage() { return Stage; }

        public class Nodes
        {
            public Node node;

            public void saveStage(int Stage, boolean SAVE) throws TransformerException, FileNotFoundException
            {
                node.getAttributes().getNamedItem("Stage").setNodeValue(Integer.toString(Stage));
                if(SAVE) THIS.Nodes.saveXML();
            }

            public void parse(Node node)
            {
                this.node = node;
                Stage = Integer.parseInt(node.getAttributes().getNamedItem("Stage").getNodeValue());
                Value = node.getFirstChild().getNodeValue();
                InLearningMap.put(Value, THISil);
            }

            public void addIn(Boolean SAVE) throws TransformerException, FileNotFoundException
            {
                    Element s = THIS.Nodes.document.createElement("Word");
                    s.appendChild(THIS.Nodes.document.createTextNode(Value));
                    s.setAttribute("Stage", "0");
                    THIS.Nodes.InLearningNode.appendChild(s);
                if(SAVE)THIS.Nodes.saveXML();
                this.node = s;
            }
        }
    }

    public InLearning inLearningGet(String Word)
    {
        return InLearningMap.get(Word);
    }

    public List<InLearning> inLearningGet(List<String> Word)
    {
        List<InLearning> inLearningList = new ArrayList<>();
        for(String s : Word)
            inLearningList.add(InLearningMap.get(s));
        return inLearningList;
    }

    public AccountData()
    {
        SuccessfulList[0] = new ArrayList<String>();
        SuccessfulList[1] = new ArrayList<String>();
        SuccessfulList[2] = new ArrayList<String>();

        UnSuccessfulList[0] = new ArrayList<String>();
        UnSuccessfulList[1] = new ArrayList<String>();
        UnSuccessfulList[2] = new ArrayList<String>();
    }

}