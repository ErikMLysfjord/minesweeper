package minesweeper.core;

public class Difficulty {
    private int width;
    private int height;
    private int minesAmount;

    /**
     * A helping constructor for difficulty
     * @param width the width for difficulty
     * @param height the height for difficulty
     * @param minesAmount the amount of mines for difficulty
     */
    public Difficulty(int width, int height, int minesAmount) {
        this.width = width;
        this.height = height;
        this.minesAmount = minesAmount;
    }

    /**
     * A constructor for difficulty
     * @param difficulty easy, medium or hard difficulty as a string
     * @throws IllegalArgumentException if difficulty is not easy, medium or hard

     */
    public Difficulty(String difficulty) {
        if (difficulty == "easy") {
            setEasy();
        }
        else if (difficulty == "medium") {
            setMedium();
        }
        else if (difficulty == "hard") {
            setHard();
        }
        else {
            throw new IllegalArgumentException("Difficulty must be easy, medium or hard");
        }
    }

    /**
     * Sets difficulty's parameters to static easy values
     */
    private void setEasy() {
        this.width = 9;
        this.height = 9;
        this.minesAmount = 10;
    }

    /**
     * Sets difficulty's parameters to static medium values
     */
    private void setMedium() {
        this.width = 16;
        this.height = 16;
        this.minesAmount = 40;
    }

    /**
     * Sets difficulty's parameters to static hard values
     */
    private void setHard() {
        this.width = 30;
        this.height = 16;
        this.minesAmount = 99;
    }

    /**
     * Gets the width of the difficulty
     * @return the width of the difficulty
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the difficulty
     * @return the height of the difficulty
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the amount of mines of the difficulty
     * @return the amount of mines of the difficulty
     */
    public int getMinesAmount() {
        return minesAmount;
    }
}
