package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class App {

    public static boolean scrabble(String letters, String word) {
        List<Character> lettersList = new ArrayList<>(letters.length());
        for (int i = 0; i < letters.length(); i++) {
            lettersList.add(letters.toLowerCase().charAt(i));
        }
        for (Character letter : word.toLowerCase().toCharArray()) {
            if (!lettersList.contains(letter)) {
                return false;
            }
            lettersList.remove(letter);
        }
        return true;
    }
}
