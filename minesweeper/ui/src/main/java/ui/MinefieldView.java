package minesweeper;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinefieldView {
    private final int width;
    private final int height;

    private final GridPane minefieldGridPane;

    private List<List<ImageView>> squareImages;
    private List<List<Button>> squareButtons;

    private final Image flagImage = new Image(getClass().getResourceAsStream("flag.png"));
    
    public MinefieldView(int width, int height, GridPane minefieldGridPane) {
        this.width = width;
        this.height = height;

        this.minefieldGridPane = minefieldGridPane;

        addButtons();
        addImages();
    }

    /*  Initialize squareButtons while adding each button to the GridPane.
        The buttons do nothing.
    */
    private void addButtons() {
        squareButtons = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Button> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                Button button = new Button();
                button.getStyleClass().add("square");
                minefieldGridPane.add(button, x, y);
                row.add(button);
            }
            squareButtons.add(row);
        }
        minefieldGridPane.setGridLinesVisible(true);
    }

    /*  Initialize squareImages while adding each image to the GridPane.
        The images are blank.
    */
    private void addImages() {
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

    //Adds EventHandler to all buttons
    public void setOnMouseRelease(EventHandler<MouseEvent> event) {
        for (List<Button> row : squareButtons) {
            for (Button button : row) {
                button.setOnMouseReleased(event);
            }
        }
    }

    private ImageView getImageView(int x, int y) { 
        return squareImages.get(y).get(x);
    }

    public void setFlagImage(int x, int y) {
        getImageView(x, y).setImage(flagImage);
    }

    public void setBlankImage(int x, int y) {
        getImageView(x, y).setImage(null);
    }

}