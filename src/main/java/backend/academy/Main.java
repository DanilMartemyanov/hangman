package backend.academy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gallows.LogicFindCorrectCharImpl;
import gallows.WordsGallows;
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
            for (JsonNode i : jsonNode.get("category").findValues("level")) {
                System.out.println(i);
            }
        } catch (IOException e) {
            //TO DO: реализовать свой класс Exctption
            throw new RuntimeException(e);
        }

        WordsGallows wordsGallows = new WordsGallows();
//        System.out.println(wordsGallows.getRandomCategory());
        System.out.println(wordsGallows.getRandomLevel());

        String emptyString = "привет".replaceAll(".","_");
        System.out.println(emptyString);

        LogicFindCorrectCharImpl logicFindCorrectCharImpl = new LogicFindCorrectCharImpl();
        System.out.println(logicFindCorrectCharImpl.checkChar('п', "Привет"));


    }
}
