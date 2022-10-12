package minesweeper.json;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import minesweeper.core.HighscoreEntry;

public class FileTreater {
    private static String path = "../core/src/main/resources/minesweeper/json/data.json";
    /**
     * Saves serialized score object to data.json.
     * @param score the score to be saved
     */
    public static void saveScore(HighscoreEntry score) {
        File data = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(data, score);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
