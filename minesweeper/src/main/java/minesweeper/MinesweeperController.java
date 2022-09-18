package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinesweeperController {

    public MinesweeperController() {

    }

    @FXML
    void initialize() {

    }

    @FXML
    void handleClickedSquare(MouseEvent mouseEvent) {
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

    private void handleLeftClickedSquare(Integer x, Integer y) {
        //Check square
    }

    private void handleRightClickedSquare(Integer x, Integer y) {
        //Put flag on square
    }

}
