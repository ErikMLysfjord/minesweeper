package minesweeper.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MinefieldViewTest extends ApplicationTest {

    private MinesweeperController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Minesweeper.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());       
        stage.setScene(scene);
        stage.show();
    }

    public Parent getRootNode() {
        return root;
    }

    private Node getElementById(String fxid) {
        return root.lookup(fxid);
    }

    private Boolean buttonWasPressed = false;
    @Test
    public void testMinefieldButtons() {
        MinefieldView minefieldView = controller.getMinefieldView();
        minefieldView.setOnMouseRelease((event) -> {
            buttonWasPressed = true;
        });

        Node button = getElementById("#button00");
        clickOn(button, MouseButton.SECONDARY);

        Assertions.assertTrue(buttonWasPressed);
    }

    @Test
    public void testGridPane() {
        GridPane minefieldGridPane = (GridPane) getElementById("#minefieldGridPane");

        Assertions.assertEquals(
            MinesweeperController.MINEFIELD_WIDTH,
            minefieldGridPane.getColumnCount()
        );

        Assertions.assertEquals(
            MinesweeperController.MINEFIELD_HEIGHT,
            minefieldGridPane.getRowCount()
        );

        Assertions.assertTrue(minefieldGridPane.isGridLinesVisible());
    }

}
