package gallows;

public class CheckInputUserImpl implements CheckInputUser {

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
