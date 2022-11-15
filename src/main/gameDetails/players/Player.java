package main.gameDetails.players;

import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Player {
    private ArrayList<CardDetails> playersHand = new ArrayList<>();
    private int mana;
    private int gamesPlayed;
    private int wonGames;

    public ArrayList<CardDetails> getPlayersHand() {
        return playersHand;
    }

    public void setPlayersHand(ArrayList<CardDetails> playersHand) {
        this.playersHand = playersHand;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    public Player() {

    }
}
