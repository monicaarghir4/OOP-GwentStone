package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class OutputGetPlayerTurn {
    public void outputGetPlayerTurn (int playerTurn, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerTurn");
        outputCommand.put("output", playerTurn);

        output.add(outputCommand);
    }
}
