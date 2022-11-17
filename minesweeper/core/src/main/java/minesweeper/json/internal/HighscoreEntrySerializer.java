package minesweeper.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import minesweeper.core.HighscoreEntry;

/**
 * Can create a json representation of HighscoreEntry-objects.
 */
public class HighscoreEntrySerializer extends JsonSerializer<HighscoreEntry> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(
        final HighscoreEntry entry,
        final JsonGenerator gen,
        final SerializerProvider serializers
    ) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("name", entry.getName());
        gen.writeNumberField("score", entry.getScore());

        gen.writeEndObject();
    }
}
