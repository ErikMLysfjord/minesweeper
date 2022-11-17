package minesweeper.server;

import minesweeper.core.Difficulty;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;
import minesweeper.json.HighscoresFileHandler;

import org.springframework.stereotype.Service;

/**
 * Used by HighscoreController to save to server using HighscoresFileHandler.
 */
@Service
public class HighscoreService {
    private HighscoresFileHandler fileHandler = new HighscoresFileHandler();

    /**
     * Gets the highscore from the json-file.
     * @param difficulty highscore list's difficulty
     * @return the highscore list
     */
    public HighscoreList getHighscoreList(final String difficulty) {
        return fileHandler.readHighscoreList(
            Difficulty.getDifficulty(difficulty)
        );
    }

    /**
     * Adds entry to the highscore list in file.
     * @param entry to be added
     * @param difficulty highscore list's difficulty
     */
    public void addHighscoreEntry(
        final HighscoreEntry entry,
        final String difficulty
    ) {
        fileHandler.saveScore(entry, Difficulty.getDifficulty(difficulty));
    }

    /**
     * Checks whether the given difficulty is valid or not.
     * @param difficulty difficulty given
     * @return whether it is valid or not
     */
    public boolean difficultyIsValid(final String difficulty) {
        return Difficulty.getDifficulty(difficulty) != null;
    }
}
