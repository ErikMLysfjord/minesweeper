package minesweeper.json;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import minesweeper.core.HighscoreEntry;

public class FileTreater {
    private ObjectMapper mapper;

    public FileTreater() {
        mapper = new ObjectMapper();
    }

    /**
     * Saves serialized score object to data.json.
     * @param score the score to be saved
     */
    public void saveScore(final HighscoreEntry score) {
        File data = new File(
            "../core/src/main/resources/minesweeper/json/data.json"
        );

        try {
            mapper.writeValue(data, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
