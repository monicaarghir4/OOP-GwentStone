package main.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import main.game.Game;
import main.gameDetails.Player;
import main.gameDetails.card.environment.Environment;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;
import main.gameDetails.details.CoordinatesDetails;
import main.output.OutputError;
/**
 * Class which treats the errors for each function
 */
public class VerifyErrors {
    private final int number0 = 0;
    private final int number1 = 1;
    private final int number2 = 2;
    private final int number3 = 3;
    private final int number5 = 5;

    /**
     * @param currGame the data for the game that's playing at the moment
     * @param card contains the card that's going to be placed
     * @param cardIndex the index of the card from the players hand
     * @param currPlayer the player which card is placed
     * @param row the row on which the card should be placed
     * @param output for writing to the output
     * @return returns 1 if there are no errors
     */
    public int verifyErrorsPlaceCard(final Game currGame, final CardDetails card,
                                     final int cardIndex, final Player currPlayer,
                                     final int row, final ArrayNode output) {
        Environment environment = new Environment();

        // checking if the card is of type environment
        if (environment.getEnvironmentCards().contains(card.getName())) {
            OutputError outputError = new OutputError();
            outputError.outputErrorPlaceCard(cardIndex, "placeCard",
                    "Cannot place environment card on table.", output);
            return 0;
        }

        // checking if the player has enough mana to place the card
        if (card.getMana() > currPlayer.getMana()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorPlaceCard(cardIndex, "placeCard",
                    "Not enough mana to place card on table.", output);
            return 0;
        }

        // checking if there is room in the row for the card
        if (currGame.getGameTable().get(row).size() >= number5) {
            OutputError outputError = new OutputError();
            outputError.outputErrorPlaceCard(cardIndex, "placeCard",
                    "Cannot place card on table since row is full.", output);
            return 0;
        }

        return 1;
    }

