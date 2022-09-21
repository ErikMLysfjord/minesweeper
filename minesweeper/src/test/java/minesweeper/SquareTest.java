package minesweeper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {


    @Test
    public void testFlag() {
        Square square = new Square();

        Assertions.assertFalse(square.isFlagged());
        square.toggleFlag();
        Assertions.assertTrue(square.isFlagged());
        square.toggleFlag();
        Assertions.assertFalse(square.isFlagged());
    }

}
