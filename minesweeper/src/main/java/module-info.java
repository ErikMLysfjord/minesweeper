module minesweeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens minesweeper to javafx.graphics, javafx.fxml, json.simple;
}
