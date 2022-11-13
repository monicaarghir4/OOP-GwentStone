package main.debug;

import com.fasterxml.jackson.databind.node.ArrayNode;
import main.gameDetails.details.*;
import main.gameDetails.players.Player;
import main.output.OutputGetPlayerDeck;
import main.output.OutputGetPlayerHero;
import main.output.OutputGetPlayerTurn;

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

            Player player1 = new Player();
            Player player2 = new Player();

            player1.getPlayersHand().add(deckPlayer1.remove(0));
            player2.getPlayersHand().add(deckPlayer2.remove(0));

            for (ActionsDetails actionDetails : actionsDetails) {
                String command = new String(actionDetails.getCommand());

                switch (command) {
                    case "getCardsInHand":
                        //TODO
                        break;

                    case "getPlayerDeck":
                        getPlayerDeck(inputDetails, gameDetails, actionDetails, output);
                        break;

                    case "getCardsOnTable":
                        //TODO
                        break;

                    case "getPlayerTurn":
                        getPlayerTurn(gameDetails, output);
                        break;

                    case "getPlayerHero":
                        getPlayerHero(gameDetails, actionDetails, output);
                        break;

                    case "getCardAtPosition":
                        //TODO
                        break;

                    case "getPlayerMana":
                        //TODO
                        break;

                    case "getEnvironmentCardsInHand":
                        //TODO
                        break;

                    case "getFrozenCardsOnTable":
                        //TODO
                        break;
                }
            }
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

    public void getPlayerTurn (GameDetails gameDetails, ArrayNode output) {
        OutputGetPlayerTurn outputGetPlayerTurn = new OutputGetPlayerTurn();
        outputGetPlayerTurn.outputGetPlayerTurn(gameDetails.getStartGame().getStartingPlayer(), output);
    }
}
