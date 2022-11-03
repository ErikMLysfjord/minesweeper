package minesweeper.json;

import java.io.File;
import java.io.IOException;

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
    private final File highscoreListFile;
    private final File dir = new File(
        "../core/src/main/resources/minesweeper/json/"
    );

    /**
     * Constructor for FileHandler.
     */
    public FileHandler() {
        highscoreListFile = new File(
            "../core/src/main/resources/minesweeper/json/highscoreList.json"
        );
        mapper = registerModule(new ObjectMapper());
        makeFile();
        if (highscoreListFile.length() == 0) {
            setEmptyList();
        }
    }

    /**
     * Constructor for FileHandler, which takes in an address.
     * @param address the address for which file to handle
     */
    public FileHandler(final String address) {
        highscoreListFile = new File(address);
        mapper = registerModule(new ObjectMapper());
        makeFile();
        if (highscoreListFile.length() == 0) {
            setEmptyList();
        }
    }

    /**
     * Registers modules to the mapper.
     * @param objMapper the mapper which will get modules registered
     * @return the mapper, with modules registered
     */
    private ObjectMapper registerModule(final ObjectMapper objMapper) {
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
        objMapper.registerModule(simpleModule);
        return objMapper;
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
        makeFile();
        if (highscoreListFile.length() == 0) {
            setEmptyList();
        }
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

    private void makeFile() {
        try {
            dir.mkdirs();
            highscoreListFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
