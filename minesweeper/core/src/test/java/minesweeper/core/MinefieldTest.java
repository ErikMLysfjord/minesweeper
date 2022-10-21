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
    private int width;
    private int height;
    private Minefield minefield;

    @BeforeEach
    public void setup() {
        width = 4;
        height = 4;
        minefield = new Minefield(width, height);
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
        
        minefield.openSquare(2, 2);
        minefield.toggleFlag(2, 2);
        Assertions.assertFalse(minefield.isFlagged(2, 2));
    }

    /**
     * Test that the mines can be placed in a square,
     * and test that they can be detected.
     */
    @Test
    public void testMine() {
        minefield.placeMine(2,2);
        Assertions.assertTrue(minefield.hasMine(2, 2));
        Assertions.assertFalse(minefield.hasMine(2, 3));
    }
    
    @Test
    public void testInitializeMines() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> minefield.initializeMines(-1, 0, 0)
        );

        Assertions.assertThrows(
            IllegalStateException.class,
            () -> minefield.initializeMines(width * height - 4 + 1, 0, 0)
        );

        for (Integer[] coord : illegalCoords) {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> minefield.initializeMines(0, coord[0], coord[1])
            );
        }

        int mineCount = (width * height) - (3 * 3);
        minefield.initializeMines(mineCount, 1, 1);
        int mineCounter = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (minefield.hasMine(x, y)) {
                    mineCounter++;
                }
            }
        }
        Assertions.assertEquals(mineCount, mineCounter);
    }

    @Test
    public void testOpen() {
        Assertions.assertFalse(minefield.isSquareOpened(0, 0));
        minefield.toggleFlag(0, 0);
        minefield.openSquare(0, 0);
        Assertions.assertFalse(minefield.isSquareOpened(0, 0));

        minefield.toggleFlag(0, 0);
        minefield.openSquare(0, 0);
        Assertions.assertTrue(minefield.isSquareOpened(0, 0));
        minefield.openSquare(0, 0);
        Assertions.assertTrue(minefield.isSquareOpened(0, 0));
    }
    
}
