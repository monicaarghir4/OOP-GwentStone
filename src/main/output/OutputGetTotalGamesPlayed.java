package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Statistics;

public class OutputGetTotalGamesPlayed {
    public void outputGetTotalGamesPlayed (Statistics statistics, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getTotalGamesPlayed");
        outputCommand.put("output", statistics.getTotalGamesPlayed());

        output.add(outputCommand);
    }
}
