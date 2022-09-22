package minesweeper;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;

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

    //Also adds flag images from each Square to the GridPane.
    public Minefield(int width, int height, GridPane minefieldGridPane) {
        this(width, height);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                minefieldGridPane.add(
                    getSquare(x, y).getFlagImage(),
                    x, y
                );
            }
        }
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

}
