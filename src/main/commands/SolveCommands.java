package main.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;
import main.game.Game;
import main.game.Statistics;
import main.gameDetails.card.environment.Environment;
import main.gameDetails.card.hero.Hero;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.*;
import main.gameDetails.Player;
import main.output.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * class which implements the game by checking the commands
 */
public class SolveCommands {
    private final int number0 = 0;
    private final int number1 = 1;
    private final int number2 = 2;
    private final int number3 = 3;

    /**
     * @param inputOriginal for always having the unmodified input
     * @param inputDetails  to work with it
     * @param output        for the results
     */
    public final void checkCommands(final Input inputOriginal, InputDetails inputDetails,
                                    final ArrayNode output) {

        // getting the games from the input
        // creating the statistics class for memorizing the scores

        ArrayList<GameDetails> gamesDetails = inputDetails.getGames();
        Statistics statistics = new Statistics();

        // going through the game
        for (GameDetails gameDetails : gamesDetails) {
            statistics.setTotalGamesPlayed(statistics.getTotalGamesPlayed() + number1);

            //making a copy for the input
            inputDetails = new InputDetails(inputOriginal);
            ArrayList<ActionsDetails> actionsDetails = gameDetails.getActions();

            ArrayList<CardDetails> deckPlayer1, deckPlayer2;
            int deckPlayer1Index = gameDetails.getStartGame().getPlayerOneDeckIdx();
            int deckPlayer2Index = gameDetails.getStartGame().getPlayerTwoDeckIdx();

            deckPlayer1 = inputDetails.getPlayerOneDecks().getDecks().get(deckPlayer1Index);
            deckPlayer2 = inputDetails.getPlayerTwoDecks().getDecks().get(deckPlayer2Index);

            // shuffling the decks
            Collections.shuffle(deckPlayer1,
                    new Random(gameDetails.getStartGame().getShuffleSeed()));
            Collections.shuffle(deckPlayer2,
                    new Random(gameDetails.getStartGame().getShuffleSeed()));

            Game currGame = new Game();
            currGame.setRounds(number1);

            startingRound(gameDetails, deckPlayer1, deckPlayer2, currGame);

            for (ActionsDetails actionDetails : actionsDetails) {
                String command = actionDetails.getCommand();

                switch (command) {
                    case "getCardsInHand" -> getCardsInHand(actionDetails, currGame, output);
                    case "getPlayerDeck" -> getPlayerDeck(inputDetails, gameDetails, actionDetails,
                            output);
                    case "getCardsOnTable" -> getCardsOnTable(currGame, output);
                    case "getPlayerTurn" -> getPlayerTurn(currGame, output);
                    case "getPlayerHero" -> getPlayerHero(gameDetails, actionDetails, output);
                    case "getCardAtPosition" -> getCardAtPosition(actionDetails, currGame, output);
                    case "getPlayerMana" -> getPlayerMana(actionDetails, currGame, output);
                    case "getEnvironmentCardsInHand" -> getEnvironmentCardsInHand(actionDetails,
                            currGame, output);
                    case "getFrozenCardsOnTable" -> getFrozenCardsOnTable(currGame, output);
                    case "endPlayerTurn" -> endPlayerTurn(gameDetails, currGame, deckPlayer1,
                            deckPlayer2);
                    case "placeCard" -> placeCard(actionDetails, currGame, output);
                    case "useEnvironmentCard" -> useEnvironmentCard(actionDetails, currGame,
                            output);
                    case "cardUsesAttack" -> cardUsesAttack(actionDetails, currGame, output);
                    case "cardUsesAbility" -> cardUsesAbility(actionDetails, currGame, output);
                    case "useAttackHero" -> useAttackHero(actionDetails, currGame, gameDetails,
                            statistics, output);
                    case "useHeroAbility" -> useHeroAbility(actionDetails, currGame, gameDetails,
                            output);
                    case "getTotalGamesPlayed" -> getTotalGamesPlayed(statistics, output);
                    case "getPlayerOneWins" -> getPlayerOneWins(statistics, output);
                    case "getPlayerTwoWins" -> getPlayerTwoWins(statistics, output);
                    default -> {
                        return;
                    }
                }
            }
        }
    }

