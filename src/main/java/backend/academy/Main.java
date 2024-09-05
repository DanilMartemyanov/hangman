package backend.academy;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import gallowsGame.WordsGame;
import lombok.experimental.UtilityClass;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/words.json");
        ObjectMapper objectMapper = new ObjectMapper();
        WordsGame wordsGame = objectMapper.readValue(file, WordsGame.class);


    }
}
