package minesweeper.ui;

import minesweeper.core.Difficulty;
import minesweeper.core.HighscoreEntry;
import minesweeper.core.Minesweeper;
import minesweeper.json.FileHandler;

import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MinesweeperController {
    private FileHandler fileHandler;
    private Minesweeper minesweeper;

    private MinefieldView minefieldView;

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
            minefieldView.setOpenedSquareImage(x, y, 0);
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
        Optional<String> name = td.showAndWait();
        while (name.isPresent() && name.get().length() < 2) {
            td.setContentText(
                "Input your name. \nYour name must be longer than 2 characters."
            );
            td.setHeaderText("Save score");
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
            Alert alert = new Alert(
                AlertType.ERROR, e.getMessage(), ButtonType.OK
            );
            alert.show();
        }
    }
}
