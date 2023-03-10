package minesweeper.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import minesweeper.core.Action;

/**
 * Timer starts at 0, and counts up until stopped with stop().
 * Must be started with start(). Can be set up to take actions on each second.
 */
public class Timer extends AnimationTimer {
    private List<Action> onSecondActions;
    private boolean startTimeIsSet = false;
    private long startTime;
    private static final double NANO_SECONDS_IN_SECOND = 1e9;
    private int seconds = 0;

    /**
     * Constructor for Timer.
     */
    public Timer() {
        onSecondActions = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final long now) {
        if (!startTimeIsSet) {
            startTimeIsSet = true;
            startTime = now;
        }
        long timeElapsed = now - startTime;
        if (timeElapsed >= (seconds + 1) * NANO_SECONDS_IN_SECOND) {
            seconds++;
            onSecond();
        }
    }

    /**
     * Adds an action the list of actions to be taken whenever a second passes.
     * @param action action to be taken each second.
     */
    public void addOnSecond(final Action action) {
        onSecondActions.add(action);
    }

    /**
     * Takes each action registered to be taken on each second.
     */
    private void onSecond() {
        for (Action onSecondAction : onSecondActions) {
            onSecondAction.run();
        }
    }

    /**
     * Gets the amount of seconds passed.
     * @return seconds passed
     */
    public int getSeconds() {
        return seconds;
    }
}
