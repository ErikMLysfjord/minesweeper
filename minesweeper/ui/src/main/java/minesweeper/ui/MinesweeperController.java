package minesweeper.ui;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.Minesweeper;
import minesweeper.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MinesweeperController {

    //Minefield dimensions
    public static final int MINEFIELD_WIDTH = 9;
    public static final int MINEFIELD_HEIGHT = 9;

    private FileHandler fileHandler;
    private Minesweeper minesweeper;

    private MinefieldView minefieldView;

    @FXML
    private TextField text;
    @FXML
    private TextField score;
    @FXML
    private GridPane minefieldGridPane;

    /**
     * Initializes the minesweeper model and minefield view.
     */
    @FXML
    private void initialize() {
        fileHandler = new FileHandler();

        //Set up minesweeper model
        minesweeper = new Minesweeper(MINEFIELD_WIDTH, MINEFIELD_HEIGHT);

        //Set up minesweeper ui
        minefieldView = new MinefieldView(MINEFIELD_WIDTH, MINEFIELD_HEIGHT);
        minefieldView.addToGridPane(minefieldGridPane);
        minefieldView.setOnMouseRelease((mouseEvent) ->
            handleClickedSquare(mouseEvent)
        );
        minefieldGridPane.setGridLinesVisible(true);
    }

    /**
     * Restarts the minesweeper game.
     * Called from restart button.
     */
    @FXML
    private void handleRestart() {
        //TODO
    }

    /**
     * Called when squares are pressed.
     * @param mouseEvent the mouse event that occurred
     */
    private void handleClickedSquare(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Button button) {
            Integer x = GridPane.getColumnIndex(button);
            Integer y = GridPane.getRowIndex(button);
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                handleLeftClickedSquare(x, y);
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                handleRightClickedSquare(x, y);
            }
        }
    }

    /**
     * Handles left-clicked squares. Called when square is left-clicked.
     * @param x x-coordinates of clicked square
     * @param y y-coordinates of clicked square
     */
    private void handleLeftClickedSquare(final Integer x, final Integer y) {
        //Check square
    }

    /**
     * Toggles flag on or off in model and view.
     * Called when square is right-clicked.
     * @param x x-coordinates of clicked square
     * @param y y-coordinates of clicked square
     */
    private void handleRightClickedSquare(final Integer x, final Integer y) {
        minesweeper.toggleFlag(x, y);

        if (minesweeper.isFlagged(x, y)) {
            minefieldView.setFlagImage(x, y);
        } else {
            minefieldView.setBlankImage(x, y);
        }
    }

    /**
     * Writes the user-input highscore list in data.json.
     * Called when submit-button is pressed.
     */
    @FXML
    private void handleSubmit() {
        String name = text.getText();
        String scoreTxt = score.getText();

        fileHandler.saveScore(new HighscoreEntry(
            name,
            Integer.parseInt(scoreTxt)
        ));

    }

    /**
     * Changes the scene from the minesweeper-game to the highscore-list-page.
     * @param event mouse-click that initializes the function
     */
    @FXML
    private void showHighscores(final ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().
                getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
            "Highscores.fxml")
            );
            fxmlLoader.setController(new HighscoresController());
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage(),
                ButtonType.OK
            );
            alert.show();
        }
    }

}
