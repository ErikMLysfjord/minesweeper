package minesweeper;

public class Square {
    private boolean isFlagged = false;

    public Square() {
        
    }

    public void toggleFlag() {
        isFlagged = !isFlagged;
    }

    public boolean isFlagged() {
        return isFlagged;
    }
    
}
