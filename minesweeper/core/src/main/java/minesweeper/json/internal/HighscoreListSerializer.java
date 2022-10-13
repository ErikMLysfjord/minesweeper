package minesweeper.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

public class HighscoreListSerializer extends JsonSerializer<HighscoreList> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(
        final HighscoreList highscoreList,
        final JsonGenerator gen,
        final SerializerProvider serializers
    ) throws IOException {
        gen.writeStartObject();

        gen.writeNumberField("maxSize", highscoreList.getMaxSize());

        gen.writeArrayFieldStart("entries");
        for (HighscoreEntry entry : highscoreList.getHighscores()) {
            gen.writeObject(entry);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }

}
