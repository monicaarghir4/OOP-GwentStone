package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.CardDetails;

public class OutputGetPlayerHero {
    public void outputGetPlayerHero(CardDetails hero, int playerIndex, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getPlayerHero");
        outputCommand.put("playerIdx", playerIndex);

        OutputCardFormat format = new OutputCardFormat();
        ObjectNode theHero = format.outputcard(hero);

        outputCommand.set("output", theHero);

        output.add(outputCommand);
    }
}
