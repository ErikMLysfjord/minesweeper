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
    private HighscoreList highscoreList;

    /**
     * Constructor for the HighscoresAccess.
     * @param uri the address
     */
    public HighscoresAccess(final URI uri) {
        this.uri = uri;
        mapper = new FileHandler().getMapper();
    }

    /**
     * Get the highscorelist with a HTTP-request.
     * @return the highscorelist
     * @throws URISyntaxException if the URI is not found
     */
    public HighscoreList getHighscoreList() throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(uri
            .resolve("highscorelist"))
            .header("accept", "application/json")
            .GET()
            .build();
        try {
            final HttpResponse<String> response =
            // another way of doing it
            /* HttpClient.newBuilder().build().send(request, HttpResponse
                .BodyHandlers.ofString()
            ); */
            HttpClient.newHttpClient().send(request, HttpResponse
                .BodyHandlers.ofString()
            );
            this.highscoreList = mapper.
                readValue(response.body(), HighscoreList.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this.highscoreList;
    }
}
