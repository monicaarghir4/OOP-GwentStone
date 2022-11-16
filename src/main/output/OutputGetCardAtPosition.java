package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Game;
import main.gameDetails.details.CardDetails;

public class OutputGetCardAtPosition {
    public void outputGetCardAtPosition (int coordX, int coordY, Game currGame, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getCardAtPosition");
        outputCommand.put("x", coordX);
        outputCommand.put("y", coordY);

        if (coordY >= currGame.getGameTable().get(coordX).size()) {
            return;
        }

        CardDetails card = currGame.getGameTable().get(coordX).get(coordY);

        OutputCardFormat cardFormat = new OutputCardFormat();
        outputCommand.set("output", cardFormat.outputcard(card));

        output.add(outputCommand);
    }
}
