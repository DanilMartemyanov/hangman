package gallows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("checkstyle:CyclomaticComplexity")
public class GameInterface {
    @SuppressWarnings("checkstyle:ConstantName")
    private final BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    private final CheckInputUserImpl checkInputUser = new CheckInputUserImpl();
    private final LogicFindCorrectCharImpl logicFindCorrectChar = new LogicFindCorrectCharImpl();
    private final WordsGallows wordsGallows = new WordsGallows();
    private final PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    private final LogicGuessingWordImpl logicGuessingWord = new LogicGuessingWordImpl();

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public void userInterface() {
        while (true) {
            printStream.println("------------------------------------------------------");
            printStream.println("Привееет, готовы поиграть в виселицу и повеселиться?");
            printStream.println("Для продолжения введите - [y], иначе - [n]");

            String ready = checkInputUser.startGame(bufferedReader, printStream);

            if (CheckInputUserImpl.YES.equals(ready)) {
                printStream.println("------------------------------------------------------");
                printStream.println("Хотите выбрать категорию или доверитесь нам?");
                printStream.println("Возможные категории:");

                for (String category : wordsGallows.getAllCategories()) {
                    printStream.println(category);
                }

                String category = checkInputUser.choiceCategory(bufferedReader, printStream, wordsGallows);

                printStream.println("------------------------------------------------------");
                printStream.println("Что насчет уровня сложности?");
                printStream.println("Для выбора уровня сложности укажите букву [e/m/h]-, иначе нажмите - [n]");

                String level = checkInputUser.choiceLevel(bufferedReader, printStream, wordsGallows);

                printStream.println("------------------------------------------------------");
                // создание игровой сессии
                SessionPlayer sessionPlayer =
                    logicGuessingWord.getWords(wordsGallows, printStream, category, level);
                logicFindCorrectChar.checkChar(sessionPlayer, '_');
                printStream.println(sessionPlayer.currentEnter());
                if (wordsGallows.checkWord(sessionPlayer.word())) {
                    break;
                }
                while (!(sessionPlayer.countAttempts < 0)) {

                    printStream.println("У вас осталось - " + sessionPlayer.countAttempts + " попыток");

                    if (sessionPlayer.countAttempts == 0) {
                        printStream.println("GAME OVER");
                        break;
                    }

                    printStream.println("Введите букву или запросите подсказку, нажав - [1]");

                    String letter = checkInputUser.enterLetter(bufferedReader, printStream);

                    if (CheckInputUserImpl.TIP.equals(letter)) {
                        printStream.println("Подсказка: " + sessionPlayer.tip());
                    } else {

                        logicFindCorrectChar.checkChar(sessionPlayer, letter.charAt(0));

                        int countMatches = checkInputUser
                            .equalsCharArray(sessionPlayer.currentEnter(), sessionPlayer.currentAnswer);

                        sessionPlayer.displayImage(countMatches, printStream);
                        if (checkInputUser.checkCorrectWord(sessionPlayer.currentAnswer(), sessionPlayer.word())) {
                            printStream.println("ВЫ победили!!!!!!!!!!!");
                            printStream.println("Загаданное слово - " + sessionPlayer.word());
                            break;
                        }
                    }
                }
            } else {
                printStream.println("Закончили");
                break;
            }

        }
    }

}
