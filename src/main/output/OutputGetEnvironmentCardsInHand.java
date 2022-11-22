package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetEnvironmentCardsInHand {
    /**
     * @param playerIndex the index of the player
     * @param playersEnvironmentCards the environment cards of the player
     * @param output where we will add
     */
    public void outputGetEnvironmentCardsInHand(final int playerIndex,
                                                final ArrayList<CardDetails>
                                                        playersEnvironmentCards,
                                                final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getEnvironmentCardsInHand");
        outputCommand.put("playerIdx", playerIndex);

        OutputDeckFormat deckFormat = new OutputDeckFormat();
        ArrayNode environmentCards = deckFormat.createDeck(playersEnvironmentCards);

        outputCommand.set("output", environmentCards);

        output.add(outputCommand);
    }
}
