package minesweeper.ui;

import minesweeper.core.Difficulty;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.Minesweeper;
import minesweeper.json.FileHandler;

import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinesweeperController {
    private SceneManager sceneSwitcher;
    private FileHandler fileHandler;
    private Minesweeper minesweeper;
    private Difficulty currentDifficulty = Difficulty.EASY;

    private MinefieldView minefieldView;

    @FXML
    private GridPane minefieldGridPane;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;

    /**
     * Initializes the minesweeper model and minefield view.
     */
    @FXML
    private void initialize() {
        fileHandler = new FileHandler();

        setupMinesweeper();
        setupMinefieldView();
        for (Difficulty difficulty : Difficulty.values()) {
            difficultyChoiceBox.getItems().add(difficulty.getName());
        }
    }

    /**
     * Changes currentDifficulty and updates Minefieldview
     * and minesweeper model.
     */
    @FXML
    private void handleDifficultyChoiceBox() {
        String chosenDifficulty = difficultyChoiceBox.getValue();
        currentDifficulty = Difficulty.getDifficulty(chosenDifficulty);

        setupMinefieldView();
        setupMinesweeper();
    }

    /**
     * Set up minesweeper model.
     */
    private void setupMinesweeper() {
        minesweeper = new Minesweeper(currentDifficulty);
        minesweeper.addOnLoss(() -> handleLoss());
        minesweeper.addOnWin(() -> handleWin());
    }

    /**
     * Set up MinefieldView.
     */
    private void setupMinefieldView() {
        minefieldView = new MinefieldView(currentDifficulty);
        minefieldView.bindGridPane(minefieldGridPane);
        minefieldView.setOnMouseRelease((mouseEvent) ->
            handleClickedSquare(mouseEvent)
        );
        minefieldGridPane.setGridLinesVisible(false); //necessary
        minefieldGridPane.setGridLinesVisible(true);
    }

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
        openSquare(x, y);
    }

    /**
     * Opens square in model, and updates view accordingly.
     * @param x x-coordinates of square
     * @param y y-coordinates of square
     */
    private void openSquare(final Integer x, final Integer y) {
        minesweeper.openSquare(x, y);
        if (minesweeper.squareIsOpened(x, y) && !minesweeper.hasMine(x, y)) {
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
        toggleFlag(x, y);
    }

    /**
     * "Spacebaring" a square either toggles flag on unopened squares,
     * or it opens each square not flagged around an opened square, as
     * long as the number on the square is satisfied by the flags.
     * @param x x-coordinates of "spacebared" square
     * @param y y-coordinates of "spacebared" square
     */
    private void handleSpacebarOnSquare(final Integer x, final Integer y) {
        if (!minesweeper.squareIsOpened(x, y)) {
            toggleFlag(x, y);
            return;
        }

        Integer[][] safeSquares = minesweeper.safeSquaresAround(x, y);
        for (Integer[] coords : safeSquares) {
            openSquare(coords[0], coords[1]);
        }
    }

    /**
     * Handles toggling flag on square at (x, y).
     * @param x x-coordinates of square
     * @param y y-coordinates of square
     */
    private void toggleFlag(final Integer x, final Integer y) {
        minesweeper.toggleFlag(x, y);

        if (minesweeper.isFlagged(x, y)) {
            minefieldView.setFlagImage(x, y);
        } else {
            minefieldView.setBlankImage(x, y);
        }
    }

    /**
     * Writes the user-input highscore list in data.json.
     * Called when save-button is pressed.
     * @param name the name of the user
     * @param score the score of the user
     */
    private void handleSaveScore(final String name, final Integer score) {
        fileHandler.saveScore(new HighscoreEntry(
            name,
            score
        ));

    }

    /**
     * Called from minesweeper when the game is won.
     */
    private void handleWin() {
        Alert winAlert = new Alert(AlertType.INFORMATION);
        winAlert.setContentText(
            "You did it, congrats! Do you wish to save your score?"
        );
        winAlert.setTitle("Well done!");
        winAlert.setHeaderText("You won!");
        winAlert.setGraphic(new ImageView(getClass().
            getResource("happy-face.png").toString())
        );
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        winAlert.getButtonTypes().setAll(no, yes);

        Optional<ButtonType> answer = winAlert.showAndWait();
        if (answer.get() == yes) {
            handleInput();
        }
    }

    /**
     * Method for handling the name-input when a player has won.
     * The method sends in a random score, as we haven't
     * implemented a scoring-system yet.
     */
    private void handleInput() {
        TextInputDialog td = new TextInputDialog("Ola Nordmann");
        td.setContentText("Input your name");
        td.setHeaderText("Save score");
        td.setTitle("Save score");
        td.setGraphic(new ImageView(getClass().
            getResource("happy-face.png").toString())
        );
        Optional<String> name = td.showAndWait();
        while (name.isPresent() && name.get().length() < 2) {
            td.setContentText(
                "Your name must be longer than 2 characters."
            );
            name = td.showAndWait();
        }
        if (name.isPresent()) {
            handleSaveScore(name.get(), new Random().nextInt());
        }
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
    public void setSceneSwitcher(final SceneManager sceneSwitcher) {
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

    /**
     * Called when a key is released.
     * Calls handleSpacebarOnSquare(x, y) when the spacebar is released.
     * @param event the KeyEvent that triggered this method
     */
    public void onKeyReleased(final KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            Integer[] coords = minefieldView.hoveredSquare();
            if (coords != null) {
                handleSpacebarOnSquare(coords[0], coords[1]);
            }
        }
    }

}
