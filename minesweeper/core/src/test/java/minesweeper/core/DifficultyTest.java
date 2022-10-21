package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifficultyTest {

    @Test
    public void testEasy() {
        Assertions.assertEquals(9, Difficulty.EASY.getWidth());
        Assertions.assertEquals(9, Difficulty.EASY.getHeight());
        Assertions.assertEquals(10, Difficulty.EASY.getMineCount());
    }

    @Test
    public void testMedium() {
        Assertions.assertEquals(16, Difficulty.MEDIUM.getWidth());
        Assertions.assertEquals(16, Difficulty.MEDIUM.getHeight());
        Assertions.assertEquals(40, Difficulty.MEDIUM.getMineCount());
    }

    @Test
    public void testHard() {
        Assertions.assertEquals(30, Difficulty.HARD.getWidth());
        Assertions.assertEquals(16, Difficulty.HARD.getHeight());
        Assertions.assertEquals(99, Difficulty.HARD.getMineCount());
    }
}
