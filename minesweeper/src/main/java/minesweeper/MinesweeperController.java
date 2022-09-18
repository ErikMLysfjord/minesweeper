package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MinesweeperController {

    public MinesweeperController() {

    }

    @FXML
    void handleClickedSquare(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button button) {
            Integer x = GridPane.getColumnIndex(button);
            Integer y = GridPane.getRowIndex(button);
            handleClickedSquare(x, y); 
        }
    }

    private void handleClickedSquare(Integer x, Integer y) {
        System.out.println(x);
        System.out.println(y);
    }

    @FXML
    void initialize() {

    }

}
