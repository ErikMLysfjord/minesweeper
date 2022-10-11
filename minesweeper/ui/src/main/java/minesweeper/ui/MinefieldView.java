package minesweeper.ui;

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
    
    /**
     * Constructor for the minefield view.
     * @param width the width of the minefield
     * @param height the height of the minefield
     * @param minefieldGridPane the javafx gridpane that represents the minefield
     */
    public MinefieldView(int width, int height, GridPane minefieldGridPane) {
        this.width = width;
        this.height = height;

        this.minefieldGridPane = minefieldGridPane;

        addButtons();
        addImages();
    }

    /**
     * Initialize squareButtons while adding each button to the GridPane.
     * The buttons do nothing.
     */
    private void addButtons() {
        squareButtons = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Button> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                // Configuration of button elements
                Button button = new Button();
                button.getStyleClass().add("square");
                // For testing
                button.setId(String.format(
                    "button%d%d", x, y
                ));
                minefieldGridPane.add(button, x, y);
                row.add(button);
            }
            squareButtons.add(row);
        }
        minefieldGridPane.setGridLinesVisible(true);
    }

    /**
     * Initialize squareImages while adding each image to the GridPane.
     * The images are blank.
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

    /**
     * Adds EventHandler to all buttons.
     * @param event the eventhandler to all squares
     */
    public void setOnMouseRelease(EventHandler<MouseEvent> event) {
        for (List<Button> row : squareButtons) {
            for (Button button : row) {
                button.setOnMouseReleased(event);
            }
        }
    }

    /**
     * Gets the image view.
     * @param x x-coordinate of image view
     * @param y y-coordinate of image view
     * @return the image at the given coordinates
     */
    private ImageView getImageView(int x, int y) { 
        return squareImages.get(y).get(x);
    }

    /**
     * Sets flag image.
     * @param x x-coordinate of square
     * @param y y-coordinate of square
     */
    public void setFlagImage(int x, int y) {
        getImageView(x, y).setImage(flagImage);
    }

    /**
     * Sets blank image.
     * @param x x-coordinate of image view
     * @param y y-coordinate of image view
     */
    public void setBlankImage(int x, int y) {
        getImageView(x, y).setImage(null);
    }

}