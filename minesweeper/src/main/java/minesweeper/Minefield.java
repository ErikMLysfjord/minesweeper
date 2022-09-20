package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Minefield {
    private final List<List<Square>> minefield;    
    private final int width;
    private final int height;

    public Minefield(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalStateException("Must have dimentions larger than 0.");
        }

        minefield = new ArrayList<>();
        for (int i = 0; i < height ; i++) {
            minefield.add(new ArrayList<>());
        }

        this.width = width;
        this.height = height;
    }

    //TODO
    public Square getSquare(int x, int y) {
        return null;
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

}
