package tests;

import tests.Test2.MainTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static mainApp.AccountData.WordBase;
import static mainApp.MainApp.ACCOUNT;

/**
 * Created by me on 25.11.2016.
 */
public class Tests {
    public Tests() throws Exception {

        if(ACCOUNT.getStage() == 0) {
            List<String> Words = ACCOUNT.InLearning;
            List<String> Translates = new ArrayList<>();
            Random rand = new Random();
            for (String s : Words) {
                List<String> listTEMP = WordBase.get(s);
                Translates.add(listTEMP.get(rand.nextInt(listTEMP.size())));
            }

            Collections.shuffle(Translates);
            Collections.shuffle(Words);
            tests.Test1.MainTest dialog =  new tests.Test1.MainTest(sample.Controller.STAGE, Words, Translates);
            //next = dialog.result;
        }
        if(ACCOUNT.getStage() == 1){
            Random rand = new Random();
            String Word =
                    ACCOUNT.InLearning.get(rand.nextInt(ACCOUNT.InLearning.size()));
            List<String> Translates = new ArrayList<>();
            Translates.add(WordBase.get(Word).get(rand.nextInt(WordBase.get(Word).size())));


            for (String s : ACCOUNT.InLearning)
            {
                if(s == Word) continue;
                //
                List<String> TranslateList = WordBase.get(s);
                Translates.add(TranslateList.get(rand.nextInt(TranslateList.size())));
            }
            Collections.shuffle(Translates);
            new MainTest(sample.Controller.STAGE, Word, Translates, 2);
        }
        Boolean result = false;
        if(ACCOUNT.getStage() == 2){
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
            new MainTest(sample.Controller.STAGE, Translate, Words, 3);
            result = MainTest.result;

        }
        if(result)
        {
            // Добавить слова из InLearning в Learned
            // Очистить InLearning,
            // Добавить рандомные слова в InLearning из UnLearned
        }
    }
}
