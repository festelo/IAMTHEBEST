package tests;

import javafx.stage.Stage;
import tests.Test2.MainTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static mainApp.AccountData.WordBase;
import static mainApp.MainApp.ACCOUNT;

public class Tests {

    public int rightAnswers;
    public int wrongAnswers;

    public Tests(Stage MainStage) throws Exception
    {

        System.out.println(ACCOUNT.getStage());

        if(ACCOUNT.getStage() == 0)
        {
            List<String> Words = ACCOUNT.InLearning;
            List<String> Translates = new ArrayList<>();
            Random rand = new Random();
            for (String s : Words)
            {
                List<String> listTEMP = WordBase.get(s);
                Translates.add(listTEMP.get(rand.nextInt(listTEMP.size())));
            }

            Collections.shuffle(Translates);
            Collections.shuffle(Words);
            tests.Test1.MainTest dialog =  new tests.Test1.MainTest(MainStage, Words, Translates);
            //next = dialog.result;
        }
        if(ACCOUNT.getStage() == 1)
        {
            Random rand = new Random();
            String Word =
                    ACCOUNT.InLearning.get(rand.nextInt(ACCOUNT.InLearning.size()));
            List<String> Translates = new ArrayList<>();
            Translates.add(WordBase.get(Word).get(rand.nextInt(WordBase.get(Word).size())));

            for (String s : ACCOUNT.InLearning)
            {
                if(s.equals(Word)) continue;
                List<String> TranslateList = WordBase.get(s);
                Translates.add(TranslateList.get(rand.nextInt(TranslateList.size())));
            }
            Collections.shuffle(Translates);
            new MainTest(MainStage, Word, Translates, 2);
        }
        Boolean result = false;
        if(ACCOUNT.getStage() == 2)
        {
            Random rand = new Random();
            String Word =
                    ACCOUNT.InLearning.get(rand.nextInt(ACCOUNT.InLearning.size()));
            String Translate = WordBase.get(Word).get(rand.nextInt(WordBase.get(Word).size()));
            List<String>  Words = new ArrayList<>();
            Words.add(Word);
            for (String s : ACCOUNT.InLearning)
            {
                if(s == Word) continue;
                Words.add(s);
            }
            MainTest dialog = new MainTest(MainStage, Translate, Words, 3);
            result = dialog.result;

        }
        if(result)
        {
            // Добавить слова из InLearning в Learned
            // Очистить InLearning,
            // Добавить рандомные слова в InLearning из UnLearned
        }
    }
}
