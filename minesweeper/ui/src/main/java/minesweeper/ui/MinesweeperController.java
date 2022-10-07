package minesweeper.ui;

import minesweeper.core.Minefield;
import minesweeper.json.FileTreater;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinesweeperController {

    private Minefield minefield;
    private MinefieldView minefieldView;

    @FXML
    private TextField text;
    @FXML
    private TextField score;
    @FXML
    private GridPane minefieldGridPane;

    /**
     * Initializes the minefield view and model
     */
    @FXML
    private void initialize() {
        int width = 9;
        int height = 9;

        //Set up minefield model
        minefield = new Minefield(width, height);
        
        //Set up minefield ui
        minefieldView = new MinefieldView(width, height, minefieldGridPane);
        minefieldView.setOnMouseRelease((mouseEvent) -> handleClickedSquare(mouseEvent));
    }

    /**
     * Restarts the minefield
     * Called from restart button
     */
    @FXML
    private void handleRestart() {
    }

    /**
     * Called when squares are pressed
     * @param mouseEvent the mouse event that occurred
     */
    private void handleClickedSquare(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Button button) {
            Integer x = GridPane.getColumnIndex(button);
            Integer y = GridPane.getRowIndex(button);
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                handleLeftClickedSquare(x, y);
            }
            else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                handleRightClickedSquare(x, y);
            }
        }
    }

    /**
     * Handles left-clicked squares. Called when square is left-clicked
     * @param x x-coordinates of clicked square
     * @param y y-coordinates of clicked square
     */
    private void handleLeftClickedSquare(Integer x, Integer y) {
        //Check square
    }

    /**
     * Toggles flag on or off in model and view.
     * Called when square is right-clicked.
     * @param x x-coordinates of clicked square
     * @param y y-coordinates of clicked square
     */
    private void handleRightClickedSquare(Integer x, Integer y) {
        minefield.toggleFlag(x, y);

        if (minefield.isFlagged(x, y)) {
            minefieldView.setFlagImage(x, y);
        }
        else {
            minefieldView.setBlankImage(x, y);
        }
    }


    /**
     * Writes the user-input to data.json
     * Called when submit-button is pressed
     */
    @FXML
    private void handleSubmit() {
        String str = text.getText();
        String num = score.getText();
        new FileTreater().writeToFile(str, num);
    }
}
