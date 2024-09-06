package gallows;

import java.util.ArrayList;

public class LogicFindCorrectCharImpl implements LogicFindCorrectChar {

    @Override
    public char[] checkChar(char letter, String word) {
        char[] emptyString = word.replaceAll(".","_").toCharArray();
        char[] wordArray = word.toLowerCase().toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] == letter) {
                emptyString[i] = letter;
            }
        }
        return emptyString;
    }

}
