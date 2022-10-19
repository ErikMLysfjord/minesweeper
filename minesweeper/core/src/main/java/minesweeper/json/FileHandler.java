package minesweeper.json;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;
import minesweeper.json.internal.HighscoreEntryDeserializer;
import minesweeper.json.internal.HighscoreEntrySerializer;
import minesweeper.json.internal.HighscoreListDeserializer;
import minesweeper.json.internal.HighscoreListSerializer;

public class FileHandler {
    private final ObjectMapper mapper;
    private final File highscoreListFile = new File(
        "../core/src/main/resources/minesweeper/json/highscoreList.json"
    );

    /**
     * Constructor for FileHandler.
     */
    public FileHandler() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(
            HighscoreEntry.class,
            new HighscoreEntryDeserializer()
        );
        simpleModule.addSerializer(
            HighscoreEntry.class,
            new HighscoreEntrySerializer()
        );
        simpleModule.addDeserializer(
            HighscoreList.class,
            new HighscoreListDeserializer()
        );
        simpleModule.addSerializer(
            HighscoreList.class,
            new HighscoreListSerializer()
        );

        mapper = new ObjectMapper();
        mapper.registerModule(simpleModule);
    }

    /**
     * Saves serialized score object to highscore list in data.json.
     * @param score the score to be saved
     */
    public void saveScore(final HighscoreEntry score) {
        HighscoreList highscoreList = readHighscoreList();
        highscoreList.addEntry(score);
        try {
            mapper.writeValue(highscoreListFile, highscoreList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads highscore list from data.json.
     * @return highscore list in data.json
     */
    public HighscoreList readHighscoreList() {
        try {
            return mapper.readValue(
                highscoreListFile,
                HighscoreList.class
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Saves empty highscore list to file.
     */
    public void setEmptyList() {
        HighscoreList highscoreList = new HighscoreList();
        try {
            mapper.writeValue(highscoreListFile, highscoreList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
