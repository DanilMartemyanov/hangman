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
    @JsonProperty(Constant.CATEGORY)
    private JsonNode category;
    @JsonProperty(Constant.LEVEL)
    private JsonNode level;
    @JsonProperty(Constant.TITLE)
    private JsonNode title;
    @JsonProperty(Constant.EASY)
    private JsonNode easy;
    @JsonProperty(Constant.MIDDLE)
    private JsonNode middle;
    @JsonProperty(Constant.HARD)
    private JsonNode hard;
    private JsonNode answer;
    private JsonNode description;


}
