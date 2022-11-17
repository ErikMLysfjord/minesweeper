package minesweeper.ui;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            "http://localhost:8089/minesweeper")
        );
    }

    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }
}
