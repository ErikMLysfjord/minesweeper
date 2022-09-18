package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MinesweeperController {

    public MinesweeperController() {

    }

    @FXML
    void handleClickedSquare(ActionEvent actionEvent) {
        System.out.println("click");
    }

    @FXML
    void initialize() {

    }

}
