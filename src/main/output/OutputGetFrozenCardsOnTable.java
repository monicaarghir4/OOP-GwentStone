package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class OutputGetFrozenCardsOnTable {
    public void outputGetFrozenCardsOnTable (ArrayList<ArrayList<CardDetails>> gameTable, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getFrozenCardsOnTable");

        ArrayList<CardDetails> frozen = new ArrayList<>();

        for (ArrayList<CardDetails> row : gameTable) {
            for (CardDetails card : row) {
                if (card.isFrozen()) {
                    frozen.add(card);
                }
            }
        }

        OutputDeckFormat deckFormat = new OutputDeckFormat();
        ArrayNode frozenForm = deckFormat.createDeck(frozen);

        outputCommand.set("output", frozenForm);

        output.add(outputCommand);
    }
}
