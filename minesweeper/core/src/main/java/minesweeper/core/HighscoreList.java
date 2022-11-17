package minesweeper.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * HighscoreList contains highscore entries. The highscore list only keeps
 * track of the top "maxSize" scores. It will keep itself sorted as entries are
 * added. Lower scores are better.
 */
public class HighscoreList implements Iterable<HighscoreEntry> {
    private final int maxSize;
    private static final int DEFAULT_MAX_SIZE = 5;
    private final List<HighscoreEntry> highscoreList;

    /**
     * Constructor for HighscoreList.
     * Sets max size to a default value.
     */
    public HighscoreList() {
        this(DEFAULT_MAX_SIZE);
    }

    /**
     * Contructor for HighscoreList.
     * @param maxSize the amount of entries allowed in highscore list
     */
    public HighscoreList(final int maxSize) {
        this.maxSize = maxSize;
        highscoreList = new ArrayList<>();
    }

    /**
     * Adds entry to its correct spot in the highscore list.
     * Keeps the list sorted, and keeps it <= maxSize.
     * Lower scores are considered better.
     * @param entry the entry to try to add to the highscore list
     */
    public void addEntry(final HighscoreEntry entry) {
        highscoreList.add(entry);
        Collections.sort(highscoreList);
        if (highscoreList.size() > maxSize) {
            highscoreList.remove(maxSize);
        }
    }

    /**
     * Gets highscore entry at index.
     * @param index of HighscoreEntry. 0 is lowest score.
     * @return HighscoreEntry at index
     */
    public HighscoreEntry getHighscoreEntry(final int index) {
        return highscoreList.get(index);
    }

    /**
     * Gets the max size of the HighscoreList.
     * @return the max size
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<HighscoreEntry> iterator() {
        return highscoreList.iterator();
    }
}
