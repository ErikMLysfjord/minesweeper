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
     * @return the highscorelist
     */
    public HighscoreList getHighscoreList() {
        return fileHandler.readHighscoreList();
    }

    /**
     * Adds entry to the highscore list in file.
     * @param entry to be added
     */
    public void addHighscoreEntry(final HighscoreEntry entry) {
        fileHandler.saveScore(entry);
    }

}
