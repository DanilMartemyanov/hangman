package gallows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;


@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class WordsGallows {
    private static final File FILE = new File("src/main/resources/words.json");
    private SecureRandom random = new SecureRandom();

    public String getRandomWords(ArrayList<String> words) {
        int quantity = words.size();
        int index = random.nextInt(0, quantity);
        return words.get(index);
    }

    public String getRandomCategory() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(FILE);
            ArrayList<String> category = new ArrayList<>();
            for (JsonNode jsonNode1 : node.get("category")) {
                category.add(jsonNode1.get("title").asText());
            }
            int index = random.nextInt(0, category.size());

            return category.get(index).substring(0, 1);
        } catch (IOException e) {
            // TODO: реализовать свой класс Exctption
            throw new RuntimeException(e);
        }

    }

    public String getRandomLevel() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(FILE);
            int sizeLevelArray = node.get("category").findValues("level").size();
            int index = random.nextInt(0, sizeLevelArray);
            switch (index) {
                case 0:
                    return "e";
                case 1:
                    return "m";
                case 2:
                    return "h";
                default:
                    return null;
            }
        } catch (IOException e) {
            //TO DO: реализовать свой класс Exctption
            throw new RuntimeException(e);
        }

    }

    public ArrayList<String> getAllCategories() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(FILE);
            ArrayList<String> categories = new ArrayList<>();
            for (JsonNode jsonNode : node.get("category")) {
                categories.add(jsonNode.get("title").asText());
            }
            return categories;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getJsonWords(String category, String levelUser) {
        ObjectMapper objectMapper = new ObjectMapper();
        int indexCategory;
        int count = 0;
        String level;
        try {
            JsonNode node = objectMapper.readTree(FILE);
            switch (category) {
                case "m":
                    indexCategory = 0;
                    break;
                case "a":
                    indexCategory = 1;
                    break;
                case "c":
                    indexCategory = 2;
                    break;
                default:
                    indexCategory = -1;
            }
            switch (levelUser) {
                case "e":
                    level = "easy";
                    break;
                case "m":
                    level = "middle";
                    break;
                case "h":
                    level = "hard";
                    break;
                default:
                    level = "";
            }
            for (JsonNode categoryNode : node.path("category")) {
                for (JsonNode levelNode : categoryNode.path("level")) {
                    for (JsonNode wordsNode : levelNode.path(level)) {
                        if (indexCategory == count) {
                            return wordsNode;
                        }
                        count++;
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public HashMap<String, String> getWord(String category, String level) {
        JsonNode jsonNode = getJsonWords(category, level);
        int index = random.nextInt(0, jsonNode.findValues("answer")
            .toArray().length);
        String answer = jsonNode.findValues("answer").toArray()[index].toString().replace("\"", "");
        String description = jsonNode.findValues("description").toArray()[index].toString();
        HashMap<String, String> wordGame = new HashMap<>();
        wordGame.put(answer, description);
        return wordGame;
    }
}
