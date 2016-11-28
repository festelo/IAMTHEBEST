package tests;

import mainApp.AccountData;
import mainApp.MainApp;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import tests.Test2.MainTest;
import static mainApp.AccountData.WordBase;
import static mainApp.MainApp.ACCOUNT;

public class Tests {

    //Создание первого теста
    public void FirstTest(MainApp main) throws Exception {
        List<String> Words = ACCOUNT.InLearning.stream().map(s -> s.getValue()).collect(Collectors.toList());
        List<String> Translates = new ArrayList<>();
        Random rand = new Random();
        for (String s : Words)
        {
            List<String> listTEMP = WordBase.get(s);
            Translates.add(listTEMP.get(rand.nextInt(listTEMP.size())));
        }


        Collections.shuffle(Translates);
        Collections.shuffle(Words);
        tests.Test1.MainTest dialog =  new tests.Test1.MainTest(main.primaryStageMain, Words, Translates);
        CheckLearning(main);
    }

    //Создание второго теста
    public void SecondTest(MainApp main) throws Exception{
        Random rand = new Random();
        String Word =
                ACCOUNT.InLearning.get(rand.nextInt(ACCOUNT.InLearning.size())).getValue();
        List<String> Translates = new ArrayList<>();
        Translates.add(WordBase.get(Word).get(rand.nextInt(WordBase.get(Word).size())));

        for (AccountData.InLearning s : ACCOUNT.InLearning) {
            if (s.getValue().equals(Word)) continue;
            List<String> TranslateList = WordBase.get(s.getValue());
            Translates.add(TranslateList.get(rand.nextInt(TranslateList.size())));
        }
        Collections.shuffle(Translates);
        tests.Test2.MainTest dialog2 = new MainTest(main.primaryStageMain, Word, Translates, 1);
        CheckLearning(main);
    }

    //Создание третьего теста
    public void ThirdTest(MainApp main) throws Exception{
        Random rand = new Random();
        String Word =
                ACCOUNT.InLearning.get(rand.nextInt(ACCOUNT.InLearning.size())).getValue();
        String Translate = WordBase.get(Word).get(rand.nextInt(WordBase.get(Word).size()));
        List<String> Words = new ArrayList<>();
        Words.add(Word);
        for (AccountData.InLearning s : ACCOUNT.InLearning) {
            if (s.getValue() == Word) continue;
            Words.add(s.getValue());
        }
        new MainTest(main.primaryStageMain, Translate, Words, 2);
        CheckLearning(main);
    }

    public Tests(MainApp main, int Test) throws Exception
    {
        switch(Test)
        {
            case 0:
                FirstTest(main);
                break;
            case 1:
                SecondTest(main);
                break;
            case 2:
                ThirdTest(main);
                break;
        }
    }

    public List<AccountData.InLearning> GetCompletes()
    {
        List<AccountData.InLearning> returnList = ACCOUNT.InLearning.stream().filter(s -> s.Stage == ACCOUNT.Settings.Tests).collect(Collectors.toList());
        return returnList;
    }

    public void CheckLearning(MainApp main) throws TransformerException, FileNotFoundException {
        List<AccountData.InLearning> completes = GetCompletes();
        List<String> strings = completes.stream().map(s -> s.getValue()).collect(Collectors.toList());
        ACCOUNT.RemoveFromInLearning(completes);
        ACCOUNT.AddIn(strings, "Learned");
        List<String> AddIn = ACCOUNT.GetRandomUnLearnedWords(ACCOUNT.Settings.Words - ACCOUNT.InLearning.size());
        ACCOUNT.AddIn(AddIn, "InLearning");
        ACCOUNT.UnLearned.removeAll(AddIn);
        main.Controller.refresh();
    }


}