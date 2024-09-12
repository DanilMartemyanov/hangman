package gallows;

import lombok.NoArgsConstructor;

@SuppressWarnings("checkstyle:OperatorWrap")
@NoArgsConstructor
public class ImageGallows {
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static final String STEP_1 = "" +

        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "                   ";
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static final String STEP_2 = "" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "/|\\\n" +
        "                   ";
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static final String STEP_3 = "" +
        " __________________\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "/|\\\n" +
        "                   ";
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static final String STEP_4 = "" +
        " __________________\n" +
        " |/\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "/|\\\n" +
        "                   ";
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static final String STEP_5 = "" +
        " __________________\n" +
        " |/               |\n" +
        " |                |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "/|\\\n" +
        "                   ";
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static final String STEP_6 = "" +
        " __________________\n" +
        " |/               |\n" +
        " |                |\n" +
        " |                0\n" +
        " |               /|\\\n" +
        " |                |\n" +
        " |               / \\\n" +
        "/|\\\n" +
        "                   ";

    public static final String[] IMAGES = {STEP_6, STEP_5, STEP_4, STEP_3, STEP_2, STEP_1};
}
