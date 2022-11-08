package minesweeper.server;

public class Placeholder {
    private String noe;

    /**
     * Placeholder constructor.
     * @param noe placeholder parameter
     */
    public Placeholder(final String noe) {
        this.noe = noe;
    }

    /**
     * This is just a placeholder.
     * @param tekst placeholder text
     * @return a placeholder text
     */
    public String place(final String tekst) {
        return tekst + noe;
    }
}
