package gallows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("checkstyle:ConstantName")
public class CheckInputUserImpl {
    static final Pattern patternCategory = Pattern.compile("^[macnMACN]$");
    static final Pattern patternLevel = Pattern.compile("^[emhnEMHN]$");
    static final Pattern patternStartGame = Pattern.compile("^[ynYN]$");
    static final Pattern patternLetter = Pattern.compile("^[а-яА-Я1]$");
    static final Pattern patternGetTip = Pattern.compile("^[1\\S\s]$");
    static final String YES = "y";
    static final String NO = "n";
    static final String TIP = "1";
    private static final String INCORRECTINPUT = "Некорректный ввод, введите значение заново";

    public int equalsCharArray(char[] currentEnter, char[] currentAnswer) {
        int count = 0;
        for (int i = 0; i < currentAnswer.length; i++) {
            if (currentEnter[i] != '_') {
                count++;
            }
        }
        return count;
    }

    public boolean checkCorrectWord(char[] currentAnswer, String word) {
        int count = 0;
        String newWord = word.toLowerCase();
        for (int i = 0; i < currentAnswer.length; i++) {
            if (currentAnswer[i] == newWord.charAt(i)) {
                count++;
            }
        }
        if (count == word.length()) {
            return true;
        }
        return false;
    }

    public String startGame(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String ready = bufferedReader.readLine();
            Matcher matcherReady = CheckInputUserImpl.patternStartGame.matcher(ready);

            while (!matcherReady.find()) {
                printStream.println(INCORRECTINPUT);
                ready = bufferedReader.readLine();
                matcherReady = CheckInputUserImpl.patternStartGame.matcher(ready);
            }
            return ready.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String choiceCategory(BufferedReader bufferedReader, PrintStream printStream, WordsGallows wordsGallows) {
        printStream.println("Для выбора категории укажите первую букву [m/a/с]-, иначе нажмите - [n]");

        try {
            String category = bufferedReader.readLine();
            Matcher matcherCategory = CheckInputUserImpl.patternCategory.matcher(category);

            while (!matcherCategory.find()) {
                printStream.println(INCORRECTINPUT);
                category = bufferedReader.readLine();
                matcherCategory = CheckInputUserImpl.patternCategory.matcher(category);
            }

            if (CheckInputUserImpl.NO.equals(category)) {
                category = wordsGallows.getRandomCategory();
            }
            return category.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String choiceLevel(BufferedReader bufferedReader, PrintStream printStream, WordsGallows wordsGallows) {
        try {
            String level = bufferedReader.readLine();
            Matcher matcherLevel = CheckInputUserImpl.patternLevel.matcher(level);

            while (!matcherLevel.find()) {
                printStream.println(INCORRECTINPUT);
                level = bufferedReader.readLine();
                matcherLevel = CheckInputUserImpl.patternLevel.matcher(level);
            }

            if (CheckInputUserImpl.NO.equals(level)) {
                level = wordsGallows.getRandomLevel();
            }
            return level.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String enterLetter(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String letter = bufferedReader.readLine();
            Matcher matcherLetter = CheckInputUserImpl.patternLetter.matcher(letter);
            while (!matcherLetter.find()) {
                printStream.println(INCORRECTINPUT);
                letter = bufferedReader.readLine();
                matcherLetter = CheckInputUserImpl.patternLetter.matcher(letter);
            }
            return letter.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
