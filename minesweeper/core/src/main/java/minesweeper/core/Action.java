package minesweeper.core;

@FunctionalInterface
public interface Action {
    /**
     * Used for passing actions to be taken when events occcur.
     */
    void run();
}
