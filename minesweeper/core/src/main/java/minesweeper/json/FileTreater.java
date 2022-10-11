package minesweeper.json;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import minesweeper.core.HighscoreEntry;

public class FileTreater {
    
    public static void saveScore(HighscoreEntry score) {
        File data = new File("../core/src/main/resources/minesweeper/json/data.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(data, score);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
