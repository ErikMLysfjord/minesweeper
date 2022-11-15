package minesweeper.server;

import minesweeper.core.Difficulty;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;
import minesweeper.json.HighscoresFileHandler;

import org.springframework.stereotype.Service;

@Service
public class HighscoreService {

    /**
     * Gets the highscore read from the json-file.
     * @param difficulty difficulty chosen
     * @return the highscorelist
     */
    public HighscoreList getHighscoreList(final String difficulty) {

        if (difficulty.equals("Test")) {
            HighscoresFileHandler fileHandler = new HighscoresFileHandler(
            "../core/src/main/resources/minesweeper/json/testPersistence.json"
            );
            return fileHandler.readHighscoreList();
        }
        HighscoresFileHandler fileHandler = new HighscoresFileHandler();
        return fileHandler.readHighscoreList(
            Difficulty.getDifficulty(difficulty)
        );
    }

    /**
     * Adds entry to the highscore list in file.
     * @param entry to be added
     * @param difficulty difficulty chosen
     */
    public void addHighscoreEntry(
        final HighscoreEntry entry,
        final String difficulty
    ) {
        if (difficulty == "Test") {
            HighscoresFileHandler fileHandler = new HighscoresFileHandler(
                difficulty
            );
            fileHandler.saveScore(entry);
        } else {
            HighscoresFileHandler fileHandler = new HighscoresFileHandler();
            fileHandler.saveScore(entry, Difficulty.getDifficulty(difficulty));
        }
    }

    /**
     * Checks whether the given difficulty is valid.
     * @param difficulty difficulty given
     * @return whether it is valid
     */
    public boolean difficultyIsValid(final String difficulty) {
        System.out.println(difficulty.equals("Test"));
        return difficulty.equals("Test")
        || Difficulty.getDifficulty(difficulty) != null;
    }

}

