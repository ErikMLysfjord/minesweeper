module minesweeper.core {
    requires transitive com.fasterxml.jackson.databind;

    exports minesweeper.core;
    exports minesweeper.json;
    exports minesweeper.json.internal;

    opens minesweeper.core to com.fasterxml.jackson.databind;
}
