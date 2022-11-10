package minesweeper.server;

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

}
