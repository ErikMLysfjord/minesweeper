package minesweeper.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import minesweeper.core.HighscoreEntry;

public class HighscoreEntryTest {
    private HighscoreEntry entry;
    private HighscoreEntry entry2;
    private String name;
    private String name2;
    private Integer score;
    private Integer score2;
    @BeforeEach
    public void setUp() {
        name = "Ola Nordmann";
        name2 = "Jakob kirkegaard";
        score = 42;
        score2 = 34;
        entry = new HighscoreEntry(name, score);
        entry2 = new HighscoreEntry(name2, score2);
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals(name, entry.getName());
        Assertions.assertEquals(name2, entry2.getName());
    }

    @Test
    public void testGetScore() {
        Assertions.assertEquals(score, entry.getScore());
        Assertions.assertEquals(score2, entry2.getScore()); 
    }
}
