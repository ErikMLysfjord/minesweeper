package minesweeper.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import minesweeper.core.Action;

public class Timer extends AnimationTimer {
    List<Action> onSecondActions;

    public Timer() {
        onSecondActions = new ArrayList<>();
    }

    @Override
    public void handle(long now) {
        
    }

    public void setOnSecond(Action action) {
        onSecondActions.add(action);
    }

    private void onSecond() {

    }

}
