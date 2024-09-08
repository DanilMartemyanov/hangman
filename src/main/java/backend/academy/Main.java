package backend.academy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gallows.ImageGallows;
import gallows.LogicFindCorrectCharImpl;
import gallows.WordPlayer;
import gallows.WordsGallows;
import lombok.experimental.UtilityClass;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/words.json");
        Pattern pattern = Pattern.compile("^[masn]$");
        Matcher matcher = pattern.matcher("m");
        System.out.println(matcher.find());
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

        String emptyString = "привет".replaceAll(".", "_");
        System.out.println(emptyString);

        LogicFindCorrectCharImpl logicFindCorrectCharImpl = new LogicFindCorrectCharImpl();
//        System.out.println(logicFindCorrectCharImpl.checkChar('п', "Привет"));
        System.out.println(ImageGallows.STEP_1);
        System.out.println(ImageGallows.STEP_2);
        System.out.println(ImageGallows.STEP_3);
        System.out.println(ImageGallows.STEP_4);
        System.out.println(ImageGallows.STEP_5);
        System.out.println(ImageGallows.STEP_6);

        WordPlayer wordPlayer = new WordPlayer("собака");
        System.out.println(logicFindCorrectCharImpl.checkChar(wordPlayer, 'а').currentAnswer());
        System.out.println(wordPlayer.currentAnswer());
        System.out.println(logicFindCorrectCharImpl.checkChar(wordPlayer, 'р').currentAnswer());
        System.out.println(wordPlayer.currentAnswer());
        System.out.println(wordPlayer.word());

        System.out.println(wordsGallows.getAllCategories());

    }
}
