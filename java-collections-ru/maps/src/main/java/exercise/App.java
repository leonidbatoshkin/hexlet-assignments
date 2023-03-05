package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> hashmap = new HashMap<>();
        List<String> words = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        var counter = 1;
        if (sentence.equals("")) {
            return new HashMap<>();
        }
        for (String word : words) {
            if (hashmap.containsKey(word)) {
                var value = hashmap.get(word);
                hashmap.put(word, ++value);
            } else {
                hashmap.put(word, counter);
            }
        }
        return hashmap;
    }

    public static String toString(Map<String, Integer> wordsHashmap) {
        var resultString = "{";
        if (!wordsHashmap.isEmpty()) {
            resultString += "\n";
            for (Map.Entry<String, Integer> words : wordsHashmap.entrySet()) {
                resultString += "  " + words.getKey() + ": " + words.getValue() + "\n";
            }
        }
        resultString += "}";
        return resultString;
    }
}

