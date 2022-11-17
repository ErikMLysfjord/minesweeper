package minesweeper.ui;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import minesweeper.core.Difficulty;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

/**
 * Controller for the Highscores scene. It displays the provided HighscoreList.
 */
public class HighscoresController {
    private SceneManager sceneManager;
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
     * Initializes highscoresView and updates the highscore list.
     * Also adds difficulties to difficultyChoiceBox.
     */
    @FXML
    private void initialize() {
        for (Difficulty difficulty : Difficulty.values()) {
            difficultyChoiceBox.getItems().add(difficulty.getName());
        }

        highscoresView = new HighscoresView();
        highscoresView.setCells(highscores, name, score, getHighscores());
    }

    /**
     * Changes to the highscore list of another difficulty.
     * Called when new difficulty gets chosen in ChoiceBox.
     */
    @FXML
    private void changeDifficulty() throws URISyntaxException {
        HighscoresAccess access = new HighscoresAccess(new URI(uri));

        try {
            String chosenDifficulty = difficultyChoiceBox.getValue();
            sceneManager.setHighscores(
                access.getHighscoreList(chosenDifficulty)
            );
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Could not reach server.");
            alert.setContentText("Highscores are unavailable :(");
            alert.show();
        }
    }

    /**
     * Creates an observable list of the highscores.
     * @return the observable list made
     */
    private ObservableList<HighscoreEntry> getHighscores() {
        ObservableList<HighscoreEntry> list =
            FXCollections.observableArrayList();
        for (HighscoreEntry entry : highscoreList) {
            list.add(entry);
        }
        return list;
    }

    /**
     * Sets the sceneManager that will be used to switch scenes.
     * @param sceneManager the sceneManager
     */
    public void setSceneManager(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Method for returning from highscores back to the
     * minesweeper-scene.
     * @param event the event from the button press
     */
    @FXML
    private void goBack(final ActionEvent event) {
        sceneManager.setMinesweeper();
    }
}
