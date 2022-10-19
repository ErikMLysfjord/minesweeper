package minesweeper.core;

public class Square {
    private boolean isFlagged = false;
    private boolean hasMine = false;
    private boolean isOpened = false;

    /**
     * Toggles flag if the square isn't opened.
     */
    public void toggleFlag() {
        if (!isOpened) {
            isFlagged = !isFlagged;
        }
    }

    /**
     * Checks whether the square is toggled or not.
     * @return whether the square is toggled or not
     */
    public boolean isFlagged() {
        return isFlagged;
    }
    /**
     * Make hasMine true.
     */
    public void placeMine() {
        hasMine = true;
    }

    /**
     * Returns value of hasMine.
     * @return value of hasMine
     */
    public boolean hasMine() {
        return hasMine;
    }

    /**
     * Set isOpened true if square is not flagged.
     */
    public void open() {
        if (!isFlagged) {
            this.isOpened = true;
        }
    }

    /**
     * Checks whether the square is opened or not.
     * @return the isOpened-value
     */
    public boolean isOpened() {
        return isOpened;
    }
}
