package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetPlayerMana {
    /**
     * @param playerIndex the players index
     * @param playerMana the players mana
     * @param output where we will add
     */
    public void outputGetPlayerMana(final int playerIndex, final int playerMana,
                                    final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerMana");
        outputCommand.put("playerIdx", playerIndex);
        outputCommand.put("output", playerMana);

        output.add(outputCommand);
    }
}
