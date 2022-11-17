package minesweeper.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import minesweeper.core.HighscoreEntry;

/**
 * Can create HighscoreEntry-object from a json representation.
 */
public class HighscoreEntryDeserializer
       extends JsonDeserializer<HighscoreEntry> {

    /**
     * {@inheritDoc}
     */
    @Override
    public HighscoreEntry deserialize(
        final JsonParser parser,
        final DeserializationContext ctxt
    ) throws IOException, JsonProcessingException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        return deserialize(jsonNode);
    }

    /**
     * Deserializes json node.
     * @param jsonNode to be deserialized
     * @return highscore entry
     */
    public HighscoreEntry deserialize(final JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode objectNode) {
            String name = objectNode.get("name").asText();
            int score = objectNode.get("score").asInt();
            return new HighscoreEntry(name, score);
        }
        return null;
    }
}
