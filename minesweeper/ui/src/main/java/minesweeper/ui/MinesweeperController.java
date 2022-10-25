package minesweeper.ui;

import minesweeper.core.Difficulty;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.Minesweeper;
import minesweeper.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinesweeperController {
    private SceneSwitcher sceneSwitcher;
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

        setupMinesweeper();
        setupMinefieldView();
    }

    /**
     * Set up minesweeper model.
     */
    private void setupMinesweeper() {
        minesweeper = new Minesweeper(Difficulty.EASY);
        minesweeper.addOnLoss(() -> handleLoss());
        minesweeper.addOnWin(() -> handleWin());
    }

    /**
     * Set up MinefieldView.
     */
    private void setupMinefieldView() {
        minefieldView = new MinefieldView(Difficulty.EASY);
        minefieldView.bindGridPane(minefieldGridPane);
        minefieldView.setOnMouseRelease((mouseEvent) ->
            handleClickedSquare(mouseEvent)
        );
        minefieldGridPane.setGridLinesVisible(false); //necessary
        minefieldGridPane.setGridLinesVisible(true);
    };

    /**
     * Restarts the minesweeper game.
     * Called from restart button.
     */
    @FXML
    private void handleRestart() {
        setupMinesweeper();
        setupMinefieldView();
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
        minesweeper.openSquare(x, y);
        if (minesweeper.isSquareOpened(x, y) && !minesweeper.hasMine(x, y)) {
            minefieldView.setOpenedSquareImage(
                x, y,
                minesweeper.getAdjacentMines(x, y)
            );
        }
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
     * Called from minesweeper when the game is won.
     */
    private void handleWin() {

    }

    /**
     * Called from minesweeper when the game is lost.
     */
    private void handleLoss() {
        for (int y = 0; y < minesweeper.getHeight(); y++) {
            for (int x = 0; x < minesweeper.getWidth(); x++) {
                if (minesweeper.hasMine(x, y)) {
                    minefieldView.setBombImage(x, y);
                }
            }
        }
        minefieldView.showLoss();
    }

    /**
     * Sets the sceneSwitcher that will be used to switch scenes.
     * @param sceneSwitcher the sceneSwitcher
     */
    public void setSceneSwitcher(final SceneSwitcher sceneSwitcher) {
        this.sceneSwitcher = sceneSwitcher;
    }

    /**
     * Changes the scene from the minesweeper to the highscore list scene.
     * @param event mouse-click that initializes the function
     */
    @FXML
    private void showHighscores(final ActionEvent event) {
        sceneSwitcher.setHighscores(fileHandler.readHighscoreList());
    }
}
