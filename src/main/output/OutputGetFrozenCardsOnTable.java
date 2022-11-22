package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetFrozenCardsOnTable {
    /**
     * @param gameTable the table of the game
     * @param output where we will add
     */
    public void outputGetFrozenCardsOnTable(final ArrayList<ArrayList<CardDetails>> gameTable,
                                            final ArrayNode output) {
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
