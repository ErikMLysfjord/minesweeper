package minesweeper.server;

import minesweeper.json.HighscoresFileHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The application class that starts the server.
 */
@SpringBootApplication
public class HighscoreApplication {

    /**
     * Starts the server.
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(HighscoreApplication.class, args);
    }

    /**
     * Adds the serializers.
     * @return the serializer
     */
    @Bean
    public ObjectMapper getMapper() {
        return new HighscoresFileHandler().getMapper();
    }
}
