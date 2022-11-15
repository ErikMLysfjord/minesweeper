package minesweeper.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
    
    private ObjectMapper mapper = new HighscoresFileHandler().getMapper();
    private HighscoreList tempList;
    private File easyFile = new File(
        "../core/src/main/resources/minesweeper/json/easyHighscoreList.json"
    );

    @BeforeEach
    public void setUp() throws Exception {
        tempList = mapper.readValue(easyFile, HighscoreList.class);
    }

    private void setNonEmptyList() throws 
        JsonGenerationException,
        JsonMappingException,
        IOException {
        HighscoreList nonEmptyList = new HighscoreList();
        HighscoreEntry entry = new HighscoreEntry("Test", 200);
        nonEmptyList.addEntry(entry);
        mapper.writeValue(easyFile, nonEmptyList);
    }

    private void setEmptyList() throws 
        JsonGenerationException,
        JsonMappingException,
        IOException {
        HighscoreList emptyList = new HighscoreList();
        mapper.writeValue(easyFile, emptyList);
    }

    @Test
    public void testGet() throws Exception {
        setNonEmptyList();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
            "/minesweeper/highscorelist/Easy"
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
            assertEquals("Test", list.getHighscoreEntry(0).getName());
            assertEquals(200, list.getHighscoreEntry(0).getScore());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPost() throws Exception {
        setEmptyList();
        HighscoreEntry entry = new HighscoreEntry("Name", 100);
        String url = "/minesweeper/highscorelist/Easy/save";
        mockMvc.perform(
            MockMvcRequestBuilders.post(url).contentType(
                MediaType.APPLICATION_JSON
            ).content(mapper.writeValueAsString(entry)))
            .andExpect(MockMvcResultMatchers.status().isOk()
        );
        // Get-method to check if the correct data was sent
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
            "/minesweeper/highscorelist/Easy"
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
    public void tearDown() throws IOException {
        mapper.writeValue(easyFile, tempList);
    }
}
