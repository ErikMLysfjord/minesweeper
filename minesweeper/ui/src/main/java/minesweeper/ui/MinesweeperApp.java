package minesweeper.ui;

import javafx.application.Application;
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
