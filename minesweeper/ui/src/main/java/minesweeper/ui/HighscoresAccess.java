package minesweeper.ui;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

import minesweeper.core.HighscoreList;
import minesweeper.json.FileHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HighscoresAccess {

    private final URI uri;
    private ObjectMapper mapper;

    /**
     * Constructor for HighscoresAccess.
     * @param uri the URI-address
     */
    public HighscoresAccess(final URI uri) {
        this.uri = uri;
        mapper = new FileHandler().getMapper();
    }

    /**
     * Gets the highscore list with an HTTP-request from server.
     * @return the highscore list
     * @throws URISyntaxException if the URI is not found
     */
    public HighscoreList getHighscoreList() throws URISyntaxException {
        HttpRequest request = HttpRequest
            .newBuilder(uri.resolve("highscorelist"))
            .header("accept", "application/json")
            .GET()
            .build();

        HighscoreList highscoreList;
        try {
            final HttpResponse<String> response =
                HttpClient.newHttpClient().send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
                );

            highscoreList = mapper.readValue(
                response.body(),
                HighscoreList.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return highscoreList;
    }
}
