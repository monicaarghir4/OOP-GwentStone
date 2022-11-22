package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetCardsOnTable {
    /**
     * @param gameTable the table of the game
     * @param output where we will add
     */
    public void outputGetCardsOnTable(final ArrayList<ArrayList<CardDetails>> gameTable,
                                      final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getCardsOnTable");

        ArrayNode cards = mapper.createArrayNode();

        for (ArrayList<CardDetails> row : gameTable) {
            OutputDeckFormat deckFormat = new OutputDeckFormat();
            ArrayNode rowCard = deckFormat.createDeck(row);

            cards.add(rowCard);
        }

        outputCommand.set("output", cards);
        output.add(outputCommand);
    }
}
