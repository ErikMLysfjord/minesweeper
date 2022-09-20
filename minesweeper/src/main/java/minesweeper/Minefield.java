package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Minefield {
    private final List<List<Square>> minefield;    

    public Minefield(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalStateException("Must have dimentions larger than 0.");
        }

        minefield = new ArrayList<>();
        for (int i = 0; i < height ; i++) {
            minefield.add(new ArrayList<>());
        }
    }

    //TODO
    public Square getSquare(int x, int y) {
        return null;
    }

    //TODO
    public void setSquare(Square square, int x, int y) {
        
    }

    private boolean isOutOfBounds(int x, int y) {
        return false; //TODO
    }

}
