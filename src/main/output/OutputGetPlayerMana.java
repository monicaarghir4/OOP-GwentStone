package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class OutputGetPlayerMana {
    public void outputGetPlayerMana (int playerIndex, int playerMana, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerMana");
        outputCommand.put("playerIdx", playerIndex);
        outputCommand.put("output", playerMana);

        output.add(outputCommand);
    }
}
