package minesweeper.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
            .getResource("Minesweeper.fxml")
        );
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource("style.css")
            .toExternalForm()
        );
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The function that launches the fxml-application.
     * @param args arguments that could affect the launch
     */
    public static void main(final String[] args) {
        launch();
    }
}
