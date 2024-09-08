package gallows;

import java.util.ArrayList;

public class LogicFindCorrectCharImpl implements LogicFindCorrectChar {

    @Override
    public WordPlayer checkChar(WordPlayer wordPlayer, char letter) {
        // замена символов в слове пользователя на "_"
        wordPlayer.currentEnter(wordPlayer.word().replaceAll(".", "_").toCharArray());

        char[] wordArray = wordPlayer.word().toLowerCase().toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] == letter) {
                wordPlayer.currentEnter()[i] = letter;
                wordPlayer.currentAnswer[i] = letter;

            }
        }
        return wordPlayer;
    }

}
