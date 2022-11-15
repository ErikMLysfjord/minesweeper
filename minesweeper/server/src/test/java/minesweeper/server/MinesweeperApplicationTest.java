package minesweeper.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
    HighscoreController.class,
    HighscoreService.class,
    HighscoreApplication.class}
)
public class MinesweeperApplicationTest {
    
    @Test
    public void test() {
        
    }
}
