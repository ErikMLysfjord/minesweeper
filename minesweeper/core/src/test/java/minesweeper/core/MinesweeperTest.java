package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinesweeperTest {
    private Minesweeper minesweeper;
    private boolean isWon;
    private boolean isLost;
    private Integer[][] safeCoords = {
        {1,1}, {0,0}, {0,1},
        {0,2}, {1,0}, {1,2},
        {2,0}, {2,1}, {2,2}
    };

    @BeforeEach
    public void setupMinesweeper() {
        //All squares on fourth row and column should have mines.
        minesweeper = new Minesweeper(4, 4, 4 * 4 - 3 * 3);
        isWon = false;
        isLost = false;
    }

    @Test
    public void testLosing() {
        minesweeper.addOnLoss(() -> {
            isLost = true;
        });
        minesweeper.openSquare(1, 1);
        //Guaranteed mine
        minesweeper.openSquare(3, 3);

        Assertions.assertTrue(isLost);
    }

    @Test
    public void testWinning() {
        minesweeper.addOnWin(() -> {
            isWon = true;
        });
        //Open all safeCoords
    }

    @Test
    public void testStartingSafeSquares() {
        minesweeper.addOnLoss(() -> {
            isLost = true;
        });

        
        for (Integer[] coord : safeCoords) {
            minesweeper.openSquare(coord[0], coord[1]);
        }
        Assertions.assertFalse(isLost);
    }

    @Test
    public void testAdjacentMines() {
        /*
            [0,0,2,m]
            [0,0,3,m]
            [2,3,5,m]
            [m,m,m,m]
         */
        minesweeper.openSquare(1, 1);
        Assertions.assertEquals(0, minesweeper.getAdjacentMines(1, 1));
        minesweeper.openSquare(0, 2);
        Assertions.assertEquals(2, minesweeper.getAdjacentMines(0, 2));
        minesweeper.openSquare(1, 2);
        Assertions.assertEquals(3, minesweeper.getAdjacentMines(1, 2));
        minesweeper.openSquare(2, 2);
        Assertions.assertEquals(5, minesweeper.getAdjacentMines(2, 2));
    }

    @Test
    public void testSafeSquaresAround() {
        minesweeper.openSquare(1, 1);
        minesweeper.openSquare(2, 0);
        minesweeper.toggleFlag(3, 0);
        /*
            [ , ,2,f]
            [ ,0, , ]
            [ , , , ]
            [ , , , ]
         */
        Integer[][] empty = {};
        Assertions.assertArrayEquals(empty, minesweeper.safeSquaresAround(2, 0));
        minesweeper.toggleFlag(1, 0);
        Integer[][] expected = {{2, 1}, {3, 1}};
        Assertions.assertArrayEquals(expected, minesweeper.safeSquaresAround(2, 0));
    }
}
