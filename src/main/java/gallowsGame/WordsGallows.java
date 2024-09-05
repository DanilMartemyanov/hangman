package gallowsGame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class WordsGallows {
    private static File fileWords = new File("src/recources/words.json");
    public String getWords(ArrayList<String> words) {
        int quantity = words.size();
        int index = ThreadLocalRandom.current().nextInt(0, quantity + 1);
        return words.get(index);

    }

    public String getRandomWordsforGame(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(fileWords);
        } catch (IOException e) {
            //TO DO: реализовать свой класс Exctption
            throw new RuntimeException(e);
        }

        return null;
    }

    public String getRandomCategory(){
        return null;
    }
}
