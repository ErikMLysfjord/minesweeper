package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinesweeperController {

    public MinesweeperController() {

    }

    @FXML
    GridPane board;

    @FXML
    void initialize() {

    }

    @FXML
    void handleClickedSquare(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Button button) {
            Integer x = GridPane.getColumnIndex(button);
            Integer y = GridPane.getRowIndex(button);
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                handleLeftClickedSquare(x, y);
            }
            else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                handleRightClickedSquare(x, y);
                setFlagOnButton(button); //TEMP location
            }
        }
    }

    private void handleLeftClickedSquare(Integer x, Integer y) {
        //Check square
    }

    private void handleRightClickedSquare(Integer x, Integer y) {
        //Put flag on square
    }

    private void setFlagOnButton(Button button) {
        Image image = new Image(getClass().getResourceAsStream("flag.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        
        button.setGraphic(imageView);
    }
}
