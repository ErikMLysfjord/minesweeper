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
        this.width = 9;
        this.height = 9;
        this.minesAmount = 10;
    }

    public void medium() {
        this.width = 16;
        this.height = 16;
        this.minesAmount = 40;
    }

    public void hard() {
        this.width = 30;
        this.height = 16;
        this.minesAmount = 99;
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
