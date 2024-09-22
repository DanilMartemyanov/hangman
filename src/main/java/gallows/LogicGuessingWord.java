package gallows;

import java.io.PrintStream;


public interface LogicGuessingWord {
    SessionPlayer getWords(WordsGallows wordsGallows, PrintStream printStream, String category, String level);
}
