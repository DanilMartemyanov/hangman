package backend.academy;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import gallowsGame.WordsGallows;
import lombok.experimental.UtilityClass;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/words.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            for(JsonNode i : jsonNode.get("category").findValues("level")){
                System.out.println(i);
            }
        } catch (IOException e) {
            //TO DO: реализовать свой класс Exctption
            throw new RuntimeException(e);
        }

        WordsGallows wordsGallows = new WordsGallows();
        System.out.println(wordsGallows.getRandomCategory());
        System.out.println(wordsGallows.getRandomLevel());


    }
}
