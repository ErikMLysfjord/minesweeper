module minesweeper.core {
    requires transitive com.fasterxml.jackson.databind;

    exports minesweeper.core;
    exports minesweeper.json;

    opens minesweeper.core to com.fasterxml.jackson.databind;
}
