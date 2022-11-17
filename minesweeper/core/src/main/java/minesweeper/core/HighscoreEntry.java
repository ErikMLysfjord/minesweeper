package minesweeper.core;

/**
 * Highscore entries are contained in highscore lists. This is a data class
 * with the player name and his/her score. The score is supposed to be the time
 * it took to win.
 */
public class HighscoreEntry implements Comparable<HighscoreEntry> {
    private final String name;
    private final Integer score;

    /**
     * Constructor for the highscore entry.
     * @param name the name of the user
     * @param score the score of the user
     */
    public HighscoreEntry(final String name, final Integer score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the score.
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final HighscoreEntry entry) {
        return score - entry.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(
            "[name=%s, score=%d]",
            name,
            score
        );
    }
}
