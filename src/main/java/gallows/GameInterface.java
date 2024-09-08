package gallows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class GameInterface {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final WordsGallows wordsGallows = new WordsGallows();

    public void userInterface() {
        PrintStream printStream = new PrintStream(System.out);
        printStream.println("------------------------------------------------------");
        printStream.println("Привееет, готовы поиграть в виселицу и повеселиться?");
        printStream.println("Для продолжения введите - [y], иначе - [n]");
        try {
            Boolean ready = (bufferedReader.readLine().toLowerCase().equals("y"))? true : false;
            if (ready) {
                printStream.println("------------------------------------------------------");
                printStream.println("Хотите выбрать категорию или доверитесь нам?");
                printStream.println("Возможные категории:");
                for (String category : wordsGallows.getAllCategories()) {
                    printStream.println(category);
                }
                printStream.println("Для выбора категории укажите первую букву [m/a/s]-, иначе нажмите - [n]");
                String answerCategory = bufferedReader.readLine();

                printStream.println("answerCategory " + answerCategory);
                printStream.println("------------------------------------------------------");
                printStream.println("Что насчет уровня сложности?");
                printStream.println("Для выбора уровня сложности укажите букву [e/m/h]-, иначе нажмите - [n]");
                String answerLevel = bufferedReader.readLine();
                printStream.println("answerCategory " + answerLevel);
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
