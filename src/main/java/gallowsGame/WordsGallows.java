package gallowsGame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class WordsGallows {
    public String getWords(ArrayList<String> words) {
        int quantity = words.size();
        int index = ThreadLocalRandom.current().nextInt(0, quantity + 1);
        return words.get(index);

    }

    public String getRandomWordsforGame(){
        return null;
    }

    public String getRandomCategory(){
        return null;
    }
}
