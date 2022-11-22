package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetCardsInHand {
    /**
     * @param playerIndex the index of the player
     * @param playersHand the players hand of cards
     * @param output where we will add
     */
    public void outputGetCardsInHand(final int playerIndex,
                                     final ArrayList<CardDetails> playersHand,
                                     final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getCardsInHand");
        outputCommand.put("playerIdx", playerIndex);

        OutputDeckFormat deckFormat = new OutputDeckFormat();
        ArrayNode hand = deckFormat.createDeck(playersHand);

        outputCommand.set("output", hand);

        output.add(outputCommand);
    }
}
