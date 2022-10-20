package minesweeper.core;

public class Difficulty {
    private int width;
    private int height;
    private int minesAmount;

    public Difficulty(int width, int height, int minesAmount) {
        this.width = width;
        this.height = height;
        this.minesAmount = minesAmount;
    }

    public void easy() {
        //TODO
    }

    public void medium() {
        //TODO
    }

    public void hard() {
        //TODO
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinesAmount() {
        return minesAmount;
    }
}
