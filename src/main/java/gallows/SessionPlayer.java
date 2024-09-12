package gallows;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SessionPlayer {
    @SuppressWarnings("checkstyle:MagicNumber")
    public int countAttempts = 6;
    private String word;
    private char[] currentEnter;
    public char[] currentAnswer;

    public SessionPlayer(String word) {
        this.word = word;
        this.currentAnswer = new char[word.length()];
    }

}
