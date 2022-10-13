package minesweeper.json;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import minesweeper.core.HighscoreEntry;

public final class FileTreater {

    private FileTreater() {
    }

    /**
     * Saves serialized score object to data.json.
     * @param score the score to be saved
     */
    public static void saveScore(final HighscoreEntry score) {
        File data = new File(
            "../core/src/main/resources/minesweeper/json/data.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(data, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
