package gallows;

public interface CheckInputUser {
    int equalsCharArray(char[] currentEnter, char[] currentAnswer);

    boolean checkCorrectWord(char[] currentAnswer, String word);
}
