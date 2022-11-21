package main.game;

import main.gameDetails.details.CardDetails;
import main.gameDetails.Player;

import java.util.ArrayList;

/**
 * Class that contains the data for a game that's playing at the moment
 */
public class Game {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private int playersTurn;
    private int rounds;
    private ArrayList<ArrayList<CardDetails>> gameTable;

    private final int number4 = 4;
    private final int number5 = 5;

    /**
     * @return returns the game table
     */
    public ArrayList<ArrayList<CardDetails>> getGameTable() {
        return gameTable;
    }

    /**
     * @return returns the number of rounds played
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * @param rounds changes the number of rounds played
     */
    public void setRounds(final int rounds) {
        this.rounds = rounds;
    }

    /**
     * @return return the first player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * @return returns the second player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * @return returns the player that has the turn at the moment
     */
    public int getPlayersTurn() {
        return playersTurn;
    }

    /**
     * @param playersTurn changes the players turn
     */
    public void setPlayersTurn(final int playersTurn) {
        this.playersTurn = playersTurn;
    }

    /**
     * default constructor
     */
    public Game() {
        createGameTable();
    }

    /**
     * creates the game table at the beginning
     */
    public void createGameTable() {
        gameTable = new ArrayList<>(number4);

        for (int i = 0; i < number4; i++) {
            gameTable.add(new ArrayList<>(number5));
        }
    }

    /**
     * adds a card to the game table at a given row on the next position available
     * @param card the card
     * @param row given row
     */
    public void addCardToGameTable(final CardDetails card, final int row) {
        if (gameTable.get(row).size() < number5) {
            gameTable.get(row).add(card);
        }
    }
}