    /**
     * @param currGame the data for the game that's playing at the moment
     * @param card contains the card that's going to be used
     * @param currPlayer the player which is using the card
     * @param cardIndex the index of the card from the players hand
     * @param affectedRow the row that's going to be affected by the card
     * @param output for writing to the output
     * @return returns 1 if there are no errors
     */
    public int verifyErrorsUseEnvironmentCard(final Game currGame, final CardDetails card,
                                              final Player currPlayer, final int cardIndex,
                                              final int affectedRow, final ArrayNode output) {
        // setting the number for the rows of each player
        int firstNum = number0;
        int secondNum = number1;

        if (currGame.getPlayersTurn() == number1) {
            firstNum = number2;
            secondNum = number3;
        }

        // if the card is heart hound we check if the row of the enemy is full
        if (affectedRow == firstNum && card.getName().compareTo("Heart Hound") == 0) {

            if (currGame.getGameTable().get(firstNum).size() == number5) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex,
                        "useEnvironmentCard",
                        "Cannot steal enemy card since the player's row is full.", output);
                return 0;
            }
        } else if (card.getName().compareTo("Heart Hound") == 0) {
            if (currGame.getGameTable().get(secondNum).size() == number5) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex,
                        "useEnvironmentCard",
                        "Cannot steal enemy card since the player's row is full.", output);
                return 0;
            }
        }

        Environment environment = new Environment();

        // checking if the card is environment type
        if (!environment.getEnvironmentCards().contains(card.getName())) {
            OutputError outputError = new OutputError();
            outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex,
                    "useEnvironmentCard",
                    "Chosen card is not of type environment.", output);
            return 0;
        }

        // checking if we have enough mana
        if (card.getMana() > currPlayer.getMana()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex,
                    "useEnvironmentCard",
                    "Not enough mana to use environment card.", output);
            return 0;
        }

        // checking if the row belongs to the enemy
        if (affectedRow == firstNum || affectedRow == secondNum) {
            OutputError outputError = new OutputError();
            outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex,
                    "useEnvironmentCard",
                    "Chosen row does not belong to the enemy.", output);
            return 0;
        }

        return 1;
    }

    /**
     * @param currGame the data for the game that's playing at the moment
     * @param coordinateAttackedX contains the row of the attacked card
     * @param coordinatesDetailsAttacked contains the row and column of the attacked card
     * @param coordinatesDetailsAttacker contains the row and column of the attacker card
     * @param cardAttacker contains the card of the attacker
     * @param cardAttacked contains the card that's going to be attacked
     * @param command contains the command for the output
     * @param output for writing to the output
     * @return returns 1 if there are no errors
     */
    public int verifyErrorsCardUsesAttack(final Game currGame, final int coordinateAttackedX,
                                          final CoordinatesDetails coordinatesDetailsAttacked,
                                          final CoordinatesDetails coordinatesDetailsAttacker,
                                          final CardDetails cardAttacker,
                                          final CardDetails cardAttacked,
                                          final String command, final ArrayNode output) {
        int firstNum = number2;
        int secondNum = number3;

        if (currGame.getPlayersTurn() == number1) {
            firstNum = number0;
            secondNum = number1;
        }

        // checking the coordinates
        if ((firstNum == number2 && coordinateAttackedX == number0)
                || (secondNum == number3 && coordinateAttackedX == number1)) {

            OutputError outputError = new OutputError();
            outputError.outputErrorCardUsesAttackAndAbility(command,
                    "Attacked card does not belong to the enemy.",
                    coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
            return 0;
        }

        // checking if the card has attacked yet
        if (cardAttacker.isHasAttacked()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorCardUsesAttackAndAbility(command,
                    "Attacker card has already attacked this turn.",
                    coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
            return 0;
        }

        // checking if the card is frozen
        if (cardAttacker.isFrozen()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorCardUsesAttackAndAbility(command,
                    "Attacker card is frozen.",
                    coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
            return 0;
        }

        Minion minion = new Minion();

        // verifying if the card is of type Tank and if there is another Tank card
        if (!minion.getTankCards().contains(cardAttacked.getName())) {
            if (verifyTank(currGame, coordinatesDetailsAttacked, coordinatesDetailsAttacker,
                    command, output, firstNum, secondNum, minion)) {
                return 0;
            }
        }

        return 1;
    }

    /**
     * @param currGame the data for the game that's playing at the moment
     * @param coordinateAttackedX contains the row of the attacked card
     * @param coordinatesDetailsAttacked contains the row and column of the attacked card
     * @param coordinatesDetailsAttacker contains the row and column of the attacker card
     * @param cardAttacker contains the card of the attacker
     * @param cardAttacked contains the card that's going to be attacked
     * @param command contains the command for the output
     * @param output for writing to the output
     * @return returns 1 if there are no errors
     */
    public int verifyErrorsCardUsesAbility(final Game currGame, final int coordinateAttackedX,
                                           final CoordinatesDetails coordinatesDetailsAttacked,
                                           final CoordinatesDetails coordinatesDetailsAttacker,
                                           final CardDetails cardAttacker,
                                           final CardDetails cardAttacked,
                                           final String command, final ArrayNode output) {
        int firstNum = number2;
        int secondNum = number3;

        if (currGame.getPlayersTurn() == number1) {
            firstNum = number0;
            secondNum = number1;
        }

        if (cardAttacker.isFrozen()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorCardUsesAttackAndAbility(command,
                    "Attacker card is frozen.",
                    coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
            return 0;
        }

        if (cardAttacker.isHasAttacked()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorCardUsesAttackAndAbility(command,
                    "Attacker card has already attacked this turn.",
                    coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
            return 0;
        }

        // if the card is Disciple we verify if we have our own card as an attacked
        if (cardAttacker.getName().compareTo("Disciple") == 0) {
            if (coordinateAttackedX == firstNum || coordinateAttackedX == secondNum) {

                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility(command,
                        "Attacked card does not belong to the current player.",
                        coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return 0;
            }
        } else {
            // otherwise we check if the card belongs to the enemy
            if ((firstNum == number2 && (coordinateAttackedX == number0
                    || coordinateAttackedX == number1))
                    || (firstNum == number0 && (coordinateAttackedX == number2
                    || coordinateAttackedX == number3))) {

                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility(command,
                        "Attacked card does not belong to the enemy.",
                        coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return 0;
            }
        }

        Minion minion = new Minion();

        // checking for the Tank type
        if (!minion.getTankCards().contains(cardAttacked.getName())
                && cardAttacker.getName().compareTo("Disciple") != 0) {

            if (verifyTank(currGame, coordinatesDetailsAttacked, coordinatesDetailsAttacker,
                    command, output, firstNum, secondNum, minion)) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * @param currGame the data for the game that's playing at the moment
     * @param coordinateCardAttacker contains the row and column of the attacker card
     * @param cardAttacker contains the card of the attacker
     * @param output for writing to the output
     * @return returns 1 if there are no errors
     */
    public int verifyErrorsUseAttackHero(final Game currGame,
                                         final CoordinatesDetails coordinateCardAttacker,
                                         final CardDetails cardAttacker, final ArrayNode output) {
        int firstNum = number2;
        int secondNum = number3;

        if (currGame.getPlayersTurn() == number1) {
            firstNum = number0;
            secondNum = number1;
        }

        if (cardAttacker.isFrozen()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorUseAttackHero("useAttackHero",
                    "Attacker card is frozen.",
                    coordinateCardAttacker, output);
            return 0;
        }

        if (cardAttacker.isHasAttacked()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorUseAttackHero("useAttackHero",
                    "Attacker card has already attacked this turn.",
                    coordinateCardAttacker, output);
            return 0;
        }

        Minion minion = new Minion();

        for (int row = firstNum; row < secondNum + 1; row++) {
            for (CardDetails card : currGame.getGameTable().get(row)) {
                if (minion.getTankCards().contains(card.getName())) {

                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseAttackHero("useAttackHero",
                            "Attacked card is not of type 'Tank'.",
                            coordinateCardAttacker, output);
                    return 0;
                }
            }
        }

        return 1;
    }

    /**
     * @param currGame the data for the game that's playing at the moment
     * @param hero the players hero
     * @param currPlayer the player which is using the card
     * @param affectedRow the row that's going to be affected by the card
     * @param output for writing to the output
     * @return returns 1 if there are no errors
     */
    public int verifyErrorsUseHeroAbility(final Game currGame, final CardDetails hero,
                                          final Player currPlayer,
                                          final int affectedRow, final ArrayNode output) {
        int firstNum = number2;

        if (currGame.getPlayersTurn() == number1) {
            firstNum = number0;
        }

        if (hero.getMana() > currPlayer.getMana()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow,
                    "Not enough mana to use hero's ability.", output);
            return 0;
        }

        if (hero.isHasAttacked()) {
            OutputError outputError = new OutputError();
            outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow,
                    "Hero has already attacked this turn.", output);
            return 0;
        }

        if (hero.getName().compareTo("Lord Royce") == 0
                || hero.getName().compareTo("Empress Thorina") == 0) {

            if (firstNum == number0 && (affectedRow == number2 || affectedRow == number3)
                    || firstNum == number2 && (affectedRow == number0 || affectedRow == number1)) {

                OutputError outputError = new OutputError();
                outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow,
                        "Selected row does not belong to the enemy.", output);
                return 0;
            }
        } else {
            if (firstNum == number0 && (affectedRow == number0 || affectedRow == number1)
                    || firstNum == number2 && (affectedRow == number2 || affectedRow == number3)) {

                OutputError outputError = new OutputError();
                outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow,
                        "Selected row does not belong to the current player.", output);
                return 0;
            }
        }
        return 1;
    }

    /**
     * @param currGame the data for the game that's playing at the moment
     * @param coordinatesDetailsAttacked contains the row and column of the attacked card
     * @param coordinatesDetailsAttacker contains the row and column of the attacker card
     * @param command the command showed to the output
     * @param output for writing to the output
     * @param firstNum the first row of the player
     * @param secondNum the second row of the player
     * @param minion the type of card
     * @return returns true if there is another card of type Tank
     */
    private boolean verifyTank(final Game currGame,
                               final CoordinatesDetails coordinatesDetailsAttacked,
                               final CoordinatesDetails coordinatesDetailsAttacker,
                               final String command, final ArrayNode output, final int firstNum,
                               final int secondNum, final Minion minion) {
        for (int row = firstNum; row < secondNum + 1; row++) {
            for (int column = 0; column < currGame.getGameTable().get(row).size(); column++) {
                if (minion.getTankCards().contains(
                        currGame.getGameTable().get(row).get(column).getName())) {

                    OutputError outputError = new OutputError();
                    outputError.outputErrorCardUsesAttackAndAbility(command,
                            "Attacked card is not of type 'Tank'.",
                            coordinatesDetailsAttacked, coordinatesDetailsAttacker,
                            output);
                    return true;
                }
            }
        }
        return false;
    }
}
