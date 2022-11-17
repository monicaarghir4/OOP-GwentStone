package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.details.ActionsDetails;
import main.gameDetails.details.CoordinatesDetails;

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

    public void outputErrorGetCardAtPosition(String command, String message, int coordX, int coordY, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);
        outputCommand.put("x", coordX);
        outputCommand.put("y", coordY);
        outputCommand.put("output", message);

        output.add(outputCommand);
    }

    public void outputErrorCardUsesAttackAndAbility(String command, String message, CoordinatesDetails coordAttacked, CoordinatesDetails coordAttacker, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);

        ObjectNode attacked = mapper.createObjectNode();

        attacked.put("x", coordAttacked.getX());
        attacked.put("y", coordAttacked.getY());

        ObjectNode attacker = mapper.createObjectNode();

        attacker.put("x", coordAttacker.getX());
        attacker.put("y", coordAttacker.getY());

        outputCommand.set("cardAttacker", attacker);
        outputCommand.set("cardAttacked", attacked);

        outputCommand.put("error", message);

        output.add(outputCommand);
    }

    public void outputErrorUseAttackHero(String command, String message, CoordinatesDetails coordAttacker, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);

        ObjectNode attacker = mapper.createObjectNode();

        attacker.put("x", coordAttacker.getX());
        attacker.put("y", coordAttacker.getY());

        outputCommand.set("cardAttacker", attacker);

        outputCommand.put("error", message);

        output.add(outputCommand);
    }

    public void outputErrorHeroDied(int playersTurn, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        if (playersTurn == 1) {
            outputCommand.put("gameEnded", "Player one killed the enemy hero.");
        } else {
            outputCommand.put("gameEnded", "Player two killed the enemy hero.");
        }

        output.add(outputCommand);
    }
}
