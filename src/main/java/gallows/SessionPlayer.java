package gallows;

import java.io.PrintStream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionPlayer {
    public int countAttempts = Constant.ATTEMPTS;
    private String word;
    private String tip;
    private char[] currentEnter;
    public char[] currentAnswer;

    public SessionPlayer(String word) {
        this.word = word;
        this.currentAnswer = new char[word.length()];
    }

    public boolean displayImage(int countMatches, PrintStream printStream) {
        if (countMatches == 0) {
            printStream.println(ImageGallows.IMAGES[this.countAttempts - 1]);
            this.countAttempts--;
            return true;
        }
        printStream.println(this.currentAnswer);
        return false;
    }

}
