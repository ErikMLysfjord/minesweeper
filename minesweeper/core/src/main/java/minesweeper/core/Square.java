package minesweeper.core;

public class Square {
    private boolean isFlagged = false;

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
    
}
