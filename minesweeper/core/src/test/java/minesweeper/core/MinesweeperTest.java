package minesweeper.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinesweeperTest {
    private Minesweeper minesweeper;

    @BeforeEach
    public void setupMinesweeper() {
        minesweeper = new Minesweeper(3, 3);
    }

    private boolean isLost;
    @Test
    public void testLosing() {
        minesweeper.addOnLoss(() -> {
            isLost = true;
        });
        //Needs mines in minefield
    }

    private boolean isWon;
    public void testWinning() {
        minesweeper.addOnWin(() -> {
            isWon = true;
        });
        //Needs mines in minefield
    }
}
