package minesweeper;

import javafx.fxml.FXML;

public class MinesweeperController {

    private Minesweeper calc;

    public MinesweeperController() {
        calc = new Minesweeper(0.0, 0.0, 0.0);
    }

    @FXML
    void initialize() {
    }

}
