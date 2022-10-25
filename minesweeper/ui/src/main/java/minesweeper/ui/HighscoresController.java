package minesweeper.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;
import minesweeper.json.FileHandler;

public class HighscoresController {
    private SceneSwitcher sceneSwitcher;
    private FileHandler fileHandler;
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

    /**
     * Constructs a highscore-controller that reads highscores from file.
     */
    public HighscoresController() {
        fileHandler = new FileHandler();
        highscoreList = fileHandler.readHighscoreList();
    }

    /**
     * Constructs a highscore-controller that won't read highscores from file.
     * Copies elements from a pre-made highscorelist into a new Highscore-list,
     * so the highscores cant be mutated externally.
     * @param highscoreList a pre-made highscorelist
     */
    public HighscoresController(final HighscoreList highscoreList) {
        fileHandler = new FileHandler();

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
        highscoresView = new HighscoresView();
        highscoresView.setCells(highscores, name, score, getHighScores());
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
    public void setSceneSwitcher(final SceneSwitcher sceneSwitcher) {
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
