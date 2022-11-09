module minesweeper.ui {
    requires minesweeper.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens minesweeper.ui to javafx.graphics, javafx.fxml;
}
