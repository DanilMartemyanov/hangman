package gallows;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class LogicGuessingWordImpl implements LogicGuessingWord {
    @Override
    public SessionPlayer getWords(
        WordsGallows wordsGallows,
        PrintStream printStream,
        String category,
        String level
    ) {
        HashMap<String, String> gameWord = wordsGallows.getWord(category, level);
        String word =
            gameWord.keySet().stream().findFirst()
                .orElseThrow(() -> new NoSuchElementException("Нет такого элемента"));
        String tip = gameWord.get(word);
        SessionPlayer sessionPlayer = new SessionPlayer(word);
        sessionPlayer.tip(tip);
        printStream.println("Подготовили для вас слово");
        return sessionPlayer;
    }
}
