package minesweeper.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
            "Highscores.fxml")
        );
        fxmlLoader.setController(new HighscoresController(highscoreList));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        this.initialScene = scene;
        stage.setScene(scene);
        stage.show();
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
            getElementById("#highscoresList");

        return (TableColumn<HighscoreEntry, String>)list.getColumns().get(0);
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
            getElementById("#highscoresList");

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
    public void testGoBack() throws IOException {
        Node button = getElementById("#backButton");
        clickOn(button, MouseButton.PRIMARY);
        Scene currentScene = stage.getScene();

        Assertions.assertNotEquals(initialScene, currentScene);
    }
}
