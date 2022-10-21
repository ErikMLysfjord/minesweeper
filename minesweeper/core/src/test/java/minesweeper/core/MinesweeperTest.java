package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinesweeperTest {
    private Minesweeper minesweeper;
    private boolean isWon;
    private boolean isLost;

    @BeforeEach
    public void setupMinesweeper() {
        minesweeper = new Minesweeper(4, 4);
        isWon = false;
        isLost = false;
    }

    @Test
    public void testLosing() {
        minesweeper.addOnLoss(() -> {
            isLost = true;
        });
        //Needs mines in minefield
    }

    @Test
    public void testWinning() {
        minesweeper.addOnWin(() -> {
            isWon = true;
        });
        //Needs mines in minefield
    }

    @Test
    public void testStartingSafeSquares() {
        minesweeper.addOnLoss(() -> {
            isLost = true;
        });

        Integer[][] coords = {
            {1,1}, {0,0}, {0,1},
            {0,2}, {1,0}, {1,2},
            {2,0}, {2,1}, {2,2}
        };
        
        for (Integer[] coord : coords) {
            minesweeper.openSquare(coord[0], coord[1]);
        }
        Assertions.assertFalse(isLost);
    }
}
