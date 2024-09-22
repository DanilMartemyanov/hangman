package gallows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("checkstyle:ConstantName")
public class CheckInputUserImpl implements CheckInputUser {
    static final String REGEXCATEGORY = "^[macnMACN]$";
    static final Pattern patternCategory = Pattern.compile(REGEXCATEGORY);
    static final String REGEXLEVEL = "^[emhnEMHN]$";
    static final Pattern patternLevel = Pattern.compile(REGEXLEVEL);
    static final String STARTGAME = "^[ynYN]$";
    static final Pattern patternStartGame = Pattern.compile(STARTGAME);
    static final String ENTERLETTER = "^[а-яА-Я1]$";
    static final Pattern patternLetter = Pattern.compile(ENTERLETTER);
    static final String GETTIP = "^[1\\S\s]$";
    static final Pattern patternGetTip = Pattern.compile(GETTIP);
    static final String YES = "y";
    static final String NO = "n";
    static final String TIP = "1";

    @Override
    public int equalsCharArray(char[] currentEnter, char[] currentAnswer) {
        int count = 0;
        for (int i = 0; i < currentAnswer.length; i++) {
            if (currentEnter[i] != '_') {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean checkCorrectWord(char[] currentAnswer, String word) {
        int count = 0;
        for (int i = 0; i < currentAnswer.length; i++) {
            if (currentAnswer[i] == word.charAt(i)) {
                count++;
            }
        }
        if (count == word.length()) {
            return true;
        }
        return false;
    }


    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    @Override
    public String startGame(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String ready = bufferedReader.readLine();
            Matcher matcherReady = CheckInputUserImpl.patternStartGame.matcher(ready);

            while (!matcherReady.find()) {
                printStream.println("Некорректный ввод, введите значение заново");
                ready = bufferedReader.readLine();
                matcherReady = CheckInputUserImpl.patternStartGame.matcher(ready);
            }
            return ready.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    @Override
    public String choiceCategory(BufferedReader bufferedReader, PrintStream printStream, WordsGallows wordsGallows) {
        printStream.println("Для выбора категории укажите первую букву [m/a/с]-, иначе нажмите - [n]");

        try {
            String category = bufferedReader.readLine();
            Matcher matcherCategory = CheckInputUserImpl.patternCategory.matcher(category);

            while (!matcherCategory.find()) {
                printStream.println("Некорректный ввод, введите значение заново");
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


    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    @Override
    public String choiceLevel(BufferedReader bufferedReader, PrintStream printStream, WordsGallows wordsGallows) {
        try {
            String level = bufferedReader.readLine();
            Matcher matcherLevel = CheckInputUserImpl.patternLevel.matcher(level);

            while (!matcherLevel.find()) {
                printStream.println("Некорректный ввод, введите значение заново");
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


    @Override
    public String enterLetter(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String letter = bufferedReader.readLine();
            Matcher matcherLetter = CheckInputUserImpl.patternLetter.matcher(letter);
            while (!matcherLetter.find()) {
                printStream.println("Некорректный ввод");
                letter = bufferedReader.readLine();
                matcherLetter = CheckInputUserImpl.patternLetter.matcher(letter);
            }
            return letter.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
