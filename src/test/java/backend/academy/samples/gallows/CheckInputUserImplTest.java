package backend.academy.samples.gallows;

import gallows.CheckInputUserImpl;
import gallows.SessionPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CheckInputUserImplTest {
    private CheckInputUserImpl checkInputUserImpl;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        checkInputUserImpl = new CheckInputUserImpl();
        printStream = new PrintStream(System.out);
    }

    // тест на регистр ввода пользователя на готовность
    @Test
    void checkStartGame() throws IOException {
        String inputUser = "Y";
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputUser.getBytes())));
        String ready = checkInputUserImpl.startGame(bufferedReader, printStream);
        Assertions.assertEquals("y", ready);
    }

    // тест на регистр ввода буквы
    @Test
    void enterLetter() throws IOException {
        String inputUser = "А";
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputUser.getBytes())));
        String letter = checkInputUserImpl.enterLetter(bufferedReader, printStream);
        Assertions.assertEquals("а", letter);
    }

    // тест на количество совпадений
    @Test
    void equalsCharArray() {
        SessionPlayer sessionPlayer = new SessionPlayer("слон");
        sessionPlayer.currentEnter("c___".toCharArray());
        int countMatches =
            checkInputUserImpl.equalsCharArray(sessionPlayer.currentEnter(), sessionPlayer.currentAnswer);
        Assertions.assertEquals(1, countMatches);

    }

}
