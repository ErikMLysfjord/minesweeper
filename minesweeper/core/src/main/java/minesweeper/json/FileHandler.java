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
    private static final File DIR = new File(
        "../core/src/main/resources/minesweeper/json/"
    );
    private static final File EASYFILE = new File(
        "../core/src/main/resources/minesweeper/json/easyHighscoreList.json"
    );
    private static final File MEDIUMFILE = new File(
        "../core/src/main/resources/minesweeper/json/mediumHighscoreList.json"
    );
    private static final File HARDFILE = new File(
        "../core/src/main/resources/minesweeper/json/hardHighscoreList.json"
    );

    /**
     * Constructor for FileHandler.
     */
    public FileHandler() {
        highscoreListFile = null;
        mapper = registerModule(new ObjectMapper());
        makeFiles();
    }

    /**
     * Get-method for the objectmapper.
     * @return the objectmapper
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * Constructor for FileHandler, which takes in an address.
     * @param address the address for which file to handle
     */
    public FileHandler(final String address) {
        highscoreListFile = new File(address);
        mapper = registerModule(new ObjectMapper());
        makeFiles();
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
     * Saves serialized score object to highscore list in the file
     * corresponding to the difficulty.
     * @param score the score to be saved
     * @param difficulty the difficulty file to be saved to
     */
    public void saveScore(
            final HighscoreEntry score,
            final String difficulty) {

        HighscoreList highscoreList = readHighscoreList(difficulty);
        highscoreList.addEntry(score);
        try {
            switch (difficulty) {
                case "easy":
                    mapper.writeValue(EASYFILE, highscoreList);
                    break;
                case "medium":
                    mapper.writeValue(MEDIUMFILE, highscoreList);
                    break;
                case "hard":
                    mapper.writeValue(HARDFILE, highscoreList);
                        break;
                default:
                    mapper.writeValue(EASYFILE, highscoreList);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * Reads highscore list from the file corresponding to the difficulty.
     * @param difficulty difficulty chosen
     * @return highscorelist in the file
     */
    public HighscoreList readHighscoreList(final String difficulty) {
        makeFiles();
        try {
            switch (difficulty) {
                case "easy":
                    return mapper.readValue(
                        EASYFILE,
                        HighscoreList.class
                    );
                case "medium":
                    return mapper.readValue(
                        MEDIUMFILE,
                        HighscoreList.class
                    );
                case "hard":
                    return mapper.readValue(
                        HARDFILE,
                        HighscoreList.class
                    );

                default:
                    return mapper.readValue(
                        EASYFILE,
                        HighscoreList.class
                    );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Reads highscore list from highscorelistFile.
     * @return highscore list in highscorelistFile
     */
    public HighscoreList readHighscoreList() {
        makeFiles();
        try {
            return mapper.readValue(highscoreListFile, HighscoreList.class);
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
            if (highscoreListFile != null && highscoreListFile.length() == 0) {
                mapper.writeValue(highscoreListFile, highscoreList);
            }
            if (EASYFILE.length() == 0) {
                mapper.writeValue(EASYFILE, highscoreList);
            }
            if (MEDIUMFILE.length() == 0) {
                mapper.writeValue(MEDIUMFILE, highscoreList);
            }
            if (HARDFILE.length() == 0) {
                mapper.writeValue(HARDFILE, highscoreList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeFiles() {
        try {
            DIR.mkdirs();
            if (highscoreListFile != null) {
                highscoreListFile.createNewFile();
            }
            EASYFILE.createNewFile();
            MEDIUMFILE.createNewFile();
            HARDFILE.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setEmptyList();
    }
}

