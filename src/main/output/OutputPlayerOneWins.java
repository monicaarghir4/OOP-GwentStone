package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Statistics;

public class OutputPlayerOneWins {
    public void outputPlayerOneWins (Statistics statistics, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerOneWins");
        outputCommand.put("output", statistics.getPlayerOneWins());

        output.add(outputCommand);
    }
}
