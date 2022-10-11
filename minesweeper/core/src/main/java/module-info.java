module minesweeper.core {
    requires json.simple;
    requires transitive com.fasterxml.jackson.databind;

    exports minesweeper.core;
    exports minesweeper.json;
}
