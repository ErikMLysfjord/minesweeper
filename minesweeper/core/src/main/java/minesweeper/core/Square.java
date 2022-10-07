package minesweeper.core;

public class Square {
    private boolean isFlagged = false;
    private boolean hasMine = false;

    public Square() {

    }

    public void toggleFlag() {
        isFlagged = !isFlagged;
    }

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
