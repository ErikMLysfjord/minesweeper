package minesweeper.core;

/**
 * Representation of the squares or cells in a minesweeper minefield. It's a
 * dataclass containing the square's current status: Whether or not it's
 * flagged, opened or if it has a mine.
 */
public class Square {
    private boolean isFlagged = false;
    private boolean hasMine = false;
    private boolean isOpened = false;

    /**
     * Toggles flag as long as the square is unopened.
     */
    public void toggleFlag() {
        if (!isOpened) {
            isFlagged = !isFlagged;
        }
    }

    /**
     * Checks whether the square is flagged or not.
     * @return whether the square is flagged or not
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     * Places a mine in this square.
     */
    public void placeMine() {
        hasMine = true;
    }

    /**
     * Checks whether the square has a mine or not.
     * @return whether the square has a mine or not.
     */
    public boolean hasMine() {
        return hasMine;
    }

    /**
     * Opens the square unless it is flagged.
     */
    public void open() {
        if (!isFlagged) {
            this.isOpened = true;
        }
    }

    /**
     * Checks whether the square is opened or not.
     * @return whether the square is opened or not.
     */
    public boolean isOpened() {
        return isOpened;
    }
}
