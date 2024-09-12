package gallows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParsingJsonWords {
    @JsonProperty("category")
    private JsonNode category;
    @JsonProperty("level")
    private JsonNode level;
    @JsonProperty("name1")
    private JsonNode name1;
    @JsonProperty("name2")
    private JsonNode name2;
    @JsonProperty("name3")
    private JsonNode name3;
    @JsonProperty("title")
    private JsonNode title;
    @JsonProperty("easy")
    private JsonNode easy;
    @JsonProperty("middle")
    private JsonNode middle;
    @JsonProperty("hard")
    private JsonNode hard;
    private JsonNode answer;
    private JsonNode description;
    @JsonProperty("word1")
    private JsonNode word1;
    @JsonProperty("word2")
    private JsonNode word2;
    @JsonProperty("word3")
    private JsonNode word3;

}
