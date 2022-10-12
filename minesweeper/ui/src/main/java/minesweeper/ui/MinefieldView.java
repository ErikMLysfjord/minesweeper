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

    private List<List<ImageView>> squareImages;
    private List<List<Button>> squareButtons;

    /**
     * The image of a flag.
     */
    private final Image flagImage = new Image(getClass()
        .getResourceAsStream("flag.png"));

    /**
     * Constructor for the minefield view.
     * @param width the width of the minefield
     * @param height the height of the minefield
     * represents the minefield
     */
    public MinefieldView(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Initialize squareButtons while adding each button to the GridPane.
     * The buttons do nothing.
     * @param minefieldGridPane gets the buttons added to it
     */
    private void addButtons(final GridPane minefieldGridPane) {
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
    }

    /**
     * Initialize squareImages while adding each image to the GridPane.
     * The images are blank.
     * @param minefieldGridPane gets the images added to it
     */
    private void addImages(final GridPane minefieldGridPane) {
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
     * Initializes the JavaFX objects, and adds them to the GridPane.
     * @param minefieldGridPane gets Buttons and Images added to it
     */
    public void addToGridPane(final GridPane minefieldGridPane) {
        addButtons(minefieldGridPane);
        addImages(minefieldGridPane);
    }

    /**
     * Adds EventHandler to all buttons.
     * @param event the eventhandler to all squares
     */
    public void setOnMouseRelease(final EventHandler<MouseEvent> event) {
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
    private ImageView getImageView(final int x, final int y) {
        return squareImages.get(y).get(x);
    }

    /**
     * Sets flag image.
     * @param x x-coordinate of square
     * @param y y-coordinate of square
     */
    public void setFlagImage(final int x, final int y) {
        getImageView(x, y).setImage(flagImage);
    }

    /**
     * Sets blank image.
     * @param x x-coordinate of image view
     * @param y y-coordinate of image view
     */
    public void setBlankImage(final int x, final int y) {
        getImageView(x, y).setImage(null);
    }

}
