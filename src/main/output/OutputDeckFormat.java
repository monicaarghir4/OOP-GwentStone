package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class OutputDeckFormat {
    public ArrayNode createDeck (ArrayList<CardDetails> deckDetails) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode cards = mapper.createArrayNode();

        for (CardDetails cardDetails : deckDetails) {
            OutputCardFormat card = new OutputCardFormat();
            cards.add(card.outputcard(cardDetails));
        }

        return cards;
    }
}
