package minesweeper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Square {
    private boolean isFlagged = false;
    private ImageView flagImage;

    public Square() {
        setFlagImage();
    }

    public void toggleFlag() {
        isFlagged = !isFlagged;
        flagImage.setVisible(isFlagged);
    }

    public boolean isFlagged() {
        return isFlagged;
    }
    
    private void setFlagImage() {
        flagImage = new ImageView(new Image(getClass().getResourceAsStream("flag.png")));
        flagImage.setFitHeight(25);
        flagImage.setFitWidth(25);
        flagImage.setVisible(false);
    }

    public ImageView getFlagImage() {
        return flagImage;
    }
}
