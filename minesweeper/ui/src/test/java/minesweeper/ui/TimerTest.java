package minesweeper.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TimerTest extends ApplicationTest {
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Minesweeper.fxml"));
        root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());       
        stage.setScene(scene);
        stage.show();
    }

    public Parent getRootNode() {
        return root;
    }

    private int seconds;
    private Timer timer;
    @Test
    public void testOnSecond() {
        seconds = 0;
        timer = new Timer();
        timer.start();
        timer.setOnSecond(() -> updateSeconds());
        sleep(1001);
        timer.stop();
        Assertions.assertEquals(1, seconds);
    }

    public void updateSeconds() {
        seconds = timer.getSeconds();
    }

}
