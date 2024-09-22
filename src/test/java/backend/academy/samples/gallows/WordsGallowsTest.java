package backend.academy.samples.gallows;


import gallows.WordsGallows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordsGallowsTest {
    private WordsGallows wordsGallows;

    @BeforeEach
    void setUp() {
        wordsGallows = new WordsGallows();
    }

    // тест на извлечение не пустого json
    @Test
    void getJsonWords() {
        Assertions.assertNotNull(
            wordsGallows.getJsonWords(wordsGallows.getRandomCategory(), wordsGallows.getRandomLevel()));
    }

    // тест на извлечение не пустого уровня
    @Test
    void getRandomLevel() {
        String level = wordsGallows.getRandomLevel();
        Assertions.assertNotEquals("", level);
    }
    // тест на завершение игры в случае некорректной длины слова
    @Test
    void checkWord() {
        Assertions.assertTrue(wordsGallows.checkWord(""));
    }

}
