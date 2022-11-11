package minesweeper.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HighscoreList implements Iterable<HighscoreEntry> {
    private static final int DEFAULT_MAX_SIZE = 5;

    private final List<HighscoreEntry> highscoreList;
    private final int maxSize;

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
     * Getter for highscore entries.
     * @param index of HighscoreEntry. 0 is highest score.
     * @return HighscoreEntry at index
     */
    public HighscoreEntry getHighscoreEntry(final int index) {
        return highscoreList.get(index);
    }

    /**
     * Getter for maxSize.
     * @return maxSize
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
