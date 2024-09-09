package gallows;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordPlayer {
    private String word;
    private char[] currentEnter;
    public char[] currentAnswer;

    public WordPlayer(String word) {
        this.word = word;
        this.currentAnswer = new char[word.length()];
    }

}
