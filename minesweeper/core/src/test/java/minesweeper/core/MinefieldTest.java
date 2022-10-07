package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinefieldTest {

    private final Integer[][] illegalCoords = {
        {4,3},
        {3,4},
        {-1,3},
        {3,-1}
    };

    private Minefield minefield;

    @BeforeEach
    public void setup() {
        minefield = new Minefield(4, 4);
    }

    @Test
    public void testGetAndSetSquare() {
        Square square = new Square();
        minefield.setSquare(square, 0, 3);

        Assertions.assertEquals(square, minefield.getSquare(0, 3));
    }

    @Test
    public void testIllegalMinefield() {
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new Minefield(0, 1)  
        );
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new Minefield(1, 0)  
        );
    }

    @Test
    public void testSetIllegalSquare() {
        Square square = new Square();

        for (Integer[] coord : illegalCoords) {
            Assertions.assertThrows(
                IllegalStateException.class,
                () -> minefield.setSquare(square, coord[0], coord[1])
            );
        }
    }

    @Test
    public void testGetIllegalSquare() {
        for (Integer[] coord : illegalCoords) {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> minefield.getSquare(coord[0], coord[1])
            );
        }
        
    }

    @Test
    public void testFlag() {
        Assertions.assertFalse(minefield.isFlagged(2, 2));
        minefield.toggleFlag(2, 2);
        Assertions.assertTrue(minefield.isFlagged(2, 2));
        minefield.toggleFlag(2, 2);
        Assertions.assertFalse(minefield.isFlagged(2, 2));
    }
}
