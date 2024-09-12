package gallows;

import java.util.regex.Pattern;

@SuppressWarnings("checkstyle:ConstantName")
public class CheckInputUserImpl implements CheckInputUser {
    static final String REGEXCATEGORY = "^[macn]$";
    static final Pattern patternCategory = Pattern.compile(REGEXCATEGORY);
    static final String REGEXLEVEL = "^[emhn]$";
    static final Pattern patternLevel = Pattern.compile(REGEXLEVEL);
    static final String STARTGAME = "^[yn]$";
    static final Pattern patternStartGame = Pattern.compile(STARTGAME);
    static final String GETTIP = "^[1\\S\s]$";
    static final Pattern patternGetTip = Pattern.compile(GETTIP);


    @Override
    public int equalsCharArray(char[] currentEnter, char[] currentAnswer) {
        int count = 0;
        for (int i = 0; i < currentAnswer.length; i++) {
            if (currentEnter[i] != '_') {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean checkCorrectWord(char[] currentAnswer, String word) {
        int count = 1;
        for (int i = 0; i < currentAnswer.length; i++) {
            if (currentAnswer[i] == word.charAt(i)) {
                count++;
            }
        }
        if (count == word.length()) {
            return true;
        }
        return false;
    }
}
