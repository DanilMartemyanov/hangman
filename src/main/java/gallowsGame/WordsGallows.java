package gallowsGame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class WordsGallows {
    private static final File fileWords = new File("src/main/resources/words.json");
//    private static final char EASY = 0;
//    private static final char MIDDLE = 1;
//    private static final char HARD = 3;

    public String getWords(ArrayList<String> words) {
        int quantity = words.size();
        int index = ThreadLocalRandom.current().nextInt(0, quantity + 1);
        return words.get(index);

    }

    public String getRandomCategory() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(fileWords);
            ArrayList<String> category = new ArrayList<>();
            for (JsonNode jsonNode1 : node.get("category")) {
                category.add(jsonNode1.get("title").asText());
            }
            int index = ThreadLocalRandom.current().nextInt(0, category.size());
            return category.get(index);
        } catch (IOException e) {
            //TO DO: реализовать свой класс Exctption
            throw new RuntimeException(e);
        }

    }

    public String getRandomLevel() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(fileWords);
            ArrayList<String> level = new ArrayList<>();
            int sizeLevelArray = node.get("category").findValues("level").size();
            int index = ThreadLocalRandom.current().nextInt(0, sizeLevelArray);
            switch (index) {
                case 0:
                    return "easy";
                case 1:
                    return "middle";
                case 2:
                    return "hard";
            }
            return null;
        } catch (IOException e) {
            //TO DO: реализовать свой класс Exctption
            throw new RuntimeException(e);
        }

    }
}
