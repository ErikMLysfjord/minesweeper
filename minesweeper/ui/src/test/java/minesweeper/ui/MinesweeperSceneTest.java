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
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minesweeper.core.Difficulty;

public class MinesweeperSceneTest extends ApplicationTest {

    private Parent root;
    private Stage stage;
    private Scene initialScene;

    private WireMockConfiguration config;
    private WireMockServer wireMockServer;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        SceneManager sceneSwitcher = new SceneManager(stage);
        sceneSwitcher.setMinesweeper();
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

    private boolean nodeExists(String id) {
        return getElementById(id) != null;
    }

    @Test
    public void testSetFlag() {
        Assertions.assertFalse(nodeExists("#image00flag"));
        clickOn("#button00", MouseButton.SECONDARY);
        Assertions.assertTrue(nodeExists("#image00flag"));

        /*If this fails it might be because you added a new button without
          focusTraversable="false"
        */
        Assertions.assertFalse(nodeExists("#image01flag"));
        moveTo("#button01");
        press(KeyCode.SPACE);
        release(KeyCode.SPACE);
        Assertions.assertTrue(nodeExists("#image01flag"));
        clickOn("#button01", MouseButton.SECONDARY);
        Assertions.assertFalse(nodeExists("#image01flag"));
    }

    @Test
    public void testGridPane() {
        GridPane minefieldGridPane = (GridPane) getElementById("#minefieldGridPane");

        Assertions.assertEquals(
            Difficulty.EASY.getWidth(),
            minefieldGridPane.getColumnCount()
        );

        Assertions.assertEquals(
            Difficulty.EASY.getHeight(),
            minefieldGridPane.getRowCount()
        );

        Assertions.assertTrue(minefieldGridPane.isGridLinesVisible());
    }

    @Test
    public void testCheckSquare() {
        Node button = getElementById("#button00");
        // The first square pressed is supposed to have 0 adjacent mines.
        Assertions.assertFalse(nodeExists("#image00opened0"));
        clickOn(button, MouseButton.PRIMARY);
        Assertions.assertTrue(nodeExists("#image00opened0"));
    }
    /**
     * Test switching scenes, by pressing the "See highscores"-button.
     */
    @Test
    public void testCheckHighscores() {
        // Sets up the wiremock-server, so the application can switch scenes
        setUpServer();
        stubFor(get(urlEqualTo("/minesweeper/highscorelist/Easy"))
            .withHeader("accept", equalTo("application/json"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"maxSize\":5,\"entries\":[]}"))
        );

        Node button = getElementById("#checkHighscoresButton");
        clickOn(button, MouseButton.PRIMARY);

        Scene currentScene = stage.getScene();
        Assertions.assertNotEquals(initialScene, currentScene);

        tearDown();
    }

    @Test
    public void testRestart() {
        clickOn("#button00");
        Assertions.assertTrue(nodeExists("#image00opened0"));
        clickOn("#restart");
        Assertions.assertFalse(nodeExists("#image00opened0"));
    }

    @Test
    public void testAutoOpenAround0() {
        clickOn("#button11");
        Assertions.assertTrue(nodeExists("#image00opened0"));
        Assertions.assertTrue(nodeExists("#image10opened0"));
        Assertions.assertTrue(nodeExists("#image01opened0"));
    }

    @Test
    public void testDifficultyChoiceBox() {
        Assertions.assertFalse(nodeExists("#button1010"));
        clickOn("#difficultyChoiceBox");
        clickOn("Medium");
        Assertions.assertTrue(nodeExists("#button1010"));
        clickOn("#difficultyChoiceBox");
        clickOn("Easy");
        Assertions.assertFalse(nodeExists("#button1010"));
    }

    public void testFlagsLeft() {
        Label flagsLeft = (Label) getElementById("#flagsLeftLabel");
        Assertions.assertEquals(10, Integer.valueOf(flagsLeft.getText()));
        clickOn("#button00", MouseButton.SECONDARY);
        clickOn("#button01", MouseButton.SECONDARY);
        clickOn("#button10", MouseButton.SECONDARY);
        clickOn("#button01", MouseButton.SECONDARY);
        Assertions.assertEquals(
            10-2,
            Integer.valueOf(flagsLeft.getText())
        );
    }

    public void tearDown() {
        wireMockServer.stop();
    }
}
