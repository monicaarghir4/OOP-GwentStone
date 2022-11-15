package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class OutputGetCardsInHand {
    public void outputGetCardsInHand (int playerIndex, ArrayList<CardDetails> playersHand, ArrayNode output) {
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
