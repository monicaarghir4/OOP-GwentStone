package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that writes in the json format for the output
 */
public class OutputDeckFormat {
    /**
     * @param deckDetails the deck we want
     * @return the whole deck format
     */
    public ArrayNode createDeck(final ArrayList<CardDetails> deckDetails) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode cards = mapper.createArrayNode();

        for (CardDetails cardDetails : deckDetails) {
            OutputCardFormat card = new OutputCardFormat();
            cards.add(card.outputCard(cardDetails));
        }

        return cards;
    }
}
