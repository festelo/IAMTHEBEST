package mainApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

import static mainApp.Controller.WordBase;

public class TableData
{
    public static class ToTableData
    {
        public static ObservableList<TableData> ToTableDataList(List<String> list)
        {
            ObservableList<TableData> toReturn = FXCollections.observableArrayList();;
            for (String s: list)
            {
                String TEMPString = "";
                for(String a: WordBase.get(s))
                    TEMPString += a + ", ";
                if(TEMPString != "")
                    TEMPString = TEMPString.substring(0, TEMPString.length()-2);
                toReturn.add(new TableData(s, TEMPString));
            }
            return toReturn;
        }
    }

    private String Word;
    private String Translates;
    public void setWord(String Word){ this.Word = Word; }
    public String getWord() { return this.Word; }
    public void setTranslates(String Translates){ this.Translates = Translates; }
    public String getTranslates() { return this.Translates; }

    public TableData(String Word, String Translates)
    {
        this.Word = Word;
        this.Translates = Translates;
    }

    public TableData() {}
}
