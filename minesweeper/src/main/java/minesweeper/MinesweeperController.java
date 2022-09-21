package minesweeper;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MinesweeperController {

    public MinesweeperController() {

    }

    @FXML
    private GridPane minefield;
    @FXML
    private TextField text;
    @FXML
    private TextField num;
    
    @FXML
    private void initialize() {

    }

    @FXML
    private void handleRestart() {
        //Restart board
    }

    @FXML
    private void handleClickedSquare(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Button button) {
            Integer x = GridPane.getColumnIndex(button);
            Integer y = GridPane.getRowIndex(button);
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                handleLeftClickedSquare(x, y);
            }
            else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                handleRightClickedSquare(x, y);
            }
        }
    }

    private void handleLeftClickedSquare(Integer x, Integer y) {
        //Check square
    }

    private void handleRightClickedSquare(Integer x, Integer y) {
        putFlagImage(x, y);
    }

    private void putFlagImage(Integer x, Integer y) {
        Image flagImage = new Image(getClass().getResourceAsStream("flag.png"));
        ImageView flagImageView = new ImageView(flagImage);
        flagImageView.setFitHeight(25);
        flagImageView.setFitWidth(25);
        
        minefield.add(flagImageView, x, y);
    }

    @FXML
    private void writeToFile() {
        String str = text.getText();
        String number = num.getText();
        Minesweeper temp = new Minesweeper();
        temp.writeToFile(str, number);
    }

}
