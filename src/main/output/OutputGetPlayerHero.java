package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetPlayerHero {
    /**
     * @param hero the hero of the player
     * @param playerIndex the players index
     * @param output where we will add
     */
    public void outputGetPlayerHero(final CardDetails hero, final int playerIndex,
                                    final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerHero");
        outputCommand.put("playerIdx", playerIndex);

        OutputCardFormat format = new OutputCardFormat();
        ObjectNode theHero = format.outputCard(hero);

        outputCommand.set("output", theHero);

        output.add(outputCommand);
    }
}
