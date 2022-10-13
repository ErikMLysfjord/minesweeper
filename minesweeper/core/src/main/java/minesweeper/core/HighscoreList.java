package minesweeper.core;

import java.util.ArrayList;
import java.util.List;

public class HighscoreList {
    
    private final List<HighscoreEntry> highscore;
    private final int maxSize;

    public HighscoreList(final int maxSize) {
        this.maxSize = maxSize;
        this.highscore = new ArrayList<>();
    }

    public void addEntry(final HighscoreEntry entry) {

    }

    public HighscoreEntry getHighscoreEntry(final int index) {
        return null;
    } 


}
