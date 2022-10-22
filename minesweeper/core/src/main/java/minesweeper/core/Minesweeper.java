package minesweeper.core;

public class Minesweeper {
    private final Minefield minefield;
    private boolean isLost;
    private boolean isWon;


    /**
     * Constructor for Minesweeper.
     * @param width the width of minefield
     * @param height the height of minefield
     */
    public Minesweeper(final int width, final int height) {
        minefield = new Minefield(width, height);
        isLost = false;
        isWon = false;
    }
    /**
     * Constructor for Minesweeper.
     * @param difficulty the difficulty of minesweeper
     */
    public Minesweeper(final Difficulty difficulty) {
        this(difficulty.getWidth(), difficulty.getHeight());
    }

    /**
     * Checks whether the game is lost or not.
     * @return whether the game is lost or not
     */
    public boolean isLost() {
        return isLost;
    }

    /**
     * Checks whether the game is won or not.
     * @return whether the game is won or not
     */
    public boolean isWon() {
        return isWon;
    }

    /**
     * Toggles flag at given square in minefield.
     * @param x x-coordinates of the square
     * @param y y-coordinates of the square
     */
    public void toggleFlag(final int x, final int y) {
        minefield.toggleFlag(x, y);
    }

    /**
     * Checks whether the square is flagged or not in minefield.
     * @param x x-coordinates of the square
     * @param y y-coordinates of the square
     * @return whether the square is flagged or not
     */
    public boolean isFlagged(final int x, final int y) {
        return minefield.isFlagged(x, y);
    }

    /**
     * Opens the square in the minefield.
     * @param x x-coordinate of the square
     * @param y y-coordinate of the square
     */
    public void openSquare(final int x, final int y) {
        minefield.openSquare(x, y);
    }

    /**
     * Checks whether the square is opened or not.
     * @param x x-coordinate of the square
     * @param y y-coordinate of the square
     * @return whether the square is opened or not
     */
    public boolean isSquareOpened(final int x, final int y) {
        return minefield.isSquareOpened(x, y);
    }
}
