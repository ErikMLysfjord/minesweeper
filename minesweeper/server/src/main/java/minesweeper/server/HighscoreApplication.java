package minesweeper.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HighscoreApplication {

    /**
     * Starts the server.
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(HighscoreApplication.class, args);
    }

}