    /**
     * Function used for setting the specifics at the beginning of each new round
     *
     * @param gameDetails contains the data for the game
     * @param deckPlayer1 the deck used by the first player
     * @param deckPlayer2 the deck used by the second player
     * @param currGame    the data for the game that's playing at the moment
     */
    public void startingRound(final GameDetails gameDetails,
                              final ArrayList<CardDetails> deckPlayer1,
                              final ArrayList<CardDetails> deckPlayer2, final Game currGame) {
        Player player1 = currGame.getPlayer1();
        Player player2 = currGame.getPlayer2();

        // drawing a card from the deck and removing it from the deck
        if (!deckPlayer1.isEmpty()) {
            player1.getPlayersHand().add(deckPlayer1.remove(0));
        }

        if (!deckPlayer2.isEmpty()) {
            player2.getPlayersHand().add(deckPlayer2.remove(0));
        }

        currGame.setPlayersTurn(gameDetails.getStartGame().getStartingPlayer());

        // setting the mana
        if (currGame.getRounds() == number1) {
            player1.setMana(number1);
            player2.setMana(number1);
        } else {
            player1.setMana(player1.getMana() + currGame.getRounds());
            player2.setMana(player2.getMana() + currGame.getRounds());
        }
    }

    /**
     * Function that writes in the output the deck of the player with which he plays
     *
     * @param inputDetails   for getting the decks
     * @param gameDetails    contains the data for the game
     * @param actionsDetails for getting the data of the command
     * @param output         for writing in the output format
     */
    public void getPlayerDeck(final InputDetails inputDetails, final GameDetails gameDetails,
                              final ActionsDetails actionsDetails, final ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();

        DecksDetails decksDetails;
        int deckIndex;

        if (playerIndex == number1) {
            decksDetails = inputDetails.getPlayerOneDecks();
            deckIndex = gameDetails.getStartGame().getPlayerOneDeckIdx();

        } else {
            decksDetails = inputDetails.getPlayerTwoDecks();
            deckIndex = gameDetails.getStartGame().getPlayerTwoDeckIdx();

        }
        OutputGetPlayerDeck outputGetPlayerDeck = new OutputGetPlayerDeck();
        outputGetPlayerDeck.outputGetPlayerDeck(decksDetails.getDecks().get(deckIndex),
                playerIndex, output);
    }

    /**
     * Function that writes in the output the hero given to the player
     *
     * @param gameDetails    contains the data for the game
     * @param actionsDetails for getting the data of the command
     * @param output         for writing in the output format
     */
    public void getPlayerHero(final GameDetails gameDetails, final ActionsDetails actionsDetails,
                              final ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();

        OutputGetPlayerHero outputGetPlayerHero = new OutputGetPlayerHero();

        if (playerIndex == number1) {
            outputGetPlayerHero.outputGetPlayerHero(gameDetails.getStartGame().getPlayerOneHero(),
                    playerIndex, output);
        } else {
            outputGetPlayerHero.outputGetPlayerHero(gameDetails.getStartGame().getPlayerTwoHero(),
                    playerIndex, output);
        }
    }

    /**
     * Function that writes in the output the turn of the round
     *
     * @param currGame the data for the game that's playing at the moment
     * @param output   for writing in the output format
     */
    public void getPlayerTurn(final Game currGame, final ArrayNode output) {
        int playersTurn = currGame.getPlayersTurn();

        OutputGetPlayerTurn outputGetPlayerTurn = new OutputGetPlayerTurn();
        outputGetPlayerTurn.outputGetPlayerTurn(playersTurn, output);
    }

    /**
     * Function that ends a players turn and sets the characteristics of the game back to normal
     *
     * @param gameDetails contains the data for the game
     * @param currGame    the data for the game that's playing at the moment
     * @param deckPlayer1 the deck used by the first player
     * @param deckPlayer2 the deck used by the second player
     */
    public void endPlayerTurn(final GameDetails gameDetails, final Game currGame,
                              final ArrayList<CardDetails> deckPlayer1,
                              final ArrayList<CardDetails> deckPlayer2) {

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        if (currGame.getPlayersTurn() == number1) {
            setRowToNormal(gameTable, number2);
            setRowToNormal(gameTable, number3);

            currGame.setPlayersTurn(number2);
            gameDetails.getStartGame().getPlayerOneHero().setHasAttacked(false);

        } else {
            setRowToNormal(gameTable, number0);
            setRowToNormal(gameTable, number1);

            currGame.setPlayersTurn(number1);
            gameDetails.getStartGame().getPlayerTwoHero().setHasAttacked(false);
        }

        // checking if a round has ended and calling the function that modifies the details
        if (currGame.getPlayersTurn() == gameDetails.getStartGame().getStartingPlayer()) {
            currGame.setRounds(currGame.getRounds() + 1);
            startingRound(gameDetails, deckPlayer1, deckPlayer2, currGame);
        }
    }

