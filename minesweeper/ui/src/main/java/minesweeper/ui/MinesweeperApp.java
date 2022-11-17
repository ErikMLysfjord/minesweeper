package minesweeper.ui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App.
 */
public class MinesweeperApp extends Application {

    /**
     * Starts the application.
     */
    @Override
    public void start(final Stage stage) throws IOException {
        Image bomb = new Image(getClass().getResourceAsStream("bomb.png"));
        stage.getIcons().add(bomb);
        SceneManager sceneSwitcher = new SceneManager(stage);
        sceneSwitcher.setMinesweeper();
    }

    /**
     * The function that launches the fxml-application.
     * @param args arguments that could affect the launch
     */
    public static void main(final String[] args) {
        launch();
    }
}
