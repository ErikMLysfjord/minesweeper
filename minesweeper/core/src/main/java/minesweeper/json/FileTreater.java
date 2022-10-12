package minesweeper.json;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import minesweeper.core.HighscoreEntry;

public class FileTreater {
    private static String path = "../core/src/main/resources/minesweeper/json/data.json";
    /**
     * Saves serialized score object to data.json.
     * @param score the score to be saved
     */
    public static void saveScore(HighscoreEntry score) {
        File data = new File(getPath());
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(data, score);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getScore(String name){
    }

    public static String getPath(){
        return path;
    }

}
