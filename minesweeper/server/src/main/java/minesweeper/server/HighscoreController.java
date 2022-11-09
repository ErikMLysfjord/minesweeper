package minesweeper.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minesweeper.core.HighscoreList;

@RestController
@RequestMapping("minesweeper")
public class HighscoreController {

    @Autowired
    private HighscoreService service;

    /**
     * Get the highscorelist.
     * @return the highscorelist
     */
    @GetMapping("/highscorelist")
    public HighscoreList getHighscoreList() {
        return service.getHighscoreList();
    }
}
