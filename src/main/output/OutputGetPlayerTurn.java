package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetPlayerTurn {
    /**
     * @param playerTurn the players turn
     * @param output where we will add
     */
    public void outputGetPlayerTurn(final int playerTurn, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerTurn");
        outputCommand.put("output", playerTurn);

        output.add(outputCommand);
    }
}
