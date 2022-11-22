package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Game;
import main.gameDetails.details.CardDetails;

/**
 * Class that writes in the json format for the output
 */
public class OutputGetCardAtPosition {
    /**
     * @param coordinateX the row of the card
     * @param coordinateY the column of the card
     * @param currGame the game that's playing
     * @param output where we will add
     */
    public void outputGetCardAtPosition(final int coordinateX, final int coordinateY,
                                        final Game currGame, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", "getCardAtPosition");
        outputCommand.put("x", coordinateX);
        outputCommand.put("y", coordinateY);

        if (coordinateY >= currGame.getGameTable().get(coordinateX).size()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorGetCardAtPosition("getCardAtPosition",
                    "No card available at that position.", coordinateX, coordinateY, output);
            return;
        }

        CardDetails card = currGame.getGameTable().get(coordinateX).get(coordinateY);

        OutputCardFormat cardFormat = new OutputCardFormat();
        outputCommand.set("output", cardFormat.outputCard(card));

        output.add(outputCommand);
    }
}