    /**
     * Function that places a card on the game table if it's in the right parameters
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void placeCard(final ActionsDetails actionsDetails, final Game currGame,
                          final ArrayNode output) {
        CardDetails card;
        Minion minion = new Minion();
        int cardIndex = actionsDetails.getHandIdx();

        // setting the row to 0 for now
        int row = number0;

        // getting the current player
        Player currPlayer = currGame.getPlayer1();

        if (currGame.getPlayersTurn() == number2) {
            currPlayer = currGame.getPlayer2();
        }

        // checking if the players hand is empty and if the index of the card is not too high
        if (!currPlayer.getPlayersHand().isEmpty()
                && cardIndex < currPlayer.getPlayersHand().size()) {

            card = currPlayer.getPlayersHand().get(cardIndex);

            // getting the row on which the card should be placed
            if (currGame.getPlayersTurn() == number1) {
                if (minion.getMinionCardsFrontRow().contains(card.getName())) {
                    row = number2;
                } else if (minion.getMinionCardsBackRow().contains(card.getName())) {
                    row = number3;
                }
            } else {
                if (minion.getMinionCardsFrontRow().contains(card.getName())) {
                    row = number1;
                }
            }

            // checking each error and printing to the output the expected message
            VerifyErrors verifyErrors = new VerifyErrors();
            int check = verifyErrors.verifyErrorsPlaceCard(currGame, card, cardIndex,
                    currPlayer, row, output);

            if (check == 0) {
                return;
            }

            // changing the mana since the card is placed on the table
            currPlayer.setMana(currPlayer.getMana() - card.getMana());

            // adding the card to the table and deleting it from the players hand
            currGame.addCardToGameTable(card, row);
            currPlayer.getPlayersHand().remove(card);
        }
    }

    /**
     * Function that writes in the output the cards that a player has in his hands
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void getCardsInHand(final ActionsDetails actionsDetails, final Game currGame,
                               final ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();
        ArrayList<CardDetails> playersHand;

        if (playerIndex == number1) {
            playersHand = currGame.getPlayer1().getPlayersHand();
        } else {
            playersHand = currGame.getPlayer2().getPlayersHand();
        }

        OutputGetCardsInHand outputGetCardsInHand = new OutputGetCardsInHand();
        outputGetCardsInHand.outputGetCardsInHand(playerIndex, playersHand, output);
    }

    /**
     * Function that writes in the output the mana a player has
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void getPlayerMana(final ActionsDetails actionsDetails, final Game currGame,
                              final ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();
        int playerMana;

        if (playerIndex == number1) {
            playerMana = currGame.getPlayer1().getMana();
        } else {
            playerMana = currGame.getPlayer2().getMana();
        }

        OutputGetPlayerMana outputGetPlayerMana = new OutputGetPlayerMana();
        outputGetPlayerMana.outputGetPlayerMana(playerIndex, playerMana, output);
    }

    /**
     * Function that writes in the output the cards that are on the table
     *
     * @param currGame the data for the game that's playing at the moment
     * @param output   for writing in the output format
     */
    public void getCardsOnTable(final Game currGame, final ArrayNode output) {
        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        OutputGetCardsOnTable outputGetCardsOnTable = new OutputGetCardsOnTable();
        outputGetCardsOnTable.outputGetCardsOnTable(gameTable, output);
    }

