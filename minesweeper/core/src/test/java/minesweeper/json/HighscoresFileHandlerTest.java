package minesweeper.json;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import minesweeper.core.HighscoreEntry;

public class HighscoresFileHandlerTest {

    private HighscoresFileHandler fileHandler;
    private String rPath = "../core/src/main/resources/minesweeper/json/testPersistence.json";

    @BeforeEach
    public void setUp() {
        fileHandler = new HighscoresFileHandler(rPath);
    }

    @Test
    public void testGetList() {
        // Asserts that there exists a highscorelist in the file
        Assertions.assertEquals(5, fileHandler.readHighscoreList().
            getMaxSize()
        );
        // Asserts that the initial highscorelist is empty
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            fileHandler.readHighscoreList().getHighscoreEntry(0);
        });
    }

    @Test
    public void testAddHighscores() {
        HighscoreEntry entry1 = new HighscoreEntry("First-test", 10);
        HighscoreEntry entry2 = new HighscoreEntry("Second-test", 1000);
        fileHandler.saveScore(entry1);
        // Asserts that the entry in the file is equal to the highscore-entry,
        // although they are two different  objects.
        Assertions.assertEquals(0, fileHandler.readHighscoreList().
            getHighscoreEntry(0).compareTo(entry1)
        );
        // Further asserts that the highscore-entries are the same,
        // by comparing names.
        Assertions.assertEquals(entry1.getName(), fileHandler.
            readHighscoreList().getHighscoreEntry(0).getName()
        );
        // The same assertions as before, just with a new highscore-entry.
        fileHandler.saveScore(entry2);
        Assertions.assertEquals(0, fileHandler.readHighscoreList().
            getHighscoreEntry(1).compareTo(entry2)
        );
        Assertions.assertEquals(entry2.getName(), fileHandler.
            readHighscoreList().getHighscoreEntry(1).getName()
        );
    }

    @AfterEach
    public void tearDown() {
        new File(rPath).delete();
    }

}
