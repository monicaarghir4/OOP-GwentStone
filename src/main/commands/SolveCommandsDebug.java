package main.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import main.game.Game;
import main.gameDetails.card.environment.Environment;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.*;
import main.gameDetails.players.Player;
import main.output.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SolveCommandsDebug {
    public void CheckCommands (String file, InputDetails inputDetails, ArrayNode output) {
        ArrayList<GameDetails> gamesDetails = inputDetails.getGames();

        for (GameDetails gameDetails : gamesDetails) {
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
                        //TODO
                        break;

                    case "getPlayerMana":
                        getPlayerMana(actionDetails, currGame, output);
                        break;

                    case "getEnvironmentCardsInHand":
                        getEnvironmentCardsInHand(actionDetails, currGame, output);
                        break;

                    case "getFrozenCardsOnTable":
                        //TODO
                        break;

                    case "endPlayerTurn":
                        endPlayerTurn(gameDetails, currGame, deckPlayer1, deckPlayer2, output);
                        break;

                    case "placeCard":
                        placeCard(actionDetails, currGame, output);
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
        //TODO
        // unfreeze the frozen cards

        if (currGame.getPlayersTurn() == 1) {
            currGame.setPlayersTurn(2);
        } else {
            currGame.setPlayersTurn(1);
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
                    outputError.outputError(actionsDetails.getHandIdx(), "Cannot place environment card on table.", output);
                    return;
                }

                if (card.getMana() > currGame.getPlayer1().getMana()) {
                    OutputError outputError = new OutputError();
                    outputError.outputError(actionsDetails.getHandIdx(), "Not enough mana to place card on table.", output);
                    return;
                }

                if (minion.getMinionCardsFrontRow().contains(card.getName())) {
                    row = 2;
                } else if (minion.getMinionCardsBackRow().contains(card.getName())) {
                    row = 3;
                }

                if (currGame.getGameTable().get(row).size() >= 5) {
                    OutputError outputError = new OutputError();
                    outputError.outputError(actionsDetails.getHandIdx(), "Cannot place card on table since row is full.", output);
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
                    outputError.outputError(actionsDetails.getHandIdx(), "Cannot place environment card on table.", output);
                    return;
                }

                if (card.getMana() > currGame.getPlayer2().getMana()) {
                    OutputError outputError = new OutputError();
                    outputError.outputError(actionsDetails.getHandIdx(), "Not enough mana to place card on table.", output);
                    return;
                }

                if (minion.getMinionCardsFrontRow().contains(card.getName())) {
                    row = 1;
                }

                if (currGame.getGameTable().get(row).size() >= 5) {
                    OutputError outputError = new OutputError();
                    outputError.outputError(actionsDetails.getHandIdx(), "Cannot place card on table since row is full.", output);
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
}
