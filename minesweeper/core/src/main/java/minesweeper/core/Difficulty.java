package minesweeper.core;

/**
 * Difficulty contains the minesweeper settings for each difficulty:
 * height, width and mine count.
 */
public enum Difficulty {
    EASY(9, 9, 10, "Easy"),
    MEDIUM(16, 16, 40, "Medium"),
    HARD(30, 16, 99, "Hard");

    private final int width;
    private final int height;
    private final int mineCount;
    private final String name;

    /**
     * A constructor for difficulty.
     * @param width the width for difficulty
     * @param height the height for difficulty
     * @param mineCount the amount of mines for difficulty
     * @param name the name for difficulty
     */
    Difficulty(
        final int width,
        final int height,
        final int mineCount,
        final String name
    ) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.name = name;
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

    /**
     * Gets the name of the difficulty.
     * @return the name of the difficulty
     */
    public String getName() {
        return name;
    }

    /**
     * Gets difficulty based on name.
     * @param name name of difficulty
     * @return difficulty that corresponds with name or null
     * if none are.
     */
    public static Difficulty getDifficulty(final String name) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (name.equals(difficulty.getName())) {
                return difficulty;
            }
        }
        return null;
    }
}
