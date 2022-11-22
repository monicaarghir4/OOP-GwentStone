package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Statistics;

/**
 * Class that writes in the json format for the output
 */
public class OutputPlayerTwoWins {
    /**
     * @param statistics the statistics of the game
     * @param output the output where we should add
     */
    public void outputPlayerTwoWins(final Statistics statistics, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerTwoWins");
        outputCommand.put("output", statistics.getPlayerTwoWins());

        output.add(outputCommand);
    }
}
