package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class OutputGetCardsOnTable {
    public void outputGetCardsOnTable (ArrayList<ArrayList<CardDetails>> gameTable, ArrayNode output) {
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
