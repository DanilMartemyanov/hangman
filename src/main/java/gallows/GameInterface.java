package gallows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;

@SuppressWarnings("checkstyle:CyclomaticComplexity")
public class GameInterface {
    @SuppressWarnings("checkstyle:ConstantName")
    private final BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    private final CheckInputUserImpl checkInputUser = new CheckInputUserImpl();
    private final LogicFindCorrectCharImpl logicFindCorrectChar = new LogicFindCorrectCharImpl();
    private final WordsGallows wordsGallows = new WordsGallows();
    private final PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public void userInterface() {
        while (true) {
            printStream.println("------------------------------------------------------");
            printStream.println("Привееет, готовы поиграть в виселицу и повеселиться?");
            printStream.println("Для продолжения введите - [y], иначе - [n]");
            try {
                String ready = bufferedReader.readLine();

                while (ready == null) {
                    printStream.println("Неккоректный ввод, введите значение ещё раз");
                    ready = bufferedReader.readLine();
                }
                Matcher matcherReady = CheckInputUserImpl.patternStartGame.matcher(ready);

                if (!matcherReady.find()) {
                    printStream.println("Некорректный ввод, введите значение заново");
                    ready = bufferedReader.readLine().toLowerCase();
                }

                if (CheckInputUserImpl.YES.equals(ready)) {
                    printStream.println("------------------------------------------------------");
                    printStream.println("Хотите выбрать категорию или доверитесь нам?");
                    printStream.println("Возможные категории:");

                    for (String category : wordsGallows.getAllCategories()) {
                        printStream.println(category);
                    }

                    printStream.println("Для выбора категории укажите первую букву [m/a/с]-, иначе нажмите - [n]");
                    String answerCategory = bufferedReader.readLine().toLowerCase();
                    Matcher matcherCategory = CheckInputUserImpl.patternCategory.matcher(answerCategory);

                    while (!matcherCategory.find()) {
                        printStream.println("Некорректный ввод, введите значение заново");
                        answerCategory = bufferedReader.readLine().toLowerCase();
                        matcherCategory = CheckInputUserImpl.patternCategory.matcher(answerCategory);
                    }

                    if (CheckInputUserImpl.NO.equals(answerCategory)) {
                        answerCategory = wordsGallows.getRandomCategory();
                    }

                    printStream.println("------------------------------------------------------");
                    printStream.println("Что насчет уровня сложности?");
                    printStream.println("Для выбора уровня сложности укажите букву [e/m/h]-, иначе нажмите - [n]");

                    String answerLevel = bufferedReader.readLine().toLowerCase();
                    Matcher matcherLevel = CheckInputUserImpl.patternLevel.matcher(answerLevel);

                    while (!matcherLevel.find()) {
                        printStream.println("Некорректный ввод, введите значение заново");
                        answerLevel = bufferedReader.readLine().toLowerCase();
                        matcherLevel = CheckInputUserImpl.patternLevel.matcher(answerLevel);
                    }

                    if (CheckInputUserImpl.NO.equals(answerLevel)) {
                        answerLevel = wordsGallows.getRandomLevel();
                    }

                    printStream.println("------------------------------------------------------");

                    HashMap<String, String> gameWord = wordsGallows.getWord(answerCategory, answerLevel);
//                    Optional<String> word = Optional.of(gameWord.keySet().stream().findFirst().get());
                    String word = gameWord.keySet().stream().findFirst().get();
                    String tip = gameWord.get(word);
                    SessionPlayer sessionPlayer = new SessionPlayer(word);
                    printStream.println("Подготовили для вас слово");
                    printStream.println(word);

                    logicFindCorrectChar.checkChar(sessionPlayer, '_');
                    printStream.println(sessionPlayer.currentEnter());
                    while (!(sessionPlayer.countAttempts < 0)) {
                        printStream.println("Если хотите подсказку, нажмите -[1], пропустить - пробел или любую букву");

                        String userTip = bufferedReader.readLine().toLowerCase();
                        Matcher matcherTip = CheckInputUserImpl.patternGetTip.matcher(userTip);

                        while (!matcherTip.find()) {
                            printStream.println("Некорректный ввод");
                            userTip = bufferedReader.readLine().toLowerCase();
                            matcherTip = CheckInputUserImpl.patternGetTip.matcher(userTip);
                        }

                        if (CheckInputUserImpl.TIP.equals(userTip)) {
                            printStream.println("Подсказка: " + tip);
                        }

                        int oldCountMatches = 0;

                        printStream.println("У вас осталось - " + sessionPlayer.countAttempts + " попыток");

                        if (sessionPlayer.countAttempts == 0) {
                            printStream.println("GAME OVER");
                            break;
                        }

                        printStream.println("Введите букву:");
                        char[] enterLetter = bufferedReader.readLine().toLowerCase().toCharArray();

                        while (enterLetter.length != 1) {
                            printStream.println("Некорректный ввод");
                            enterLetter = bufferedReader.readLine().toLowerCase().toCharArray();
                        }

                        logicFindCorrectChar.checkChar(sessionPlayer, enterLetter[0]);

                        int countMatches = checkInputUser
                                .equalsCharArray(sessionPlayer.currentEnter(), sessionPlayer.currentAnswer);

                        if (countMatches == oldCountMatches) {
                            printStream.println(ImageGallows.IMAGES[sessionPlayer.countAttempts - 1]);
                            sessionPlayer.countAttempts--;
                        }

                        oldCountMatches = countMatches;

                        printStream.println(sessionPlayer.currentAnswer());

                        if (checkInputUser.checkCorrectWord(sessionPlayer.currentAnswer(), word)) {
                            printStream.println("ВЫ победили!!!!!!!!!!!");
                            printStream.println("Загаданное слово - " + word);
                            break;
                        }
                    }
                } else {
                    printStream.println("Закончили");
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {

        GameInterface gameInterface = new GameInterface();

        gameInterface.userInterface();
    }

}
