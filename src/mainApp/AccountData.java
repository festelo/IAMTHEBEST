package mainApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 23.11.2016.
 */
public class AccountData {
    public static class Word
    {
        public String original;
        public List<String> translates = new ArrayList<>();
    }
    public String FirstName;
    public String LastName;
    public List<Word> Learned = new ArrayList<>();
    public List<Word> InLearning = new ArrayList<>();
}
