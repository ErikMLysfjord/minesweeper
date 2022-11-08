package minesweeper.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlaceholderTest {
    private Placeholder placeholder;
    
    @Test
    public void test() {
        placeholder = new Placeholder("Test");
        Assertions.assertEquals("TestTest", placeholder.place("Test"));
    }
}
