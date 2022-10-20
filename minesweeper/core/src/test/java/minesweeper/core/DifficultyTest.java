package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifficultyTest {
    private Difficulty difficulty;

    @Test
    public void testSetEasy() {
        difficulty = new Difficulty("easy");
        Assertions.assertEquals(9, difficulty.getWidth());
        Assertions.assertEquals(9, difficulty.getHeight());
        Assertions.assertEquals(10, difficulty.getMineCount());
    }

    @Test
    public void testSetMedium() {
        difficulty = new Difficulty("medium");
        Assertions.assertEquals(16, difficulty.getWidth());
        Assertions.assertEquals(16, difficulty.getHeight());
        Assertions.assertEquals(40, difficulty.getMineCount());
    }

    @Test
    public void testSetHard() {
        difficulty = new Difficulty("hard");
        Assertions.assertEquals(30, difficulty.getWidth());
        Assertions.assertEquals(16, difficulty.getHeight());
        Assertions.assertEquals(99, difficulty.getMineCount());
    }

    @Test
    public void testIllegalDifficulty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Difficulty("expert"));
    }

}
