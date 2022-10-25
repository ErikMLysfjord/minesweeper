package minesweeper.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minesweeper.core.Difficulty;

public class MinesweeperSceneTest extends ApplicationTest {

    private Parent root;
    private Stage stage;
    private Scene initialScene;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        SceneSwitcher sceneSwitcher = new SceneSwitcher(stage);
        sceneSwitcher.setMinesweeper();
        initialScene = stage.getScene();
        root = initialScene.getRoot();
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
        Node button = getElementById("#button00");
        Assertions.assertFalse(nodeExists("#image00flag"));
        clickOn(button, MouseButton.SECONDARY);
        Assertions.assertTrue(nodeExists("#image00flag"));
        clickOn(button, MouseButton.SECONDARY);
        Assertions.assertFalse(nodeExists("#image00flag"));
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
        Node button = getElementById("#checkHighscoresButton");
        clickOn(button, MouseButton.PRIMARY);

        Scene currentScene = stage.getScene();
        Assertions.assertNotEquals(initialScene, currentScene);
    }

    @Test
    public void testRestart() {
        clickOn("#button00");
        Assertions.assertTrue(nodeExists("#image00opened0"));
        clickOn("#restart");
        Assertions.assertFalse(nodeExists("#image00opened0"));
    }

}
