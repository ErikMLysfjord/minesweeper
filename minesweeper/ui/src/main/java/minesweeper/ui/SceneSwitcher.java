package minesweeper.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SceneSwitcher {
    private final Stage stage;

    /**
     * Controller for SceneSwitcher.
     * @param stage the stage where scenes are shown
     */
    public SceneSwitcher(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates and displays a new minesweeper scene.
     */
    public void setMinesweeper() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("Minesweeper.fxml")
            );
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("style.css")
                .toExternalForm()
            );
            MinesweeperController controller = fxmlLoader.getController();
            controller.setSceneSwitcher(this);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(
                AlertType.ERROR, e.getMessage(), ButtonType.OK
            );
            alert.show();
        }
    }

    /**
     * Creates and displays a new highscores scene.
     */
    public void setHighscores() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("Highscores.fxml")
            );
            HighscoresController controller = new HighscoresController();
            controller.setSceneSwitcher(this);
            fxmlLoader.setController(controller);
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(
                AlertType.ERROR, e.getMessage(), ButtonType.OK
            );
            alert.show();
        }
    }
}
