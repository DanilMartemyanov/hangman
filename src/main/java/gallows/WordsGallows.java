package gallows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsGallows {
    private static InputStream file = getFileFromResourceAsStream("json/words.json");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final JsonNode NODE;
    private SecureRandom random = new SecureRandom();

    static {
        try {
            NODE = MAPPER.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomWords(ArrayList<String> words) {
        int quantity = words.size();
        int index = random.nextInt(0, quantity);
        return words.get(index);
    }

    public String getRandomCategory() {
        ArrayList<String> category = new ArrayList<>();
        for (JsonNode jsonNode1 : NODE.get(Constant.CATEGORY)) {
            category.add(jsonNode1.get(Constant.TITLE).asText());
        }
        int index = random.nextInt(0, category.size());

        return category.get(index).substring(0, 1).toLowerCase();

    }

    public String getRandomLevel() {
        int sizeLevelArray = NODE.get(Constant.CATEGORY).findValues(Constant.LEVEL).size();
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
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<String> categories = new ArrayList<>();
        for (JsonNode jsonNode : NODE.get(Constant.CATEGORY)) {
            categories.add(jsonNode.get(Constant.TITLE).asText());
        }
        return categories;
    }

    public JsonNode getJsonWords(String category, String levelUser) {
        int indexCategory;
        int count = 0;
        String level;
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
        for (JsonNode categoryNode : NODE.path(Constant.CATEGORY)) {
            for (JsonNode levelNode : categoryNode.path(Constant.LEVEL)) {
                for (JsonNode wordsNode : levelNode.path(level)) {
                    if (indexCategory == count) {
                        return wordsNode;
                    }
                    count++;
                }
            }

        }
        return null;
    }

    public HashMap<String, String> getWord(String category, String level) {
        JsonNode jsonNode = getJsonWords(category, level);
        int index = random.nextInt(0, jsonNode.findValues(Constant.ANSWER)
            .toArray().length);
        String answer = jsonNode.findValues(Constant.ANSWER).toArray()[index].toString().replace("\"", "");
        String description = jsonNode.findValues(Constant.DESCRIPTION).toArray()[index].toString();
        HashMap<String, String> wordGame = new HashMap<>();
        wordGame.put(answer, description);
        return wordGame;
    }

    public boolean checkWord(String word) {
        return "".equals(word);
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = WordsGallows.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        if (is == null) {
            throw new IllegalArgumentException("Resource not found");
        } else {
            return is;
        }
    }
}
