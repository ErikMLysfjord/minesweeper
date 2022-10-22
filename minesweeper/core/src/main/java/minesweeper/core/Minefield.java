package minesweeper.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Minefield {
    private final List<List<Square>> minefield;
    private final int width;
    private final int height;

    /**
     * A constructor for a minefield.
     * @param width the width of the minefield
     * @param height the height of the minefield
     * @throws IllegalStateException if the dimensions are zero or less
     */
    public Minefield(final int width, final int height)
        throws IllegalStateException {

        if (width < 1 || height < 1) {
            throw new IllegalStateException(
                "Must have dimensions larger than 0.");
        }

        //Fill minefield with empty squares.
        minefield = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Square> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                row.add(new Square());
            }
            minefield.add(row);
        }

        this.width = width;
        this.height = height;
    }

    /**
     * Gets the square at the given coordinates.
     * @param x x-coordinate of square
     * @param y y-coordinate of square
     * @return the square at the given coordinates
     * @throws IllegalArgumentException if the coordinates are out of bounds
     */
    public Square getSquare(final int x, final int y)
        throws IllegalArgumentException {
        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException("Coordinates are out of bounds");
        }
        return minefield.get(y).get(x);
    }

    /**
     * Sets the square at the given coordinates.
     * @param square the square that will get assigned coordinates
     * @param x x-coordinates for the square
     * @param y y-coordinates for the square
     * @throws IllegalArgumentException if the coordinates are out of bounds
     */
    public void setSquare(final Square square, final int x, final int y)
         throws IllegalArgumentException {
        if (isOutOfBounds(x, y)) {
            throw new IllegalStateException("Coordinates are out of bounds");
        }
        minefield.get(y).set(x, square);
    }

    /**
     * Checks whether the coordinates are out of bounds.
     * Used for validating arguments.
     * @param x x-coordinates
     * @param y y-coordinates
     * @return whether the coordinates are out of bounds or not
     */
    private boolean isOutOfBounds(final int x, final int y) {
        return
            x < 0
            || x >= width
            || y < 0
            || y >= height;
    }

    /**
     * Toggles flag at given square.
     * @param x x-coordinates of the square
     * @param y y-coordinates of the square
     */
    public void toggleFlag(final int x, final int y) {
        getSquare(x, y).toggleFlag();
    }

    /**
     * Checks whether the square is flagged or not.
     * @param x x-coordinates of the square
     * @param y y-coordinates of the square
     * @return whether the square is flagged or not
     */
    public boolean isFlagged(final int x, final int y) {
        return getSquare(x, y).isFlagged();
    }

    /**
     * Places the mine at the given coordinates.
     * @param x the x coordinate where the mine is placed
     * @param y the y coordinate where the mine is placed
     */

    public void placeMine(final int x, final int y) {
        getSquare(x, y).placeMine();
    }
    /**
     * Checks for mine at the given coordinates.
     * @param x the x coordinate to check for mine
     * @param y the y coordinate to check for mine
     * @return whether or not the square has a mine
     */
    public Boolean hasMine(final int x, final int y) {
        return getSquare(x, y).hasMine();
    }

    /**
     * Puts mines into random positions in minefield.
     * Stops when the number of mines in the grid equals mineCount.
     * @param mineCount the number of mines to be placed in the grid
     * @param safeX x-coordinate of the first square opened
     * @param safeY y-coordinate of the first square opened
     */
    public void initializeMines(
        final int mineCount,
        final int safeX,
        final int safeY
    ) {
        if (mineCount < 0) {
            throw new IllegalArgumentException("mineCount can't be negative.");
        }
        if (isOutOfBounds(safeX, safeY)) {
            throw new IllegalArgumentException(
                "Coordinates are out of bounds."
            );
        }
        int availableSquares = width * height - safeSquareCount(safeX, safeY);
        if (mineCount > availableSquares) {
            throw new IllegalStateException(
                "Not enough available squares for the mines."
            );
        }

        Random rand = new Random();
        int i = 0;
        while (i < mineCount) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            if (!hasMine(x, y) && !isSafeSquare(x, y, safeX, safeY)) {
                placeMine(x, y);
                i++;
            }
        }
    }

    /**
     * The amount of safe squares from coordinate.
     * This isn't always 9, since coordinates might
     * touch the edges of the board.
     * @param safeX center x-coordinate of the safe squares
     * @param safeY center y-coordinate of the safe squares
     * @return the amount of safe squares
     */
    private int safeSquareCount(final int safeX, final int safeY) {
        int x1 = Math.max(safeX - 1, 0);
        int y1 = Math.max(safeY - 1, 0);
        int x2 = Math.min(safeX + 1, width - 1);
        int y2 = Math.min(safeY + 1, height - 1);
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }

    /**
     * Checks if a coordinate is in the safe zone, determined
     * by the center coordinate of the safe squares.
     * @param x x-coordinate of the square that is checked
     * @param y y-coordinate of the square that is checked
     * @param safeX center x-coordinate of the safe squares
     * @param safeY center y-coordinate of the safe squares
     * @return whether or not the square is safe
     */
    private boolean isSafeSquare(
        final int x,
        final int y,
        final int safeX,
        final int safeY
    ) {
        return
            x >= safeX - 1
            && x <= safeX + 1
            && y >= safeY - 1
            && y <= safeY + 1;
    }

    /**
     * Opens the selected square.
     * @param x the x-coordinate of the square clicked
     * @param y the y-coordinate of the square clicked
     */
    public void openSquare(final int x, final int y) {
        getSquare(x, y).open();
    }

    /**
     * Checks whether the selected square is opened.
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return whether the mine is opened or not
     */
    public boolean isSquareOpened(final int x, final int y) {
        return getSquare(x, y).isOpened();
    }

    /**
     * Gets width of Minefield.
     * @return width of Minefield
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height of Minefield.
     * @return height of Minefield
     */
    public int getHeight() {
        return height;
    }

}
