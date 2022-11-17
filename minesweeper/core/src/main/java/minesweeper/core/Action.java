package minesweeper.core;

/**
 * Actions to be taken when events occcur.
 */
@FunctionalInterface
public interface Action {
    /**
     * Takes the action.
     */
    void run();
}
