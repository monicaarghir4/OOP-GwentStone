package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Statistics;

public class OutputPlayerTwoWins {
    public void outputPlayerTwoWins (Statistics statistics, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerTwoWins");
        outputCommand.put("output", statistics.getPlayerTwoWins());

        output.add(outputCommand);
    }
}
