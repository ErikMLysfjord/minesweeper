package minesweeper.server;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param difficulty difficulty chosen
     * @return the highscore list
     * @param response Http response
     * @throws IOException
     */
    @GetMapping("/highscorelist/{difficulty}")
    public HighscoreList getHighscoreList(
        final @PathVariable("difficulty") String difficulty,
        final HttpServletResponse response
    ) throws IOException {
        if (service.difficultyIsValid(difficulty)) {
            return service.getHighscoreList(difficulty);
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    /**
     * Saves highscore entry in the server's highscore list.
     * @param entry to be saved
     * @param difficulty difficulty chosen
     * @param response Http response
     * @return true to indicate success
     */
    @PostMapping("/highscorelist/{difficulty}/save")
    public boolean addHighscoreEntry(
        final @RequestBody HighscoreEntry entry,
        final @PathVariable("difficulty") String difficulty,
        final HttpServletResponse response
    ) throws IOException {
        if (service.difficultyIsValid(difficulty)) {
            service.addHighscoreEntry(entry, difficulty);
            return true;
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return false;
    }

}
