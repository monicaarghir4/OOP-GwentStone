package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.game.Statistics;
import main.gameDetails.details.CoordinatesDetails;

/**
 * Class that writes the errors in the json format for the output
 */
public class OutputError {
    /**
     * @param handIdx the index of the card
     * @param command the command "placeCard"
     * @param message the message we will print to the output
     * @param output where we will add
     */
    public void outputErrorPlaceCard(final int handIdx, final String command, final String message,
                                     final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);
        outputCommand.put("handIdx", handIdx);
        outputCommand.put("error", message);

        output.add(outputCommand);
    }

    /**
     * @param affectedRow the row that is affected by the command
     * @param handIdx the index of the card
     * @param command the command "useEnvironmentCard"
     * @param message the message we will print to the output
     * @param output where we will add
     */
    public void outputErrorUseEnvironmentCard(final int affectedRow, final int handIdx,
                                              final String command, final String message,
                                              final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);
        outputCommand.put("handIdx", handIdx);
        outputCommand.put("affectedRow", affectedRow);
        outputCommand.put("error", message);

        output.add(outputCommand);
    }

    /**
     * @param command the command "getCardAtPosition"
     * @param message the message we will print to the output
     * @param coordinateX the row the card is on
     * @param coordinateY the column the card is on
     * @param output where we will add
     */
    public void outputErrorGetCardAtPosition(final String command, final String message,
                                             final int coordinateX, final int coordinateY,
                                             final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);
        outputCommand.put("x", coordinateX);
        outputCommand.put("y", coordinateY);
        outputCommand.put("output", message);

        output.add(outputCommand);
    }

    /**
     * @param command the command "cardUsesAttack" or "cardUsesAbility"
     * @param message the message we will print to the output
     * @param coordinateAttacked the coordinates of the attacked card
     * @param coordinateAttacker the coordinates of the attacker card
     * @param output where we will add
     */
    public void outputErrorCardUsesAttackAndAbility(final String command, final String message,
                                                    final CoordinatesDetails coordinateAttacked,
                                                    final CoordinatesDetails coordinateAttacker,
                                                    final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);

        ObjectNode attacked = mapper.createObjectNode();

        attacked.put("x", coordinateAttacked.getX());
        attacked.put("y", coordinateAttacked.getY());

        ObjectNode attacker = mapper.createObjectNode();

        attacker.put("x", coordinateAttacker.getX());
        attacker.put("y", coordinateAttacker.getY());

        outputCommand.set("cardAttacker", attacker);
        outputCommand.set("cardAttacked", attacked);

        outputCommand.put("error", message);

        output.add(outputCommand);
    }

    /**
     * @param command the command "useAttackHero"
     * @param message the message we will print to the output
     * @param coordinateAttacker the coordinates of the attacker card
     * @param output where we will add
     */
    public void outputErrorUseAttackHero(final String command, final String message,
                                         final CoordinatesDetails coordinateAttacker,
                                         final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);

        ObjectNode attacker = mapper.createObjectNode();

        attacker.put("x", coordinateAttacker.getX());
        attacker.put("y", coordinateAttacker.getY());

        outputCommand.set("cardAttacker", attacker);

        outputCommand.put("error", message);

        output.add(outputCommand);
    }

    /**
     * @param playersTurn the turn of the player
     * @param output where we will add
     * @param statistics the statistics
     */
    public void outputErrorHeroDied(final int playersTurn, final ArrayNode output,
                                    final Statistics statistics) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        if (playersTurn == 1) {
            outputCommand.put("gameEnded", "Player one killed the enemy hero.");
            statistics.setPlayerOneWins(statistics.getPlayerOneWins() + 1);

        } else {
            outputCommand.put("gameEnded", "Player two killed the enemy hero.");
            statistics.setPlayerTwoWins(statistics.getPlayerTwoWins() + 1);
        }

        output.add(outputCommand);
    }

    /**
     * @param command the command "useHeroAbility"
     * @param affectedRow the row that is affected by the command
     * @param message the message we will print to the output
     * @param output where we will add
     */
    public void outputErrorUseHeroAbility(final String command, final int affectedRow,
                                          final String message, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outputCommand = mapper.createObjectNode();

        outputCommand.put("command", command);
        outputCommand.put("affectedRow", affectedRow);
        outputCommand.put("error", message);

        output.add(outputCommand);
    }
}
