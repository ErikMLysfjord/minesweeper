package minesweeper.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Minefield {
    private final List<List<Square>> minefield;    
    private final int width;
    private final int height;

    public Minefield(int width, int height) {
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

    public Square getSquare(int x, int y) {
        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException("Coordinates are out of bounds");
        }
        return minefield.get(y).get(x);
    }

    public void setSquare(Square square, int x, int y) {
        if (isOutOfBounds(x, y)) {
            throw new IllegalStateException("Coordinates are out of bounds");
        }
        minefield.get(y).set(x, square);
    }

    private boolean isOutOfBounds(int x, int y) {
        return
            x < 0 ||
            x >= width ||
            y < 0 ||
            y >= height;
    }

    public void toggleFlag(int x, int y) {
        getSquare(x, y).toggleFlag();
    }

    public boolean isFlagged(int x, int y) {
        return getSquare(x, y).isFlagged();
    }

    /**
     * Places the mine at the given coordinates.
     * @param x the x coordinate where the mine is placed
     * @param y the y coordinate where the mine is placed
     */

    public void placeMine(int x, int y) {
        getSquare(x, y).placeMine();
    }
    /**
     * Checks for mine at the given coordinates.
     * @param x the x coordinate to check for mine
     * @param y the y coordinate to check for mine
     */
    public Boolean hasMine(int x, int y) {
        return getSquare(x, y).hasMine();
    }

    /**
     * Puts mines into random positions in minefield.
     * Stops when the number of mines in the grid equals mineCount.
     * @param mineCount the number of mines to be placed in the grid
     */
    public void initializeMines(int mineCount) {
        Random rand = new Random();
        int i = 0;
        while (i < mineCount){
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            if (!hasMine(x, y)){
                placeMine(x, y);
                i++;
            }
        }  
    } 

}
