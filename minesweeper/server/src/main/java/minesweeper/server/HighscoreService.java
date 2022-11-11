package minesweeper.server;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;
import minesweeper.json.FileHandler;

import org.springframework.stereotype.Service;

@Service
public class HighscoreService {

    private FileHandler fileHandler = new FileHandler();

    /**
     * Gets the highscore read from the json-file.
     * @param difficulty difficulty chosen
     * @return the highscorelist
     */
    public HighscoreList getHighscoreList(final String difficulty) {
        return fileHandler.readHighscoreList(difficulty);
    }

    /**
     * Adds entry to the highscore list in file.
     * @param entry to be added
     * @param difficulty difficulty chosen
     */
    public void addHighscoreEntry(final HighscoreEntry entry,
            final String difficulty) {
        fileHandler.saveScore(entry, difficulty);
    }

}

