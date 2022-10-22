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
}
