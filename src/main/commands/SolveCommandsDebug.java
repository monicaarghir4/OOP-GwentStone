package main.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Coordinates;
import fileio.Input;
import main.game.Game;
import main.game.Statistics;
import main.gameDetails.card.environment.Environment;
import main.gameDetails.card.hero.Hero;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.*;
import main.gameDetails.players.Player;
import main.output.*;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SolveCommandsDebug {
    public void CheckCommands (String file, Input inputOriginal, InputDetails inputDetails, ArrayNode output) {
        ArrayList<GameDetails> gamesDetails = inputDetails.getGames();
        Statistics statistics = new Statistics();

        for (GameDetails gameDetails : gamesDetails) {
            statistics.setTotalGamesPlayed(statistics.getTotalGamesPlayed() + 1);

            inputDetails = new InputDetails(inputOriginal);
            ArrayList<ActionsDetails> actionsDetails = gameDetails.getActions();

            ArrayList<CardDetails> deckPlayer1, deckPlayer2;
            int deckPlayer1Index = gameDetails.getStartGame().getPlayerOneDeckIdx();
            int deckPlayer2Index = gameDetails.getStartGame().getPlayerTwoDeckIdx();

            deckPlayer1 = inputDetails.getPlayerOneDecks().getDecks().get(deckPlayer1Index);
            deckPlayer2 = inputDetails.getPlayerTwoDecks().getDecks().get(deckPlayer2Index);

            Collections.shuffle(deckPlayer1, new Random(gameDetails.getStartGame().getShuffleSeed()));
            Collections.shuffle(deckPlayer2, new Random(gameDetails.getStartGame().getShuffleSeed()));

            Game currGame = new Game();
            currGame.setRounds(1);

            startingRound(gameDetails, deckPlayer1, deckPlayer2, currGame);

            for (ActionsDetails actionDetails : actionsDetails) {
                String command = new String(actionDetails.getCommand());

                switch (command) {
                    case "getCardsInHand":
                        getCardsInHand(actionDetails, currGame, output);
                        break;

                    case "getPlayerDeck":
                        getPlayerDeck(inputDetails, gameDetails, actionDetails, output);
                        break;

                    case "getCardsOnTable":
                        getCardsOnTable(currGame, output);
                        break;

                    case "getPlayerTurn":
                        getPlayerTurn(currGame, output);
                        break;

                    case "getPlayerHero":
                        getPlayerHero(gameDetails, actionDetails, output);
                        break;

                    case "getCardAtPosition":
                        getCardAtPosition(actionDetails, currGame, output);
                        break;

                    case "getPlayerMana":
                        getPlayerMana(actionDetails, currGame, output);
                        break;

                    case "getEnvironmentCardsInHand":
                        getEnvironmentCardsInHand(actionDetails, currGame, output);
                        break;

                    case "getFrozenCardsOnTable":
                        getFrozenCardsOnTable(currGame, output);
                        break;

                    case "endPlayerTurn":
                        endPlayerTurn(gameDetails, currGame, deckPlayer1, deckPlayer2, output);
                        break;

                    case "placeCard":
                        placeCard(actionDetails, currGame, output);
                        break;

                    case "useEnvironmentCard":
                        useEnvironmentCard(actionDetails, currGame, output);
                        break;

                    case "cardUsesAttack":
                        cardUsesAttack(actionDetails, currGame, output);
                        break;

                    case "cardUsesAbility":
                        cardUsesAbility(actionDetails, currGame, output);
                        break;

                    case "useAttackHero":
                        useAttackHero(actionDetails, currGame, gameDetails, output, statistics);
                        break;

                    case "useHeroAbility":
                        useHeroAbility(actionDetails, currGame, gameDetails, output);
                        break;

                    case "getTotalGamesPlayed":
                        OutputGetTotalGamesPlayed outputGetTotalGamesPlayed = new OutputGetTotalGamesPlayed();
                        outputGetTotalGamesPlayed.outputGetTotalGamesPlayed(statistics, output);
                        break;

                    case "getPlayerOneWins":
                        OutputPlayerOneWins outputPlayerOneWins = new OutputPlayerOneWins();
                        outputPlayerOneWins.outputPlayerOneWins(statistics, output);
                        break;

                    case "getPlayerTwoWins":
                        OutputPlayerTwoWins outputPlayerTwoWins = new OutputPlayerTwoWins();
                        outputPlayerTwoWins.outputPlayerTwoWins(statistics, output);
                        break;
                }
            }
        }
    }

    public void startingRound(GameDetails gameDetails, ArrayList<CardDetails> deckPlayer1, ArrayList<CardDetails> deckPlayer2, Game currGame) {
        Player player1 = currGame.getPlayer1();
        Player player2 = currGame.getPlayer2();

        if (!deckPlayer1.isEmpty()) {
            player1.getPlayersHand().add(deckPlayer1.remove(0));
        }

        if (!deckPlayer2.isEmpty()) {
            player2.getPlayersHand().add(deckPlayer2.remove(0));
        }

        currGame.setPlayersTurn(gameDetails.getStartGame().getStartingPlayer());

        //TODO
        // de la runda 10 incolo adaugare mana altfel

        if (currGame.getRounds() == 1) {
            player1.setMana(1);
            player2.setMana(1);
        } else {
            player1.setMana(player1.getMana() + currGame.getRounds());
            player2.setMana(player2.getMana() + currGame.getRounds());
        }
    }

    public void getPlayerDeck (InputDetails inputDetails, GameDetails gameDetails, ActionsDetails actionsDetails, ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();

        if (playerIndex == 1) {
            DecksDetails decksDetails = inputDetails.getPlayerOneDecks();
            int deckIndex = gameDetails.getStartGame().getPlayerOneDeckIdx();

            OutputGetPlayerDeck outputGetPlayerDeck = new OutputGetPlayerDeck();
            outputGetPlayerDeck.outputGetPlayerDeck(decksDetails.getDecks().get(deckIndex), playerIndex, output);
        } else {
            DecksDetails decksDetails = inputDetails.getPlayerTwoDecks();
            int deckIndex = gameDetails.getStartGame().getPlayerTwoDeckIdx();

            OutputGetPlayerDeck outputGetPlayerDeck = new OutputGetPlayerDeck();
            outputGetPlayerDeck.outputGetPlayerDeck(decksDetails.getDecks().get(deckIndex), playerIndex, output);
        }
    }

    public void getPlayerHero (GameDetails gameDetails, ActionsDetails actionsDetails, ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();

        if (playerIndex == 1) {
            OutputGetPlayerHero outputGetPlayerHero = new OutputGetPlayerHero();
            outputGetPlayerHero.outputGetPlayerHero(gameDetails.getStartGame().getPlayerOneHero(), playerIndex, output);
        } else {
            OutputGetPlayerHero outputGetPlayerHero = new OutputGetPlayerHero();
            outputGetPlayerHero.outputGetPlayerHero(gameDetails.getStartGame().getPlayerTwoHero(), playerIndex, output);
        }
    }

    public void getPlayerTurn (Game currGame, ArrayNode output) {
        OutputGetPlayerTurn outputGetPlayerTurn = new OutputGetPlayerTurn();
        outputGetPlayerTurn.outputGetPlayerTurn(currGame.getPlayersTurn(), output);
    }

    public void endPlayerTurn(GameDetails gameDetails, Game currGame, ArrayList<CardDetails> deckPlayer1, ArrayList<CardDetails> deckPlayer2, ArrayNode output) {
        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        if (currGame.getPlayersTurn() == 1) {
            for (CardDetails card : gameTable.get(2)) {
                card.setFrozen(false);
                card.setHasAttacked(false);
            }

            for (CardDetails card : gameTable.get(3)) {
                card.setFrozen(false);
                card.setHasAttacked(false);
            }

            currGame.setPlayersTurn(2);
            gameDetails.getStartGame().getPlayerOneHero().setHasAttacked(false);
        } else {
            for (CardDetails card : gameTable.get(0)) {
                card.setFrozen(false);
                card.setHasAttacked(false);
            }

            for (CardDetails card : gameTable.get(1)) {
                card.setFrozen(false);
                card.setHasAttacked(false);
            }

            currGame.setPlayersTurn(1);
            gameDetails.getStartGame().getPlayerTwoHero().setHasAttacked(false);
        }

        if (currGame.getPlayersTurn() == gameDetails.getStartGame().getStartingPlayer()) {
            currGame.setRounds(currGame.getRounds() + 1);
            startingRound(gameDetails, deckPlayer1, deckPlayer2, currGame);
        }
    }

    public void placeCard(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        CardDetails card;
        int row = 0;
        Minion minion = new Minion();
        Environment environment = new Environment();

        if (currGame.getPlayersTurn() == 1) {
            if (!currGame.getPlayer1().getPlayersHand().isEmpty() && actionsDetails.getHandIdx() < currGame.getPlayer1().getPlayersHand().size()) {
                card = currGame.getPlayer1().getPlayersHand().get(actionsDetails.getHandIdx());

                if (environment.getEnvironmentCards().contains(card.getName())) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorPlaceCard(actionsDetails.getHandIdx(), "placeCard", "Cannot place environment card on table.", output);
                    return;
                }

                if (card.getMana() > currGame.getPlayer1().getMana()) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorPlaceCard(actionsDetails.getHandIdx(), "placeCard", "Not enough mana to place card on table.", output);
                    return;
                }

                if (minion.getMinionCardsFrontRow().contains(card.getName())) {
                    row = 2;
                } else if (minion.getMinionCardsBackRow().contains(card.getName())) {
                    row = 3;
                }

                if (currGame.getGameTable().get(row).size() >= 5) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorPlaceCard(actionsDetails.getHandIdx(), "placeCard", "Cannot place card on table since row is full.", output);
                    return;
                }

                currGame.getPlayer1().setMana(currGame.getPlayer1().getMana() - card.getMana());

                currGame.addCardToGameTable(card, row);
                currGame.getPlayer1().getPlayersHand().remove(card);
            }
        } else {
            if (!currGame.getPlayer2().getPlayersHand().isEmpty() && actionsDetails.getHandIdx() < currGame.getPlayer2().getPlayersHand().size()) {
                card = currGame.getPlayer2().getPlayersHand().get(actionsDetails.getHandIdx());

                if (environment.getEnvironmentCards().contains(card.getName())) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorPlaceCard(actionsDetails.getHandIdx(), "placeCard", "Cannot place environment card on table.", output);
                    return;
                }

                if (card.getMana() > currGame.getPlayer2().getMana()) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorPlaceCard(actionsDetails.getHandIdx(), "placeCard", "Not enough mana to place card on table.", output);
                    return;
                }

                if (minion.getMinionCardsFrontRow().contains(card.getName())) {
                    row = 1;
                }

                if (currGame.getGameTable().get(row).size() >= 5) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorPlaceCard(actionsDetails.getHandIdx(), "placeCard", "Cannot place card on table since row is full.", output);
                    return;
                }

                currGame.getPlayer2().setMana(currGame.getPlayer2().getMana() - card.getMana());

                currGame.addCardToGameTable(card, row);
                currGame.getPlayer2().getPlayersHand().remove(card);
            }
        }
    }

    public void getCardsInHand(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();
        ArrayList<CardDetails> playersHand;

        if (playerIndex == 1) {
            playersHand = currGame.getPlayer1().getPlayersHand();
        } else {
            playersHand = currGame.getPlayer2().getPlayersHand();
        }

        OutputGetCardsInHand outputGetCardsInHand = new OutputGetCardsInHand();
        outputGetCardsInHand.outputGetCardsInHand(playerIndex, playersHand, output);
    }

    public void getPlayerMana(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();
        int playerMana;

        if (playerIndex == 1) {
            playerMana = currGame.getPlayer1().getMana();
        } else {
            playerMana = currGame.getPlayer2().getMana();
        }

        OutputGetPlayerMana outputGetPlayerMana = new OutputGetPlayerMana();
        outputGetPlayerMana.outputGetPlayerMana(playerIndex, playerMana, output);
    }

    public void getCardsOnTable(Game currGame, ArrayNode output) {
        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        OutputGetCardsOnTable outputGetCardsOnTable = new OutputGetCardsOnTable();
        outputGetCardsOnTable.outputGetCardsOnTable(gameTable, output);
    }

    public void getEnvironmentCardsInHand(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        int playerIndex = actionsDetails.getPlayerIdx();
        ArrayList<CardDetails> playersHand;
        ArrayList<CardDetails> environmentCards = new ArrayList<>();
        Environment environment = new Environment();

        if (playerIndex == 1) {
            playersHand = currGame.getPlayer1().getPlayersHand();
        } else {
            playersHand = currGame.getPlayer2().getPlayersHand();
        }

        for (CardDetails card : playersHand) {
            if (environment.getEnvironmentCards().contains(card.getName())) {
                environmentCards.add(card);
            }
        }

        OutputGetEnvironmentCardsInHand outputGetEnvironmentCardsInHand = new OutputGetEnvironmentCardsInHand();
        outputGetEnvironmentCardsInHand.outputGetEnvironmentCardsInHand(playerIndex, environmentCards, output);
    }

    public void useEnvironmentCard(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        int playerIndex = currGame.getPlayersTurn();
        int cardIndex = actionsDetails.getHandIdx();
        int affectedRow = actionsDetails.getAffectedRow();

        CardDetails card;

        if (playerIndex == 1) {
            if (!currGame.getPlayer1().getPlayersHand().isEmpty() && cardIndex < currGame.getPlayer1().getPlayersHand().size()) {
                card = currGame.getPlayer1().getPlayersHand().get(cardIndex);

                Environment environment = new Environment();

                if (affectedRow == 0 && card.getName().compareTo("Heart Hound") == 0) {
                    if (currGame.getGameTable().get(3).size() == 5) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Cannot steal enemy card since the player's row is full.", output);
                        return;
                    }
                } else if (card.getName().compareTo("Heart Hound") == 0){
                    if (currGame.getGameTable().get(2).size() == 5) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Cannot steal enemy card since the player's row is full.", output);
                        return;
                    }
                }

                if (!environment.getEnvironmentCards().contains(card.getName())) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Chosen card is not of type environment.", output);
                    return;
                }

                if (card.getMana() > currGame.getPlayer1().getMana()) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Not enough mana to use environment card.", output);
                    return;
                }

                if (affectedRow == 2 || affectedRow == 3) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Chosen row does not belong to the enemy.", output);
                    return;
                }

                environment.useEnvironment(card, currGame, affectedRow);

                currGame.getPlayer1().setMana(currGame.getPlayer1().getMana() - card.getMana());
                currGame.getPlayer1().getPlayersHand().remove(card);
            }
        } else {
            if (!currGame.getPlayer2().getPlayersHand().isEmpty() && cardIndex < currGame.getPlayer2().getPlayersHand().size()) {
                card = currGame.getPlayer2().getPlayersHand().get(cardIndex);

                Environment environment = new Environment();

                if (affectedRow == 2 && card.getName().compareTo("Heart Hound") == 0) {
                    if (currGame.getGameTable().get(1).size() == 5) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Cannot steal enemy card since the player's row is full.", output);
                        return;
                    }
                } else if (card.getName().compareTo("Heart Hound") == 0){
                    if (currGame.getGameTable().get(0).size() == 5) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Cannot steal enemy card since the player's row is full.", output);
                        return;
                    }
                }

                if (!environment.getEnvironmentCards().contains(card.getName())) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Chosen card is not of type environment.", output);
                    return;
                }

                if (card.getMana() > currGame.getPlayer2().getMana()) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Not enough mana to use environment card.", output);
                    return;
                }

                if (affectedRow == 0 || affectedRow == 1) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseEnvironmentCard(affectedRow, cardIndex, "useEnvironmentCard", "Chosen row does not belong to the enemy.", output);
                    return;
                }

                environment.useEnvironment(card, currGame, affectedRow);

                currGame.getPlayer2().setMana(currGame.getPlayer2().getMana() - card.getMana());
                currGame.getPlayer2().getPlayersHand().remove(card);
            }
        }
    }

    public void getCardAtPosition(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        int coordX = actionsDetails.getX();
        int coordY = actionsDetails.getY();

        OutputGetCardAtPosition outputGetCardAtPosition = new OutputGetCardAtPosition();
        outputGetCardAtPosition.outputGetCardAtPosition(coordX, coordY, currGame, output);
    }

    public void getFrozenCardsOnTable(Game currGame, ArrayNode output) {
        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        OutputGetFrozenCardsOnTable outputGetFrozenCardsOnTable = new OutputGetFrozenCardsOnTable();
        outputGetFrozenCardsOnTable.outputGetFrozenCardsOnTable(gameTable, output);
    }

    public void cardUsesAttack(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        int playersTurn = currGame.getPlayersTurn();

        CoordinatesDetails coordinatesDetailsAttacked = actionsDetails.getCardAttacked();
        CoordinatesDetails coordinatesDetailsAttacker = actionsDetails.getCardAttacker();

        int coordXcardAttacked = coordinatesDetailsAttacked.getX();
        int coordYcardAttacked = coordinatesDetailsAttacked.getY();

        int coordXcardAttacker = coordinatesDetailsAttacker.getX();
        int coordYcardAttacker = coordinatesDetailsAttacker.getY();

        if (playersTurn == 1) {
            if (coordXcardAttacked == 2 || coordXcardAttacked == 3) {
                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacked card does not belong to the enemy.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return;
            }
        } else {
            if (coordXcardAttacked == 0 || coordXcardAttacked == 1) {
                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacked card does not belong to the enemy.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return;
            }
        }

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        if (coordXcardAttacked < gameTable.size() && coordXcardAttacker < gameTable.size() &&
                coordYcardAttacked < gameTable.get(coordXcardAttacked).size() &&
                coordYcardAttacker < gameTable.get(coordXcardAttacker).size()) {

            CardDetails cardAttacked = gameTable.get(coordXcardAttacked).get(coordYcardAttacked);
            CardDetails cardAttacker = gameTable.get(coordXcardAttacker).get(coordYcardAttacker);

            if (cardAttacker.isHasAttacked()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacker card has already attacked this turn.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return;
            }

            if (cardAttacker.isFrozen()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacker card is frozen.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return;
            }

            Minion minion = new Minion();

            if (!minion.getTankCards().contains(cardAttacked.getName())) {
                if (playersTurn == 1) {
                    for (int column = 0; column < gameTable.get(0).size(); column++) {
                        if (minion.getTankCards().contains(gameTable.get(0).get(column).getName())) {
                            OutputError outputError = new OutputError();
                            outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                            return;
                        }
                    }

                    for (int column = 0; column < gameTable.get(1).size(); column++) {
                        if (minion.getTankCards().contains(gameTable.get(1).get(column).getName())) {
                            OutputError outputError = new OutputError();
                            outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                            return;
                        }
                    }
                } else {
                    for (int column = 0; column < gameTable.get(2).size(); column++) {
                        if (minion.getTankCards().contains(gameTable.get(2).get(column).getName())) {
                            OutputError outputError = new OutputError();
                            outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                            return;
                        }
                    }

                    for (int column = 0; column < gameTable.get(3).size(); column++) {
                        if (minion.getTankCards().contains(gameTable.get(3).get(column).getName())) {
                            OutputError outputError = new OutputError();
                            outputError.outputErrorCardUsesAttackAndAbility("cardUsesAttack", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                            return;
                        }
                    }
                }
            }

            cardAttacked.setHealth(cardAttacked.getHealth() - cardAttacker.getAttackDamage());

            if (cardAttacked.getHealth() < 1) {
                if (playersTurn == 1) {
                    if (coordXcardAttacked == 0) {
                        gameTable.get(0).remove(cardAttacked);
                    } else {
                        gameTable.get(1).remove(cardAttacked);
                    }
                } else {
                    if (coordXcardAttacked == 2) {
                        gameTable.get(2).remove(cardAttacked);
                    } else {
                        gameTable.get(3).remove(cardAttacked);
                    }
                }
            }

            cardAttacker.setHasAttacked(true);
        }
    }

    public void cardUsesAbility(ActionsDetails actionsDetails, Game currGame, ArrayNode output) {
        int playersTurn = currGame.getPlayersTurn();

        CoordinatesDetails coordinatesDetailsAttacked = actionsDetails.getCardAttacked();
        CoordinatesDetails coordinatesDetailsAttacker = actionsDetails.getCardAttacker();

        int coordXcardAttacked = coordinatesDetailsAttacked.getX();
        int coordYcardAttacked = coordinatesDetailsAttacked.getY();

        int coordXcardAttacker = coordinatesDetailsAttacker.getX();
        int coordYcardAttacker = coordinatesDetailsAttacker.getY();

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        if (coordXcardAttacked < gameTable.size() && coordXcardAttacker < gameTable.size() &&
                coordYcardAttacked < gameTable.get(coordXcardAttacked).size() &&
                coordYcardAttacker < gameTable.get(coordXcardAttacker).size()) {

            CardDetails cardAttacked = gameTable.get(coordXcardAttacked).get(coordYcardAttacked);
            CardDetails cardAttacker = gameTable.get(coordXcardAttacker).get(coordYcardAttacker);

            if (cardAttacker.isFrozen()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacker card is frozen.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return;
            }

            if (cardAttacker.isHasAttacked()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacker card has already attacked this turn.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                return;
            }

            if (cardAttacker.getName().compareTo("Disciple") == 0) {
                if (playersTurn == 1) {
                    if (coordXcardAttacked == 0 || coordXcardAttacked == 1) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card does not belong to the current player.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                        return;
                    }
                } else {
                    if (coordXcardAttacked == 2 || coordXcardAttacked == 3) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card does not belong to the current player.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                        return;
                    }
                }
            } else {
                if (playersTurn == 1) {
                    if (coordXcardAttacked == 2 || coordXcardAttacked == 3) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card does not belong to the enemy.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                        return;
                    }
                } else {
                    if (coordXcardAttacked == 0 || coordXcardAttacked == 1) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card does not belong to the enemy.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                        return;
                    }
                }

                Minion minion = new Minion();

                if (!minion.getTankCards().contains(cardAttacked.getName()) && cardAttacker.getName().compareTo("Disciple") != 0) {
                    if (playersTurn == 1) {
                        for (int column = 0; column < gameTable.get(0).size(); column++) {
                            if (minion.getTankCards().contains(gameTable.get(0).get(column).getName())) {
                                OutputError outputError = new OutputError();
                                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                                return;
                            }
                        }

                        for (int column = 0; column < gameTable.get(1).size(); column++) {
                            if (minion.getTankCards().contains(gameTable.get(1).get(column).getName())) {
                                OutputError outputError = new OutputError();
                                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                                return;
                            }
                        }
                    } else {
                        for (int column = 0; column < gameTable.get(2).size(); column++) {
                            if (minion.getTankCards().contains(gameTable.get(2).get(column).getName())) {
                                OutputError outputError = new OutputError();
                                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                                return;
                            }
                        }

                        for (int column = 0; column < gameTable.get(3).size(); column++) {
                            if (minion.getTankCards().contains(gameTable.get(3).get(column).getName())) {
                                OutputError outputError = new OutputError();
                                outputError.outputErrorCardUsesAttackAndAbility("cardUsesAbility", "Attacked card is not of type 'Tank'.", coordinatesDetailsAttacked, coordinatesDetailsAttacker, output);
                                return;
                            }
                        }
                    }
                }
            }

            Minion minion = new Minion();

            minion.useAbility(cardAttacker, cardAttacked, currGame, coordinatesDetailsAttacked);

            cardAttacker.setHasAttacked(true);
        }
    }

    public void useAttackHero(ActionsDetails actionsDetails, Game currGame, GameDetails gameDetails, ArrayNode output, Statistics statistics) {
        int playersTurn = currGame.getPlayersTurn();

        CoordinatesDetails coordCardAttacker = actionsDetails.getCardAttacker();

        int coordAttackerX = coordCardAttacker.getX();
        int coordAttackerY = coordCardAttacker.getY();

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();

        if (coordAttackerX < gameTable.size() && coordAttackerY < gameTable.get(coordAttackerX).size()) {
            CardDetails cardAttacker = gameTable.get(coordAttackerX).get(coordAttackerY);

            if (cardAttacker.isFrozen()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseAttackHero("useAttackHero", "Attacker card is frozen.", coordCardAttacker, output);
                return;
            }

            if (cardAttacker.isHasAttacked()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseAttackHero("useAttackHero", "Attacker card has already attacked this turn.", coordCardAttacker, output);
                return;
            }

            Minion minion = new Minion();

            if (playersTurn == 1) {
                for (CardDetails card : currGame.getGameTable().get(0)) {
                    if (minion.getTankCards().contains(card.getName())) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseAttackHero("useAttackHero", "Attacked card is not of type 'Tank'.", coordCardAttacker, output);
                        return;
                    }
                }

                for (CardDetails card : currGame.getGameTable().get(1)) {
                    if (minion.getTankCards().contains(card.getName())) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseAttackHero("useAttackHero", "Attacked card is not of type 'Tank'.", coordCardAttacker, output);
                        return;
                    }
                }
            } else {
                for (CardDetails card : currGame.getGameTable().get(2)) {
                    if (minion.getTankCards().contains(card.getName())) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseAttackHero("useAttackHero", "Attacked card is not of type 'Tank'.", coordCardAttacker, output);
                        return;
                    }
                }

                for (CardDetails card : currGame.getGameTable().get(3)) {
                    if (minion.getTankCards().contains(card.getName())) {
                        OutputError outputError = new OutputError();
                        outputError.outputErrorUseAttackHero("useAttackHero", "Attacked card is not of type 'Tank'.", coordCardAttacker, output);
                        return;
                    }
                }
            }

            if (playersTurn == 1) {
                CardDetails hero = gameDetails.getStartGame().getPlayerTwoHero();

                hero.setHealth(hero.getHealth() - cardAttacker.getAttackDamage());

                if (hero.getHealth() < 1) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorHeroDied(playersTurn, output, statistics);
                    return;
                }
            } else {
                CardDetails hero = gameDetails.getStartGame().getPlayerOneHero();

                hero.setHealth(hero.getHealth() - cardAttacker.getAttackDamage());

                if (hero.getHealth() < 1) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorHeroDied(playersTurn, output, statistics);
                    return;
                }
            }

            cardAttacker.setHasAttacked(true);
        }
    }

    public void useHeroAbility(ActionsDetails actionsDetails, Game currGame, GameDetails gameDetails, ArrayNode output) {
        int playersTurn = currGame.getPlayersTurn();

        int affectedRow = actionsDetails.getAffectedRow();

        ArrayList<ArrayList<CardDetails>> gameTable = currGame.getGameTable();
        CardDetails hero;

        if (playersTurn == 1) {
            hero = gameDetails.getStartGame().getPlayerOneHero();
            if (hero.getMana() > currGame.getPlayer1().getMana()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Not enough mana to use hero's ability.", output);
                return;
            }

            if (hero.isHasAttacked()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Hero has already attacked this turn.", output);
                return;
            }

            if (hero.getName().compareTo("Lord Royce") == 0 || hero.getName().compareTo("Empress Thorina") == 0) {
                if (affectedRow == 2 || affectedRow == 3) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Selected row does not belong to the enemy.", output);
                    return;
                }
            } else {
                if (affectedRow == 0 || affectedRow == 1) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Selected row does not belong to the current player.", output);
                    return;
                }
            }

            Hero use = new Hero();

            use.useAbility(hero, currGame, affectedRow);
            hero.setHasAttacked(true);

            currGame.getPlayer1().setMana(currGame.getPlayer1().getMana() - hero.getMana());
        } else {
            hero = gameDetails.getStartGame().getPlayerTwoHero();
            if (hero.getMana() > currGame.getPlayer2().getMana()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Not enough mana to use hero's ability.", output);
                return;
            }

            if (hero.isHasAttacked()) {
                OutputError outputError = new OutputError();
                outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Hero has already attacked this turn.", output);
                return;
            }

            if (hero.getName().compareTo("Lord Royce") == 0 || hero.getName().compareTo("Empress Thorina") == 0) {
                if (affectedRow == 0 || affectedRow == 1) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Selected row does not belong to the enemy.", output);
                    return;
                }
            } else {
                if (affectedRow == 2 || affectedRow == 3) {
                    OutputError outputError = new OutputError();
                    outputError.outputErrorUseHeroAbility("useHeroAbility", affectedRow, "Selected row does not belong to the current player.", output);
                    return;
                }
            }

            Hero use = new Hero();

            use.useAbility(hero, currGame, affectedRow);
            hero.setHasAttacked(true);

            currGame.getPlayer2().setMana(currGame.getPlayer2().getMana() - hero.getMana());
        }
    }
}
