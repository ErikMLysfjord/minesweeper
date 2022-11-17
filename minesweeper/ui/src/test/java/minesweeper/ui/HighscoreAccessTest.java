package minesweeper.ui;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;

import java.net.URI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HighscoreAccessTest {

    private WireMockConfiguration config;
    private WireMockServer wireMockServer;

    private HighscoresAccess access;

    @BeforeEach
    public void setUp() {
        config = WireMockConfiguration.wireMockConfig().port(8080);
        wireMockServer = new WireMockServer(config.portNumber());
        wireMockServer.start();
        WireMock.configureFor("localhost", config.portNumber());
        access = new HighscoresAccess(URI.create(
            "http://localhost:8080/minesweeper/")
        );
    }

    @Test
    public void testGetHighscoreList() {
        stubFor(get(urlEqualTo("/minesweeper/highscorelist/Medium"))
            .withHeader("accept", equalTo("application/json"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(
                "{\"maxSize\":5,\"entries\":[ { \"name\":"
                + "\"Test\", \"score\": 100 } ]}"
        )));

        HighscoreList list = access.getHighscoreList("Medium");
        assertEquals(5, list.getMaxSize());
        assertEquals("Test", list.getHighscoreEntry(0).getName());
        assertEquals(100, list.getHighscoreEntry(0).getScore());
    }

    @Test
    public void testSaveScore() {
        HighscoreEntry entry = new HighscoreEntry("Test", 100);
        access.saveScore(entry, "Medium");
        // Verifies that the correct POST-request was sent.
        verify(postRequestedFor(
            urlEqualTo("/minesweeper/highscorelist/Medium/save"))
            .withHeader("Content-Type", equalTo("application/json"))
            .withRequestBody(equalToJson(
                "{ \"name\":\"Test\", \"score\": 100 }"
        )));
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }
}
