package minesweeper.core;

public class Square {
    private boolean isFlagged = false;
    private boolean hasMine = false;

    public Square() {

    }

    /**
     * Toggles flag.
     */
    public void toggleFlag() {
        isFlagged = !isFlagged;
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

    
}
