package minesweeper.core;

public class Square {
    private boolean isFlagged = false;
    private boolean hasMine = false;
    private boolean isClicked = false;

    /**
     * Toggles flag if the square isn't opened.
     */
    public void toggleFlag() {
        if (!isClicked) {
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
    public void click() {
        if (!isFlagged) {
            this.isClicked = true;
        }
    }

    /**
     * Checks whether the square is opened or not.
     * @return the isOpened-value
     */
    public boolean isClicked() {
        return isClicked;
    }
}
