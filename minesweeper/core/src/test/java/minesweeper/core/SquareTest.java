package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SquareTest {
    private Square square;
    
    @BeforeEach
    public void setUp(){
        square = new Square();
    }


    @Test
    public void testFlag() {

        Assertions.assertFalse(square.isFlagged());
        square.toggleFlag();
        Assertions.assertTrue(square.isFlagged());
        square.toggleFlag();
        Assertions.assertFalse(square.isFlagged());
    }

    @Test
    public void testMine(){
        Assertions.assertFalse(square.hasMine());
        square.placeMine();
        Assertions.assertTrue(square.hasMine());
    }

}
