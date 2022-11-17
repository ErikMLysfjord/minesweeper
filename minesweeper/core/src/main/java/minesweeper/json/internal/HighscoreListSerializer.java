package minesweeper.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

/**
 * Can create a json representation of HighscoreList-objects.
 */
public class HighscoreListSerializer extends JsonSerializer<HighscoreList> {
    private HighscoreEntrySerializer highscoreEntrySerializer =
        new HighscoreEntrySerializer();

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
        for (HighscoreEntry entry : highscoreList) {
            highscoreEntrySerializer.serialize(entry, gen, serializers);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
