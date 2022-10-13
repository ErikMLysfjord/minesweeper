package minesweeper.json;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import minesweeper.core.HighscoreEntry;
import minesweeper.json.internal.HighscoreEntryDeserializer;
import minesweeper.json.internal.HighscoreEntrySerializer;

public class FileTreater {
    private final ObjectMapper mapper;
    private final File data = new File(
        "../core/src/main/resources/minesweeper/json/data.json"
    );

    /**
     * Constructer for FileTreater.
     */
    public FileTreater() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(
            HighscoreEntry.class,
            new HighscoreEntryDeserializer()
        );
        simpleModule.addSerializer(
            HighscoreEntry.class,
            new HighscoreEntrySerializer()
        );

        mapper = new ObjectMapper();
        mapper.registerModule(simpleModule);
    }

    /**
     * Saves serialized score object to data.json.
     * @param score the score to be saved
     */
    public void saveScore(final HighscoreEntry score) {
        try {
            mapper.writeValue(data, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the entry from data.json.
     * @return HighscoreEntry
     */
    public HighscoreEntry readEntry() {
        try {
            return mapper.readValue(data, HighscoreEntry.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
