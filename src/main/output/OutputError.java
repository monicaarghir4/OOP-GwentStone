package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class OutputError {
    public void outputError(int handIdx, String message, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "placeCard");
        outputCommand.put("handIdx", handIdx);
        outputCommand.put("error", message);

        output.add(outputCommand);
    }
}
