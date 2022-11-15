package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class OutputGetEnvironmentCardsInHand {
    public void outputGetEnvironmentCardsInHand (int playerIndex, ArrayList<CardDetails> playersEnvironmentCards, ArrayNode output) {
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
