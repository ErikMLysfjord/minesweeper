package minesweeper.core;

public class Difficulty {
    private int width;
    private int height;
    private int mineCount;

    private static final int EASY_WIDTH_VALUE = 9;
    private static final int EASY_HEIGHT_VALUE = 9;
    private static final int EASY_MINECOUNT_VALUE = 10;

    private static final int MEDIUM_WIDTH_VALUE = 16;
    private static final int MEDIUM_HEIGHT_VALUE = 16;
    private static final int MEDIUM_MINECOUNT_VALUE = 40;

    private static final int HARD_WIDTH_VALUE = 30;
    private static final int HARD_HEIGHT_VALUE = 16;
    private static final int HARD_MINECOUNT_VALUE = 99;

    /**
     * A helping constructor for difficulty.
     * @param width the width for difficulty
     * @param height the height for difficulty
     * @param mineCount the amount of mines for difficulty
     */
    public Difficulty(final int width, final int height, final int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
    }

    /**
     * A constructor for difficulty.
     * @param difficulty easy, medium or hard difficulty as a string
     * @throws IllegalArgumentException if string is not easy, medium or hard

     */
    public Difficulty(final String difficulty) {
        if (difficulty.equals("easy")) {
            setEasy();
        } else if (difficulty.equals("medium")) {
            setMedium();
        } else if (difficulty.equals("hard")) {
            setHard();
        } else {
            throw new IllegalArgumentException(
                "Difficulty must be easy, medium or hard"
            );
        }
    }

    /**
     * Sets difficulty's parameters to static easy values.
     */
    private void setEasy() {
        this.width = EASY_WIDTH_VALUE;
        this.height = EASY_HEIGHT_VALUE;
        this.mineCount = EASY_MINECOUNT_VALUE;
    }

    /**
     * Sets difficulty's parameters to static medium values.
     */
    private void setMedium() {
        this.width = MEDIUM_WIDTH_VALUE;
        this.height = MEDIUM_HEIGHT_VALUE;
        this.mineCount = MEDIUM_MINECOUNT_VALUE;
    }

    /**
     * Sets difficulty's parameters to static hard values.
     */
    private void setHard() {
        this.width = HARD_WIDTH_VALUE;
        this.height = HARD_HEIGHT_VALUE;
        this.mineCount = HARD_MINECOUNT_VALUE;
    }

    /**
     * Gets the width of the difficulty.
     * @return the width of the difficulty
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the difficulty.
     * @return the height of the difficulty
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the amount of mines of the difficulty.
     * @return the amount of mines of the difficulty
     */
    public int getMineCount() {
        return mineCount;
    }
}
