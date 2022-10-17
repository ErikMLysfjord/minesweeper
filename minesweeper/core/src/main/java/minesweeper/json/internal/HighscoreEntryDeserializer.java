package minesweeper.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import minesweeper.core.HighscoreEntry;

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
     * @return highcore entry
     */
    public HighscoreEntry deserialize(final JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode objectNode) {
            TextNode name = (TextNode) objectNode.get("name");
            IntNode score = (IntNode) objectNode.get("score");
            return new HighscoreEntry(name.asText(), score.asInt());
        }
        return null;
    }
}
