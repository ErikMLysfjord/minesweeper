package minesweeper.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;
import minesweeper.json.FileHandler;

public class HighscoresController {
    private FileHandler fileHandler;
    private HighscoreList highscoreList;
    private HighscoresView highscoresView;

    @FXML
    private TableView<HighscoreEntry> highscoresList;
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
        highscoresView.setCells(highscoresList, name, score, getHighScores());
    }

    /**
     * Creates an observable list of the highscores.
     * @return the observable list made
     */
    private ObservableList<HighscoreEntry> getHighScores() {
        ObservableList<HighscoreEntry> highscores =
            FXCollections.observableArrayList();
        for (HighscoreEntry entry : highscoreList) {
            highscores.add(entry);
        }
        return highscores;
    }

    /**
     * Method for returning from the highscore-page back to the
     * minesweeper-game.
     * @param event
     */
    @FXML
    private void goBack(final ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().
                getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(
                "Minesweeper.fxml"
            ))));
            stage.getScene().getStylesheets().add(getClass().getResource(
                "style.css").
                toExternalForm()
            );
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage(),
            ButtonType.OK);
            alert.show();
        }
    }
}
