package minesweeper.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import minesweeper.core.HighscoreEntry;

public class FileTreaterTest {

    private List<HighscoreEntry> entries = new ArrayList<HighscoreEntry>();
    /* private String[] names;
    private Integer[] scores; */
    private String rPath;
    private Random rand = new Random();
    private Integer scoreRoof;
    private List<Integer> scores;
    
    @BeforeEach
    public void setUp() {
        scoreRoof = 1000;
        rPath = "../core/src/main/resources/minesweeper/json/data.json";
        String[] names = {"Ole", "Dole", "Toern", "Erik", "Vebjørn","David", "Marie", "Sofie", "Mathilde", "Andreas"};
        Integer i;
        for (String name : names) {
            i = rand.nextInt(scoreRoof);
            HighscoreEntry entry = new HighscoreEntry(name, i);
            entries.add(entry);
            scores.add(i);
        }
        
    }

    @Test
    public void testSaveScore(){
        Assertions.assertEquals(rPath, FileTreater.getPath());

        Assertions.assertEquals(FileTreater.getScore(entries.get(0).getName()), scores.get(0));
    }


}