    /**
     * Function that writes in the output the environment cards that a player has
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void getEnvironmentCardsInHand(final ActionsDetails actionsDetails, final Game currGame,
                                          final ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();
        ArrayList<CardDetails> playersHand;

        if (playerIndex == number1) {
            playersHand = currGame.getPlayer1().getPlayersHand();
        } else {
            playersHand = currGame.getPlayer2().getPlayersHand();
        }

        ArrayList<CardDetails> environmentCards = new ArrayList<>();
        Environment environment = new Environment();

        // creating an arraylist of the cards that are environment type
        for (CardDetails card : playersHand) {
            if (environment.getEnvironmentCards().contains(card.getName())) {
                environmentCards.add(card);
            }
        }

        OutputGetEnvironmentCardsInHand outputGetEnvironmentCardsInHand =
                new OutputGetEnvironmentCardsInHand();
        outputGetEnvironmentCardsInHand.outputGetEnvironmentCardsInHand(playerIndex,
                environmentCards, output);
    }

    /**
     * Function that uses the ability of an environment card and deletes it from the players hand
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void useEnvironmentCard(final ActionsDetails actionsDetails, final Game currGame,
                                   final ArrayNode output) {
        int cardIndex = actionsDetails.getHandIdx();
        int affectedRow = actionsDetails.getAffectedRow();

        CardDetails card;

        // getting the current player
        Player currPlayer = currGame.getPlayer1();

        if (currGame.getPlayersTurn() == number2) {
            currPlayer = currGame.getPlayer2();
        }

        // checking if the player has any cards in his hand and the index of the card
        if (!currPlayer.getPlayersHand().isEmpty()
                && cardIndex < currPlayer.getPlayersHand().size()) {

            card = currPlayer.getPlayersHand().get(cardIndex);

            // verifying the errors
            VerifyErrors verifyErrors = new VerifyErrors();
            int check = verifyErrors.verifyErrorsUseEnvironmentCard(currGame, card,
                    currPlayer, cardIndex, affectedRow, output);

            if (check == 0) {
                return;
            }

            // using the card
            Environment environment = new Environment();
            environment.useEnvironment(card, currGame, affectedRow);

            // changing the mana of the player and deleting the card from his hand
            currPlayer.setMana(currPlayer.getMana() - card.getMana());
            currPlayer.getPlayersHand().remove(card);
        }
    }

    /**
     * Function that writes in the output the card that is located at a certain position
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void getCardAtPosition(final ActionsDetails actionsDetails, final Game currGame,
                                  final ArrayNode output) {
        int coordinateX = actionsDetails.getX();
        int coordinateY = actionsDetails.getY();

        OutputGetCardAtPosition outputGetCardAtPosition = new OutputGetCardAtPosition();
        outputGetCardAtPosition.outputGetCardAtPosition(coordinateX, coordinateY, currGame, output);
    }

    /**
     * Function that writes in the output the frozen cards of the players
     *
     * @param currGame the data for the game that's playing at the moment
     * @param output   for writing in the output format
     */
    public void getFrozenCardsOnTable(final Game currGame, final ArrayNode output) {
        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        OutputGetFrozenCardsOnTable outputGetFrozenCardsOnTable = new OutputGetFrozenCardsOnTable();
        outputGetFrozenCardsOnTable.outputGetFrozenCardsOnTable(gameTable, output);
    }

