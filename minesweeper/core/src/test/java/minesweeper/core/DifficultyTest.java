package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DifficultyTest {
    private Difficulty difficulty;

    @BeforeEach
    public void setup() {
        difficulty = new Difficulty(0, 0, 0);
    }

    @Test
    public void testEasy() {
        difficulty.easy();
        Assertions.assertEquals(9, difficulty.getWidth());
        Assertions.assertEquals(9, difficulty.getHeight());
        Assertions.assertEquals(10, difficulty.getMinesAmount());
    }

    @Test
    public void testMedium() {
        difficulty.medium();
        Assertions.assertEquals(16, difficulty.getWidth());
        Assertions.assertEquals(16, difficulty.getHeight());
        Assertions.assertEquals(40, difficulty.getMinesAmount());
    }

    @Test
    public void testHard() {
        difficulty.easy();
        Assertions.assertEquals(30, difficulty.getWidth());
        Assertions.assertEquals(16, difficulty.getHeight());
        Assertions.assertEquals(99, difficulty.getMinesAmount());
    }

}
