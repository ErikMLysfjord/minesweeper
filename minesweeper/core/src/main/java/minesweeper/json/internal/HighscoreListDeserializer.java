package minesweeper.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import minesweeper.core.HighscoreEntry;
import minesweeper.core.HighscoreList;

public class HighscoreListDeserializer extends JsonDeserializer<HighscoreList> {
    private HighscoreEntryDeserializer highscoreEntryDeserializer =
        new HighscoreEntryDeserializer();

    /**
     * {@inheritDoc}
    */
    @Override
    public HighscoreList deserialize(
        final JsonParser parser,
        final DeserializationContext ctxt
    ) throws IOException, JsonProcessingException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        if (jsonNode instanceof ObjectNode objectNode) {
            int maxSize = objectNode.get("maxSize").asInt();
            HighscoreList highscoreList = new HighscoreList(maxSize);

            ArrayNode entries = (ArrayNode) objectNode.get("entries");
            for (JsonNode node : entries) {
                HighscoreEntry entry = highscoreEntryDeserializer
                    .deserialize(node);
                highscoreList.addEntry(entry);
            }
            return highscoreList;
        }
        return null;
    }

}
