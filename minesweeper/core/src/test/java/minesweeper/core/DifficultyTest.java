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
        Assertions.assertEquals(0, difficulty.getWidth());
        difficulty.easy();
        Assertions.assertEquals(9, difficulty.getWidth());
        Assertions.assertEquals(9, difficulty.getHeight());
        Assertions.assertEquals(10, difficulty.getMinesAmount());
    }

    @Test
    public void testMedium() {
        Assertions.assertEquals(0, difficulty.getHeight());
        difficulty.medium();
        Assertions.assertEquals(16, difficulty.getWidth());
        Assertions.assertEquals(16, difficulty.getHeight());
        Assertions.assertEquals(40, difficulty.getMinesAmount());
    }

    @Test
    public void testHard() {
        Assertions.assertEquals(0, difficulty.getMinesAmount());
        difficulty.hard();
        Assertions.assertEquals(30, difficulty.getWidth());
        Assertions.assertEquals(16, difficulty.getHeight());
        Assertions.assertEquals(99, difficulty.getMinesAmount());
    }

}
