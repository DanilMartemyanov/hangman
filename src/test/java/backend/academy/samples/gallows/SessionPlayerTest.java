package backend.academy.samples.gallows;

import gallows.CheckInputUserImpl;
import gallows.ImageGallows;
import gallows.SessionPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;

public class SessionPlayerTest {
    private PrintStream printStream;
    private SessionPlayer sessionPlayer;
    private CheckInputUserImpl checkInputUser;

    @BeforeEach
    void setUp() {
        printStream = new PrintStream(System.out);
        sessionPlayer = new SessionPlayer("test");
        checkInputUser = new CheckInputUserImpl();
    }

    // тест на изменение состояния при ошибке
    @Test
    void displayImage() {
        boolean checkState = sessionPlayer.displayImage(0, printStream);
        Assertions.assertTrue(checkState);
    }

    // тест об отсутствии изменения при верном ответе
    @Test
    void displayImageFalse() {
        boolean checkState = sessionPlayer.displayImage(2, printStream);
        Assertions.assertFalse(checkState);
    }

    // тест на вывод картинки при поражении
    @Test
    void emptyAttempts() {
        ImageGallows imageGallows = new ImageGallows();
        int count = sessionPlayer.countAttempts - 6;
        Assertions.assertEquals(ImageGallows.IMAGES[count], ImageGallows.STEP_6);
    }

    // тест на поражение
    @Test
    void checkCorrectWord() {
        sessionPlayer.currentAnswer("te_t".toCharArray());
        boolean answer = checkInputUser.checkCorrectWord(sessionPlayer.currentAnswer, sessionPlayer.word());
        Assertions.assertFalse(answer);
    }
}
