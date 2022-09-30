package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MinefieldView {
    private final int width;
    private final int height;
    private final GridPane minefieldGridPane;
    
    public MinefieldView(int width, int height, GridPane minefieldGridPane) {
        this.width = width;
        this.height = height;
        this.minefieldGridPane = minefieldGridPane;
    }

    public void addSquares() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                minefieldGridPane.add(new Button(), y, x);
            }
        }
    }
}
