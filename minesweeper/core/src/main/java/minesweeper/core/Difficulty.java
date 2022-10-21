package minesweeper.core;

public enum Difficulty {
    EASY(9, 9, 10),
    MEDIUM(16, 16, 40),
    HARD(30, 16, 99);

    private final int width;
    private final int height;
    private final int mineCount;

    /**
     * A helping constructor for difficulty.
     * @param width the width for difficulty
     * @param height the height for difficulty
     * @param mineCount the amount of mines for difficulty
     */
    Difficulty(final int width, final int height, final int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
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
