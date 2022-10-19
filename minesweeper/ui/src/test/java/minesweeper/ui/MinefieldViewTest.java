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

    private Node getElementById(String id) {
        return root.lookup(id);
    }

    private boolean nodeExists(String id) {
        return getElementById(id) != null;
    }

    @Test
    public void testSetFlag() {
        Node button = getElementById("#button00");
        Assertions.assertFalse(nodeExists("#image00flag"));
        clickOn(button, MouseButton.SECONDARY);
        Assertions.assertTrue(nodeExists("#image00flag"));
        clickOn(button, MouseButton.SECONDARY);
        Assertions.assertFalse(nodeExists("#image00flag"));
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

    @Test
    public void testCheckSquare() {
        Node button = getElementById("#button00");
        // The first square pressed is supposed to have 0 adjacent mines.
        Assertions.assertFalse(nodeExists("#image00opened0"));
        clickOn(button, MouseButton.PRIMARY);
        Assertions.assertTrue(nodeExists("#image00opened0"));
    }

}
