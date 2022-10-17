package minesweeper.core;

public class Minesweeper {
    private final Minefield minefield;
    private boolean isLost;

    /**
     * Constructor for Minesweeper.
     * @param width the width of minefield
     * @param height the height of minefield
     */
    public Minesweeper(final int width, final int height) {
        minefield = new Minefield(width, height);
        isLost = false;
    }

    /**
     * Checks whether the game is lost or not.
     * @return whether the game is lost or not
     */
    public boolean isLost() {
        return isLost;
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
}
