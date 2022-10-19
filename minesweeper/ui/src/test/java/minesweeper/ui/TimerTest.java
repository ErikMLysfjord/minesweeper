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
    private Timer timer;

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
    @Test
    public void testOnSecond() {
        seconds = 0;
        timer = new Timer();
        timer.addOnSecond(() -> updateSeconds());
        timer.start();
        Assertions.assertEquals(0, timer.getSeconds());
        sleep(1337);
        Assertions.assertEquals(1, timer.getSeconds());
        Assertions.assertEquals(1, seconds);
        timer.stop();
    }

    public void updateSeconds() {
        seconds = timer.getSeconds();
    }

}
