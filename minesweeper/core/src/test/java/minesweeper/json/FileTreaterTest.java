package minesweeper.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import minesweeper.core.HighscoreEntry;

public class FileTreaterTest {

    private List<HighscoreEntry> entries;
    /* private String[] names;
    private Integer[] scores; */
    private String rPath;
    private Random rand;
    private Integer scoreRoof;
    
    @BeforeEach
    public void setUp() {
        scoreRoof = 1000;
        rPath = "../core/src/main/resources/minesweeper/json/data.json";
        String[] names = {"Ole", "Dole", "Toern", "Erik", "Vebjørn","David", "Marie", "Sofie", "Mathilde", "Andreas"};
        
        for (String name : names) {
            HighscoreEntry entry = new HighscoreEntry(name, rand.nextInt(scoreRoof));
            entries.add(entry);
        }
        
    }

    @Test
    public void testSaveScore(){
        Assertions.assertEquals(rPath, FileTreater.getPath());

        for (HighscoreEntry entry: entries) {
            FileTreater.saveScore(entry);
        }

        
    }


}
