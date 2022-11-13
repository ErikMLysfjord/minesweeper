package minesweeper.ui;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import minesweeper.core.Difficulty;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

public class HighscoresController {
    private SceneManager sceneSwitcher;
    private HighscoreList highscoreList;
    private HighscoresView highscoresView;

    @FXML
    private TableView<HighscoreEntry> highscores;
    @FXML
    private TableColumn<HighscoreEntry, String> name;
    @FXML
    private TableColumn<HighscoreEntry, Integer> score;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private String uri;


    /**
     * Constructor for HighscoresController.
     * Creates and sets a copy of the provided highscore list,
     * so that the object can't be mutated externally.
     * @param highscoreList the highscore list to be displayed on the scene
     */
    public HighscoresController(final HighscoreList highscoreList) {
        this.highscoreList = new HighscoreList(highscoreList.getMaxSize());
        for (HighscoreEntry entry : highscoreList) {
            this.highscoreList.addEntry(entry);
        }
    }

    /**
     * Initializes the highscoresview and updates the highscore-list.
     */
    @FXML
    private void initialize() {
        for (Difficulty difficulty : Difficulty.values()) {
            difficultyChoiceBox.getItems().add(difficulty.getName());
        }

        highscoresView = new HighscoresView();
        highscoresView.setCells(highscores, name, score, getHighScores());
    }

    /**
     * Changes highscore difficulty list.
     * Called when new difficulty gets chosen in ChoiceBox.
     */
    @FXML
    private void changeDifficulty() throws URISyntaxException {
        HighscoresAccess access = new HighscoresAccess(new URI(uri));

        String chosenDifficulty = difficultyChoiceBox.getValue();
        sceneSwitcher.setHighscores(access.getHighscoreList(chosenDifficulty));
    }

    /**
     * Creates an observable list of the highscores.
     * @return the observable list made
     */
    private ObservableList<HighscoreEntry> getHighScores() {
        ObservableList<HighscoreEntry> list =
            FXCollections.observableArrayList();
        for (HighscoreEntry entry : highscoreList) {
            list.add(entry);
        }
        return list;
    }

    /**
     * Sets the sceneSwitcher that will be used to switch scenes.
     * @param sceneSwitcher the sceneSwitcher
     */
    public void setSceneSwitcher(final SceneManager sceneSwitcher) {
        this.sceneSwitcher = sceneSwitcher;
    }

    /**
     * Method for returning from the highscore-page back to the
     * minesweeper-game.
     * @param event
     */
    @FXML
    private void goBack(final ActionEvent event) {
        sceneSwitcher.setMinesweeper();
    }
}
