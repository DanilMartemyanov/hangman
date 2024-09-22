package gallows;

import java.io.BufferedReader;
import java.io.PrintStream;

public interface CheckInputUser {
    int equalsCharArray(char[] currentEnter, char[] currentAnswer);

    boolean checkCorrectWord(char[] currentAnswer, String word);

    String startGame(BufferedReader bufferedReader, PrintStream printStream);

    String choiceCategory(BufferedReader bufferedReader, PrintStream printStream, WordsGallows wordsGallows);

    String choiceLevel(BufferedReader bufferedReader, PrintStream printStream, WordsGallows wordsGallows);

    String enterLetter(BufferedReader bufferedReader, PrintStream printStream, SessionPlayer sessionPlayer);
}
