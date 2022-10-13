package minesweeper.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreList {

    private final List<HighscoreEntry> highscore;
    private final int maxSize;

    /**
     * Contructor for HighscoreList.
     * @param maxSize the amount of entries in highscore list
     */
    public HighscoreList(final int maxSize) {
        this.maxSize = maxSize;
        this.highscore = new ArrayList<>();
    }

    /**
     * Adds entry to its correct spot in the highscore list.
     * Keeps the list sorted from highest to lowest, and keeps it <= maxSize.
     * @param entry the entry to try to add to the highscore list
     */
    public void addEntry(final HighscoreEntry entry) {
        highscore.add(entry);
        Collections.sort(highscore, Collections.reverseOrder());
        if (highscore.size() > maxSize) {
            highscore.remove(maxSize);
        }
    }

    /**
     * Getter for highscore entries.
     * @param index of HighscoreEntry. 0 is highest score.
     * @return HighscoreEntry at index
     */
    public HighscoreEntry getHighscoreEntry(final int index) {
        return highscore.get(index);
    }

}
