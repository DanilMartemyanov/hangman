package gallows;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        GameInterface gameInterface = new GameInterface();
        gameInterface.userInterface();
    }
}
