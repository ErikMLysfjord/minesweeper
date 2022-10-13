package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HighscoreListTest {
    private HighscoreList highscoreList;   

    @BeforeEach
    public void setup() {
        highscoreList = new HighscoreList(3);
    }

    @Test
    public void testAddEntry() {
        HighscoreEntry li = new HighscoreEntry("Li", 1);
        highscoreList.addEntry(li);

        Assertions.assertEquals(li, highscoreList.getHighscoreEntry(0));

        HighscoreEntry erik = new HighscoreEntry("Erik", 1000);
        highscoreList.addEntry(erik);

        Assertions.assertEquals(erik, highscoreList.getHighscoreEntry(0));
        Assertions.assertEquals(li, highscoreList.getHighscoreEntry(1));

        HighscoreEntry ole = new HighscoreEntry("Ole", 500);
        HighscoreEntry vebjorn = new HighscoreEntry("Vebjørn", 200);

        Assertions.assertEquals(erik, highscoreList.getHighscoreEntry(0));
        Assertions.assertEquals(ole, highscoreList.getHighscoreEntry(1));
        Assertions.assertEquals(vebjorn, highscoreList.getHighscoreEntry(2));

        Assertions.assertThrows(IllegalArgumentException.class, () ->
            highscoreList.getHighscoreEntry(3)
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
            highscoreList.getHighscoreEntry(-1)
        );

    }
}
