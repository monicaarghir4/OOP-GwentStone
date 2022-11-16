package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.ActionsDetails;

public class OutputError {
    public void outputErrorPlaceCard(int handIdx, String command, String message, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);
        outputCommand.put("handIdx", handIdx);
        outputCommand.put("error", message);

        output.add(outputCommand);
    }

    public void outputErrorUseEnvironmentCard(int affectedRow, int handIdx, String command, String message, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);
        outputCommand.put("handIdx", handIdx);
        outputCommand.put("affectedRow", affectedRow);
        outputCommand.put("error", message);

        output.add(outputCommand);
    }
}
