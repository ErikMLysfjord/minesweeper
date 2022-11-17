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

/**
 * Has mappings for the REST API. See docs/REST.md for REST API documentation.
 */
@RestController
@RequestMapping("minesweeper")
public class HighscoreController {

    @Autowired
    private HighscoreService service;

    /**
     * GET mappping for getting the highscore list of a difficulty.
     * Responds with an error if difficulty is not Easy, Medium or Hard.
     * @param difficulty difficulty of highscore list
     * @param response HTTP-response
     * @return the highscore list
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
     * POST mapping for saving highscore entry in the server's highscore list.
     * Responds with an error if difficulty is not Easy, Medium or Hard.
     * @param entry the entry to save
     * @param difficulty difficulty of highscore list to save to
     * @param response HTTP response
     * @return whether or not the request was successful
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
