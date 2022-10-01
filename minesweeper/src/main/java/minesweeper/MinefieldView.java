package minesweeper;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinefieldView {
    private final int width;
    private final int height;

    private final GridPane minefieldGridPane;
    private final List<List<ImageView>> squareImages;
    
    public MinefieldView(int width, int height, GridPane minefieldGridPane) {
        this.width = width;
        this.height = height;

        this.minefieldGridPane = minefieldGridPane;

        /*  Initialize squareImages while adding each image to the GridPane.
            The images are blank.
        */
        squareImages = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<ImageView> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                ImageView blankImageView = new ImageView();
                minefieldGridPane.add(blankImageView, x, y);
                row.add(blankImageView);
            }
            squareImages.add(row);
        }
    }

    public void addButtons(EventHandler<MouseEvent> handleClickedSquare) {
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