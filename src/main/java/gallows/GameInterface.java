package gallows;

import jakarta.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.NoSuchElementException;
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
                Matcher matcherReady = CheckInputUserImpl.patternStartGame.matcher(ready);

                while (!matcherReady.find()) {
                    printStream.println("Некорректный ввод, введите значение заново");
                    ready = bufferedReader.readLine();
                    matcherReady = CheckInputUserImpl.patternStartGame.matcher(ready);
                }

                if (CheckInputUserImpl.YES.equals(ready.toLowerCase())) {
                    printStream.println("------------------------------------------------------");
                    printStream.println("Хотите выбрать категорию или доверитесь нам?");
                    printStream.println("Возможные категории:");

                    for (String category : wordsGallows.getAllCategories()) {
                        printStream.println(category);
                    }

                    printStream.println("Для выбора категории укажите первую букву [m/a/с]-, иначе нажмите - [n]");
                    String answerCategory = bufferedReader.readLine();
                    Matcher matcherCategory = CheckInputUserImpl.patternCategory.matcher(answerCategory);

                    while (!matcherCategory.find()) {
                        printStream.println("Некорректный ввод, введите значение заново");
                        answerCategory = bufferedReader.readLine();
                        matcherCategory = CheckInputUserImpl.patternCategory.matcher(answerCategory);
                    }

                    if (CheckInputUserImpl.NO.equals(answerCategory.toLowerCase())) {
                        answerCategory = wordsGallows.getRandomCategory();
                    }

                    printStream.println("------------------------------------------------------");
                    printStream.println("Что насчет уровня сложности?");
                    printStream.println("Для выбора уровня сложности укажите букву [e/m/h]-, иначе нажмите - [n]");

                    String answerLevel = bufferedReader.readLine();
                    Matcher matcherLevel = CheckInputUserImpl.patternLevel.matcher(answerLevel);

                    while (!matcherLevel.find()) {
                        printStream.println("Некорректный ввод, введите значение заново");
                        answerLevel = bufferedReader.readLine();
                        matcherLevel = CheckInputUserImpl.patternLevel.matcher(answerLevel);
                    }

                    if (CheckInputUserImpl.NO.equals(answerLevel.toLowerCase())) {
                        answerLevel = wordsGallows.getRandomLevel();
                    }

                    printStream.println("------------------------------------------------------");

                    HashMap<String, String> gameWord = wordsGallows.getWord(answerCategory, answerLevel);
                    String word =
                        gameWord.keySet().stream().findFirst()
                            .orElseThrow(() -> new NoSuchElementException("Нет такого элемента"));
                    String tip = gameWord.get(word);
                    SessionPlayer sessionPlayer = new SessionPlayer(word);
                    printStream.println("Подготовили для вас слово");
                    printStream.println(word);

                    logicFindCorrectChar.checkChar(sessionPlayer, '_');
                    printStream.println(sessionPlayer.currentEnter());
                    while (!(sessionPlayer.countAttempts < 0)) {
                        printStream.println("Если хотите подсказку, нажмите -[1], пропустить - пробел или любую букву");

                        int oldCountMatches = 0;

                        printStream.println("У вас осталось - " + sessionPlayer.countAttempts + " попыток");

                        if (sessionPlayer.countAttempts == 0) {
                            printStream.println("GAME OVER");
                            break;
                        }

                        printStream.println("Введите букву или запросите подсказку, нажав - [1]");
                        String enterLetter = bufferedReader.readLine();

                        Matcher matcherLetter = CheckInputUserImpl.patternLetter.matcher(enterLetter);
                        while (!matcherLetter.find()) {
                            printStream.println("Некорректный ввод");
                            enterLetter = bufferedReader.readLine();
                            matcherLetter = CheckInputUserImpl.patternLetter.matcher(enterLetter);
                        }

                        String enterTip = enterLetter;
                        if (CheckInputUserImpl.TIP.equals(enterTip)) {
                            printStream.println("Подсказка: " + tip);
                        } else {
                            try {
                                enterLetter = enterLetter.toLowerCase();
                            } catch (Exception e) {
                                printStream.println(e.getMessage());
                            }

                            logicFindCorrectChar.checkChar(sessionPlayer, enterLetter.charAt(0));

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

}
