package minesweeper.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

public class HighscoresViewTest extends ApplicationTest {

    private WireMockConfiguration config;
    private WireMockServer wireMockServer;

    private Parent root;
    private Stage stage;
    private Scene initialScene;
    private HighscoreList highscoreList = new HighscoreList(3);

    public HighscoresViewTest() {
        highscoreList.addEntry(new HighscoreEntry("Erik", 500));
        highscoreList.addEntry(new HighscoreEntry("Ole", 1000));
        highscoreList.addEntry(new HighscoreEntry("Li", 200));
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        SceneManager sceneSwitcher = new SceneManager(stage);
        sceneSwitcher.setHighscores(highscoreList);
        initialScene = stage.getScene();
        root = initialScene.getRoot();
    }

    public void setUpServer() {
        config = WireMockConfiguration.wireMockConfig().port(8080);
        wireMockServer = new WireMockServer(config.portNumber());
        wireMockServer.start();
        WireMock.configureFor("localhost", config.portNumber());
    }

    public Parent getRootNode() {
        return root;
    }

    private Node getElementById(String id) {
        return root.lookup(id);
    }

    /**
     * Method for getting the column containing names.
     * Surpresses warnings as we know the value of the tableview, and that
     * it won't create problems.
     * @return the column containing names
     */
    @SuppressWarnings (value = "unchecked")
    private TableColumn<HighscoreEntry, String> getNames() {
        TableView<HighscoreEntry> list = (TableView<HighscoreEntry>) 
            getElementById("#highscores");

        return (TableColumn<HighscoreEntry, String>) list.getColumns().get(0);
    }

    /**
     * A method for returning the column containing scores.
     * Surpresses warnings as we know the value of the tableview, and that
     * it won't create problems.
     * @return the column containing scores
     */
    @SuppressWarnings (value = "unchecked")
    private TableColumn<HighscoreEntry, Integer> getScores() {
        TableView<HighscoreEntry> list = (TableView<HighscoreEntry>) 
            getElementById("#highscores");

        return (TableColumn<HighscoreEntry, Integer>) list.getColumns().get(1);
    }

    @Test
    public void testList() {
        TableColumn<HighscoreEntry, String> names = getNames();
        int i = 0;
        for (HighscoreEntry entry : highscoreList) {
            Assertions.assertTrue(entry.getName().equals(names.getCellData(i)));
            i++;
        }

        TableColumn<HighscoreEntry, Integer> scores = getScores();
        int j = 0;
        for (HighscoreEntry entry : highscoreList) {
            Assertions.assertTrue(entry.getScore() == scores.getCellData(j));
            j++;
        }
    }

    /**
     * Tests switching scenes, by pressing the "Back"-button.
     */
    @Test
    public void testGoBack() {
        Node button = getElementById("#backButton");
        clickOn(button, MouseButton.PRIMARY);
        Scene currentScene = stage.getScene();

        Assertions.assertNotEquals(initialScene, currentScene);
    }

    @Test
    public void testDifficultyChoiceBox() {
        // Sets up the wiremock-server, so the application can switch
        // difficulties.
        setUpServer();
        stubFor(get(urlEqualTo("/minesweeper/highscorelist/Medium"))
            .withHeader("accept", equalTo("application/json"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"maxSize\":5,\"entries\":[]}"))
        );

        clickOn("#difficultyChoiceBox");
        clickOn("Medium");
        Scene currentScene = stage.getScene();

        Assertions.assertNotEquals(initialScene, currentScene);

        tearDown();
    }

    public void tearDown() {
        wireMockServer.stop();
    }
}
