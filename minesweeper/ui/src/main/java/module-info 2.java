module minesweeper.ui {
    requires minesweeper.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens minesweeper.ui to javafx.graphics, javafx.fxml;
}
