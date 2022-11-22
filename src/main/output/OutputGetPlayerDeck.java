package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetPlayerDeck {
    /**
     * @param deckDetails the deck of the player
     * @param playerIndex the index of the player
     * @param output where we will add
     */
    public void outputGetPlayerDeck(final ArrayList<CardDetails> deckDetails,
                                    final int playerIndex, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerDeck");
        outputCommand.put("playerIdx", playerIndex);

        OutputDeckFormat deckFormat = new OutputDeckFormat();
        ArrayNode deck = deckFormat.createDeck(deckDetails);

        outputCommand.set("output", deck);

        output.add(outputCommand);
    }
}
