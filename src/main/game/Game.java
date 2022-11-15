package main.game;

import main.gameDetails.details.CardDetails;
import main.gameDetails.details.StartGameDetails;
import main.gameDetails.players.Player;

import java.util.ArrayList;

public class Game{
    private Player player1 = new Player();
    private Player player2 = new Player();
    private int playersTurn;
    private int rounds;
    private ArrayList<ArrayList<CardDetails>> gameTable;

    public ArrayList<ArrayList<CardDetails>> getGameTable() {
        return gameTable;
    }

    public void setGameTable(ArrayList<ArrayList<CardDetails>> gameTable) {
        this.gameTable = gameTable;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(int playersTurn) {
        this.playersTurn = playersTurn;
    }

    public Game() {
        createGameTable();
    }

    public void createGameTable () {
        gameTable = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {                           //randuri
            gameTable.add(new ArrayList<>(5));      //coloane
        }
    }

    public void addCardToGameTable (CardDetails card, int row) {
        if (gameTable.get(row).size() < 5) {
            gameTable.get(row).add(card);
        }
    }
}
