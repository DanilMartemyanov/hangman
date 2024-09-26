package gallows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInputUserImpl {
    static final Pattern PATTERNCATEGORY = Pattern.compile("^[macnMACN]$");
    static final Pattern PATTERNLEVEL = Pattern.compile("^[emhnEMHN]$");
    static final Pattern PATTERNSTARTGAME = Pattern.compile("^[ynYN]$");
    static final Pattern PATTERNLETTER = Pattern.compile("^[а-яА-Я1]$");
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
        return count == word.length();
    }

    public String startGame(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String ready = bufferedReader.readLine();
            Matcher matcherReady = PATTERNSTARTGAME.matcher(ready);

            while (!matcherReady.find()) {
                printStream.println(INCORRECTINPUT);
                ready = bufferedReader.readLine();
                matcherReady = PATTERNSTARTGAME.matcher(ready);
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
            Matcher matcherCategory = PATTERNCATEGORY.matcher(category);

            while (!matcherCategory.find()) {
                printStream.println(INCORRECTINPUT);
                category = bufferedReader.readLine();
                matcherCategory = PATTERNCATEGORY.matcher(category);
            }

            if (NO.equals(category)) {
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
            Matcher matcherLevel = PATTERNLEVEL.matcher(level);

            while (!matcherLevel.find()) {
                printStream.println(INCORRECTINPUT);
                level = bufferedReader.readLine();
                matcherLevel = PATTERNLEVEL.matcher(level);
            }

            if (NO.equals(level)) {
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
            Matcher matcherLetter = PATTERNLETTER.matcher(letter);
            while (!matcherLetter.find()) {
                printStream.println(INCORRECTINPUT);
                letter = bufferedReader.readLine();
                matcherLetter = PATTERNLETTER.matcher(letter);
            }
            return letter.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
