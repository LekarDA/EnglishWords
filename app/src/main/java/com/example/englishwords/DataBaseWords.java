package com.example.englishwords;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by Дмитрий on 13.05.2015.
 */
public class DataBaseWords {
    private Map<String, String> englishWords = new HashMap<String, String>();
    private Map<String, String> onRepeatWords = new HashMap<>();
    private Random r = new Random();
//    public String[] russianWords = {"это", "но", "от", "они", "его", "она", "или", "который", "как", "мы", "сказать"};

    public Map<String, String> addWordsForRepeat(String repeatWord) {
        onRepeatWords.put(repeatWord, englishWords.get(repeatWord));
        return onRepeatWords;
    }

    public void generateDemoWords() {
        englishWords.put("это", "this");
        englishWords.put("но", "but");
        englishWords.put("от", "from");
        englishWords.put("они", "they");
        englishWords.put("его", "his");
        englishWords.put("она", "she");
        englishWords.put("или", "or");
        englishWords.put("который", "which");
        englishWords.put("как", "as");
        englishWords.put("мы", "we");
        englishWords.put("сказать", "say");
    }

    public String getRussianWord() {
        Iterator<String> iterator = englishWords.keySet().iterator();
        for (int pointer = r.nextInt(englishWords.keySet().size()); pointer > 0; pointer--)
            iterator.next();
        return iterator.next();
//            Object[] tempKey = englishWords.keySet().toArray();
//        String key = (String) tempKey[r.nextInt(tempKey.length)];
//        return key;
    }

    public String getEnglishWord(String russianWord) {
        return englishWords.get(russianWord);
    }

    public void deleteEnglishWord(String russianWord) {
        englishWords.remove(russianWord);
    }
}
