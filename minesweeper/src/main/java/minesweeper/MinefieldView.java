package minesweeper;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

    //Initializes the minefield
    public void addSquares(EventHandler<MouseEvent> handleClickedSquare) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Button button = new Button();
                button.setOnMouseReleased(handleClickedSquare);
                button.getStyleClass().add("square");
                minefieldGridPane.add(button, y, x);
            }
        }
        minefieldGridPane.setGridLinesVisible(true);
    }
}