    /**
     * Function that uses a minion cards attack damage
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void cardUsesAttack(final ActionsDetails actionsDetails, final Game currGame,
                               final ArrayNode output) {
        int playersTurn = currGame.getPlayersTurn();

        // getting the coordinates of the card
        CoordinatesDetails coordinatesDetailsAttacked = actionsDetails.getCardAttacked();
        CoordinatesDetails coordinatesDetailsAttacker = actionsDetails.getCardAttacker();

        int coordinateAttackedX = coordinatesDetailsAttacked.getX();
        int coordinateAttackedY = coordinatesDetailsAttacked.getY();

        int coordinateAttackerX = coordinatesDetailsAttacker.getX();
        int coordinateAttackerY = coordinatesDetailsAttacker.getY();

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        // if the coordinates are in the right parameters
        if (coordinateAttackedX < gameTable.size() && coordinateAttackerX < gameTable.size()
                && coordinateAttackedY < gameTable.get(coordinateAttackedX).size()
                && coordinateAttackerY < gameTable.get(coordinateAttackerX).size()) {

            // getting the cards
            CardDetails cardAttacked = gameTable.get(coordinateAttackedX).get(coordinateAttackedY);
            CardDetails cardAttacker = gameTable.get(coordinateAttackerX).get(coordinateAttackerY);

            // verifying the errors
            VerifyErrors verifyErrors = new VerifyErrors();
            int check = verifyErrors.verifyErrorsCardUsesAttack(currGame, coordinateAttackedX,
                    coordinatesDetailsAttacked, coordinatesDetailsAttacker, cardAttacker,
                    cardAttacked, "cardUsesAttack", output);

            if (check == 0) {
                return;
            }

            // changing the health of the attacked card
            cardAttacked.setHealth(cardAttacked.getHealth() - cardAttacker.getAttackDamage());

            // if the cards health is too low we delete it from the game table
            if (cardAttacked.getHealth() < number1) {

                if (playersTurn == number1) {
                    if (coordinateAttackedX == number0) {
                        gameTable.get(number0).remove(cardAttacked);
                    } else {
                        gameTable.get(number1).remove(cardAttacked);
                    }
                } else {
                    if (coordinateAttackedX == number2) {
                        gameTable.get(number2).remove(cardAttacked);
                    } else {
                        gameTable.get(number3).remove(cardAttacked);
                    }
                }
            }

            // to remember if the card has attacked
            cardAttacker.setHasAttacked(true);
        }
    }

    /**
     * Function that uses a minion cards special ability
     *
     * @param actionsDetails for getting the data of the command
     * @param currGame       the data for the game that's playing at the moment
     * @param output         for writing in the output format
     */
    public void cardUsesAbility(final ActionsDetails actionsDetails, final Game currGame,
                                final ArrayNode output) {

        CoordinatesDetails coordinatesDetailsAttacked = actionsDetails.getCardAttacked();
        CoordinatesDetails coordinatesDetailsAttacker = actionsDetails.getCardAttacker();

        int coordinateAttackedX = coordinatesDetailsAttacked.getX();
        int coordinateAttackedY = coordinatesDetailsAttacked.getY();

        int coordinateAttackerX = coordinatesDetailsAttacker.getX();
        int coordinateAttackerY = coordinatesDetailsAttacker.getY();

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        if (coordinateAttackedX < gameTable.size() && coordinateAttackerX < gameTable.size()
                && coordinateAttackedY < gameTable.get(coordinateAttackedX).size()
                && coordinateAttackerY < gameTable.get(coordinateAttackerX).size()) {

            CardDetails cardAttacked = gameTable.get(coordinateAttackedX).get(coordinateAttackedY);
            CardDetails cardAttacker = gameTable.get(coordinateAttackerX).get(coordinateAttackerY);


            Minion minion = new Minion();

            // verifying the errors
            VerifyErrors verifyErrors = new VerifyErrors();
            int check = verifyErrors.verifyErrorsCardUsesAbility(currGame, coordinateAttackedX,
                    coordinatesDetailsAttacked, coordinatesDetailsAttacker, cardAttacker,
                    cardAttacked, "cardUsesAbility", output);

            if (check == 0) {
                return;
            }

            minion.useAbility(cardAttacker, cardAttacked, currGame, coordinatesDetailsAttacked);

            // to remember if the card has attacked
            cardAttacker.setHasAttacked(true);

        }
    }

