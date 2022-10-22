package minesweeper.core;

import java.util.ArrayList;
import java.util.List;

public class Minesweeper {
    private final Minefield minefield;
    private List<Action> onWinActions;
    private List<Action> onLossActions;
    private boolean gameIsStarted;
    private final int mineCount;

    /**
     * Constructor for Minesweeper.
     * @param width the width of minefield
     * @param height the height of minefield
     * @param mineCount the amount of mines to be added to the minefield
     */
    public Minesweeper(
        final int width,
        final int height,
        final int mineCount
    ) {
        minefield = new Minefield(width, height);
        onWinActions = new ArrayList<>();
        onLossActions = new ArrayList<>();
        gameIsStarted = false;
        this.mineCount = mineCount;
    }
    /**
     * Constructor for Minesweeper.
     * @param difficulty the difficulty of minesweeper
     */
    public Minesweeper(final Difficulty difficulty) {
        this(
            difficulty.getWidth(),
            difficulty.getHeight(),
            difficulty.getMineCount()
        );
    }

    /**
     * Checks whether the square has a mine or not.
     * @param x x-coordinate of the square
     * @param y y-coordinate of the square
     * @return wether or not the square has a mine
     */
    public Boolean hasMine(final int x, final int y) {
        return minefield.hasMine(x, y);
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
     * Starts the game on first open.
     * Loses the game if it had a mine.
     * @param x x-coordinate of the square
     * @param y y-coordinate of the square
     */
    public void openSquare(final int x, final int y) {
        if (!gameIsStarted) {
            gameIsStarted = true;
            minefield.initializeMines(mineCount, x, y);
        }

        minefield.openSquare(x, y);
        if (minefield.isSquareOpened(x, y) && minefield.hasMine(x, y)) {
            lose();
        }
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

    /**
     * Add an action to the list of actions to take
     * when minesweeper game is won.
     * @param action to be taken on win
     */
    public void addOnWin(final Action action) {
        onWinActions.add(action);
    }

    /**
     * Add an action to the list of actions to take
     * when minesweeper game is lost.
     * The game is lost when a mine is opened by the openSquare method.
     * @param action to be taken on loss
     */
    public void addOnLoss(final Action action) {
        onLossActions.add(action);
    }

    /**
     * Takes all actions that have been registered
     * to be taken when the game is lost.
     */
    private void lose() {
        for (Action action : onLossActions) {
            action.run();
        }
    }

    /**
     * Takes all action that have been registered
     * to be take when the game is won.
     */
    private void win() {
        for (Action action : onWinActions) {
            action.run();
        }
    }

    /**
     * Gets width of minefield.
     * @return width of minefield
     */
    public int getWidth() {
        return minefield.getWidth();
    }

    /**
     * Gets height of minefield.
     * @return height of minefield
     */
    public int getHeight() {
        return minefield.getHeight();
    }

    /**
     * Gets the amount of mines adjacent
     * to the square on the coordinate.
     * Including diagonal squares.
     * @param x x-coordinate of square
     * @param y y-coordinate of square
     * @return amount of adjacent mines
     */
    public int getAdjacentMines(final int x, final int y) {
        return 0;
    }

}
