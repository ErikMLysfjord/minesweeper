package minesweeper.core;

import java.util.ArrayList;
import java.util.List;

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
    public Minefield(int width, int height) throws IllegalStateException {
        if (width < 1 || height < 1) {
            throw new IllegalStateException("Must have dimensions larger than 0.");
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
    public Square getSquare(int x, int y) throws IllegalArgumentException {
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
    public void setSquare(Square square, int x, int y) throws IllegalArgumentException {
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
    private boolean isOutOfBounds(int x, int y) {
        return
            x < 0 ||
            x >= width ||
            y < 0 ||
            y >= height;
    }

    /**
     * Toggles flag at given square.
     * @param x x-coordinates of the square
     * @param y y-coordinates of the square
     */
    public void toggleFlag(int x, int y) {
        getSquare(x, y).toggleFlag();
    }

    /**
     * Checks whether the square is flagged or not.
     * @param x x-coordinates of the square
     * @param y y-coordinates of the square
     * @return whether the square is flagged or not
     */
    public boolean isFlagged(int x, int y) {
        return getSquare(x, y).isFlagged();
    }

}
