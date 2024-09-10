package gallows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameInterface {
    @SuppressWarnings("checkstyle:ConstantName")
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final CheckInputUserImpl checkInputUser = new CheckInputUserImpl();
    private final LogicFindCorrectCharImpl logicFindCorrectChar = new LogicFindCorrectCharImpl();
    private final WordsGallows wordsGallows = new WordsGallows();
    private final String regexCategory = "^[masn]$";
    private final Pattern patternCategory = Pattern.compile(regexCategory);
    private final String regexLevel = "^[emhn]$";
    private final Pattern patternLevel = Pattern.compile(regexLevel);

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public void userInterface() {
        PrintStream printStream = new PrintStream(System.out);
        printStream.println("------------------------------------------------------");
        printStream.println("Привееет, готовы поиграть в виселицу и повеселиться?");
        printStream.println("Для продолжения введите - [y], иначе - [n]");
        try {
            Boolean ready = (bufferedReader.readLine().toLowerCase().equals("y")) ? true : false;
            if (ready) {
                printStream.println("------------------------------------------------------");
                printStream.println("Хотите выбрать категорию или доверитесь нам?");
                printStream.println("Возможные категории:");
                for (String category : wordsGallows.getAllCategories()) {
                    printStream.println(category);
                }
                printStream.println("Для выбора категории укажите первую букву [m/a/s]-, иначе нажмите - [n]");
                String answerCategory = bufferedReader.readLine();
                Matcher matcherCategory = patternCategory.matcher(answerCategory);
                while (!matcherCategory.find()) {
                    printStream.println("Некорректный ввод, введите значение заново");
                    answerCategory = bufferedReader.readLine();
                    matcherCategory = patternCategory.matcher(answerCategory);
                }
                if (answerCategory.equals("n")) {
                    answerCategory = wordsGallows.getRandomCategory();
                }
                printStream.println("------------------------------------------------------");
                printStream.println("Что насчет уровня сложности?");
                printStream.println("Для выбора уровня сложности укажите букву [e/m/h]-, иначе нажмите - [n]");
                String answerLevel = bufferedReader.readLine();
                Matcher matcherLevel = patternLevel.matcher(answerLevel);
                while (!matcherLevel.find()) {
                    printStream.println("Некорректный ввод, введите значение заново");
                    answerLevel = bufferedReader.readLine();
                    matcherLevel = patternLevel.matcher(answerLevel);
                }
                if (answerLevel.equals("n")) {
                    answerCategory = wordsGallows.getRandomLevel();
                }
                printStream.println("------------------------------------------------------");
                HashMap<String, String> gameWord = wordsGallows.getWord(answerCategory, answerLevel);
                String word = gameWord.keySet().stream().findFirst().get();
                String tip = gameWord.get(word);
                SessionPlayer sessionPlayer = new SessionPlayer(word);
                printStream.println("Подготовили для вас слово");
                printStream.println(word);
                printStream.println("Введите букву:");
                logicFindCorrectChar.checkChar(sessionPlayer, '_');
                printStream.println(sessionPlayer.currentEnter());
                while (!(sessionPlayer.COUNTATTEMPTS < 0)) {
                    int oldCountMatches = 0;
                    printStream.println("У вас осталось - " + sessionPlayer.COUNTATTEMPTS + " попыток");
                    if(sessionPlayer.COUNTATTEMPTS == 0){
                        printStream.println("GAME OVER");
                        break;
                    }
                    char[] enterLetter = bufferedReader.readLine().toLowerCase().toCharArray();
                    while (enterLetter.length != 1) {
                        printStream.println("Некорректный ввод");
                        enterLetter = bufferedReader.readLine().toLowerCase().toCharArray();
                    }
                    logicFindCorrectChar.checkChar(sessionPlayer, enterLetter[0]);
                    int countMatches =
                        checkInputUser.equalsCharArray(sessionPlayer.currentEnter(), sessionPlayer.currentAnswer);
                    if (countMatches == oldCountMatches) {
                        printStream.println(ImageGallows.IMAGES[sessionPlayer.COUNTATTEMPTS -1]);
                        sessionPlayer.COUNTATTEMPTS--;
                    }
                    oldCountMatches = countMatches;
                    printStream.println(sessionPlayer.currentAnswer());
                    System.out.println(checkInputUser.checkCorrectWord(sessionPlayer.currentAnswer(), word));
                    if(checkInputUser.checkCorrectWord(sessionPlayer.currentAnswer(), word)){
                        printStream.println("ВЫ победили!!!!!!!!!!!");
                        break;
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GameInterface gameInterface = new GameInterface();
        gameInterface.userInterface();
    }

}
