package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class OutputGetPlayerDeck {
    public void outputGetPlayerDeck(ArrayList<CardDetails> deckDetails, int playerIndex, ArrayNode output) {
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
