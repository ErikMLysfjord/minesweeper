package minesweeper.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

@RestController
@RequestMapping("minesweeper")
public class HighscoreController {

    @Autowired
    private HighscoreService service;

    /**
     * Get the highscore list.
     * @return the highscore list
     */
    @GetMapping("/highscorelist")
    public HighscoreList getHighscoreList() {
        return service.getHighscoreList();
    }

    /**
     * Saves highscore entry in the server's highscore list.
     * @param entry to be saved
     */
    @PostMapping("/highscorelist")
    public void addHighscoreEntry(final @RequestBody HighscoreEntry entry) {
        service.addHighscoreEntry(entry);
    }

}
