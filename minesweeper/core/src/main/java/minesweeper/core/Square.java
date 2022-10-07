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
    public void placeMine() {
        hasMine = true;
    }

    public boolean hasMine() {
        return hasMine;
    }

    
}