    /**
     * Function that uses the attack of the players hero
     * @param actionsDetails for getting the data of the command
     * @param currGame the data for the game that's playing at the moment
     * @param gameDetails contains the data for the game
     * @param statistics contains the data for the statistics
     * @param output for writing in the output format
     */
    public void useAttackHero(final ActionsDetails actionsDetails, final Game currGame,
                              final GameDetails gameDetails, final Statistics statistics,
                              final ArrayNode output) {
        int playersTurn = currGame.getPlayersTurn();

        CoordinatesDetails coordinateCardAttacker = actionsDetails.getCardAttacker();

        int coordinateAttackerX = coordinateCardAttacker.getX();
        int coordinateAttackerY = coordinateCardAttacker.getY();

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        if (coordinateAttackerX < gameTable.size()
                && coordinateAttackerY < gameTable.get(coordinateAttackerX).size()) {

            CardDetails cardAttacker = gameTable.get(coordinateAttackerX).get(coordinateAttackerY);

            // verifying the errors
            VerifyErrors verifyErrors = new VerifyErrors();
            int check = verifyErrors.verifyErrorsUseAttackHero(currGame, coordinateCardAttacker,
                    cardAttacker, output);

            if (check == 0) {
                return;
            }

            if (playersTurn == number1) {
                CardDetails hero = gameDetails.getStartGame().getPlayerTwoHero();

                // changing the hero s health
                hero.setHealth(hero.getHealth() - cardAttacker.getAttackDamage());

                // checking if the hero has died to end the game
                if (hero.getHealth() < number1) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorHeroDied(playersTurn, output, statistics);
                    return;
                }
            } else {
                CardDetails hero = gameDetails.getStartGame().getPlayerOneHero();

                hero.setHealth(hero.getHealth() - cardAttacker.getAttackDamage());

                if (hero.getHealth() < number1) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorHeroDied(playersTurn, output, statistics);
                    return;
                }
            }

            // to remember if the card has attacked
            cardAttacker.setHasAttacked(true);
        }
    }

    /**
     * Function that uses the special ability of the players hero
     * @param actionsDetails for getting the data of the command
     * @param currGame the data for the game that's playing at the moment
     * @param gameDetails contains the data for the game
     * @param output for writing in the output format
     */
    public void useHeroAbility(final ActionsDetails actionsDetails, final Game currGame,
                               final GameDetails gameDetails, final ArrayNode output) {

        int playersTurn = currGame.getPlayersTurn();
        int affectedRow = actionsDetails.getAffectedRow();

        CardDetails hero;

        // getting the current player
        Player currPlayer = currGame.getPlayer1();

        if (currGame.getPlayersTurn() == number2) {
            currPlayer = currGame.getPlayer2();
        }

        if (playersTurn == number1) {
            hero = gameDetails.getStartGame().getPlayerOneHero();
        } else {
            hero = gameDetails.getStartGame().getPlayerTwoHero();
        }

        // verifying the errors
        VerifyErrors verifyErrors =  new VerifyErrors();
        int check = verifyErrors.verifyErrorsUseHeroAbility(currGame, hero, currPlayer,
                affectedRow, output);

        if (check == 0) {
            return;
        }

        Hero use = new Hero();

        // using the hero's ability
        use.useAbility(hero, currGame, affectedRow);
        hero.setHasAttacked(true);

        // changing the players mana
        currPlayer.setMana(currPlayer.getMana() - hero.getMana());
    }

    /**
     * Function that writes in the output the number of games played so far
     * @param statistics contains the data for the statistics
     * @param output for writing in the output format
     */
    public void getTotalGamesPlayed(final Statistics statistics, final ArrayNode output) {
        OutputGetTotalGamesPlayed outputGetTotalGamesPlayed = new OutputGetTotalGamesPlayed();
        outputGetTotalGamesPlayed.outputGetTotalGamesPlayed(statistics, output);
    }

    /**
     * Function that writes in the output the number of games the first player has won
     * @param statistics contains the data for the statistics
     * @param output for writing in the output format
     */
    public void getPlayerOneWins(final Statistics statistics, final ArrayNode output) {
        OutputPlayerOneWins outputPlayerOneWins = new OutputPlayerOneWins();
        outputPlayerOneWins.outputPlayerOneWins(statistics, output);
    }

    /**
     * Function that writes in the output the number of games the second player has won
     * @param statistics contains the data for the statistics
     * @param output for writing in the output format
     */
    public void getPlayerTwoWins(final Statistics statistics, final ArrayNode output) {
        OutputPlayerTwoWins outputPlayerTwoWins = new OutputPlayerTwoWins();
        outputPlayerTwoWins.outputPlayerTwoWins(statistics, output);
    }

    /**
     * Function that goes through the row of the game table to set their characteristics
     * @param gameTable contains the table
     * @param rowIndex specifies the index of the row we want to change
     */
    public void setRowToNormal(final ArrayList<ArrayList<CardDetails>> gameTable,
                               final int rowIndex) {
        for (CardDetails card : gameTable.get(rowIndex)) {
            card.setFrozen(false);
            card.setHasAttacked(false);
        }
    }
}
