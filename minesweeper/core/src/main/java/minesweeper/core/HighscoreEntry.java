package minesweeper.core;

public class HighscoreEntry {
    private final String name;    
    private final Integer score;

    public HighscoreEntry(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

}
