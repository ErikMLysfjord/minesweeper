package minesweeper.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreList {
    private static final int DEFAULT_MAX_SIZE = 5;

    private final List<HighscoreEntry> highscore;
    private final int maxSize;


    /**
     * Constructor for HighscoreList.
     * Sets max size to a default value.
     */
    public HighscoreList() {
        maxSize = DEFAULT_MAX_SIZE;
        highscore = new ArrayList<>();
    }

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

    /**
     * Getter for maxSize.
     * @return maxSize
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * TEMP.
     * @return list
     */
    public List<HighscoreEntry> getHighscores() {
        return new ArrayList<>(highscore);
    }
}
