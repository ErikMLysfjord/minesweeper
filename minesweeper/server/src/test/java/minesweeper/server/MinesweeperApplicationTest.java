package minesweeper.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;
import minesweeper.json.HighscoresFileHandler;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
    HighscoreController.class,
    HighscoreService.class,
    HighscoreApplication.class}
)
public class MinesweeperApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new HighscoresFileHandler().getMapper();
    }

    @Test
    public void testGet() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
            "/minesweeper/highscorelist/Test"
            ).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        try {
            HighscoreList list = mapper.readValue(
                result.getResponse()
                .getContentAsString(),
                HighscoreList.class
            );
            assertEquals(5, list.getMaxSize());
            assertThrows(IndexOutOfBoundsException.class, () -> {
                list.getHighscoreEntry(0);
            });
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPost() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("Name", 100);
        String url = "/minesweeper/highscorelist/Test/save";
        mockMvc.perform(
            MockMvcRequestBuilders.post(url).contentType(
                MediaType.APPLICATION_JSON
            ).content(mapper.writeValueAsString(entry)))
            .andExpect(MockMvcResultMatchers.status().isOk()
        );
        // Get-method to check if the correct data was sent
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
            "/minesweeper/highscorelist/Test"
        ).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        try {
            HighscoreList list = mapper.readValue(
                result.getResponse()
                .getContentAsString(),
                HighscoreList.class
            );
            assertEquals("Name", list.getHighscoreEntry(0).getName());
            assertEquals(100, list.getHighscoreEntry(0).getScore());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        new File(
            "../core/src/main/resources/minesweeper/json/testPersistence.json"
        ).delete();
    }
}
