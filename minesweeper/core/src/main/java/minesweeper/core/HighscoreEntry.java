package minesweeper.core;

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
     * Get-method for the name.
     * @return the name set
     */
    public String getName() {
        return name;
    }

    /**
     * Get-method for the score.
     * @return the score set
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
