package main.gameDetails.players;

import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Player {
    private ArrayList<CardDetails> playersHand = new ArrayList<>();
    private static int mana;
    private static int gamesPlayed;
    private static int wonGames;

    public ArrayList<CardDetails> getPlayersHand() {
        return playersHand;
    }

    public void setPlayersHand(ArrayList<CardDetails> playersHand) {
        this.playersHand = playersHand;
    }

    public static int getMana() {
        return mana;
    }

    public static void setMana(int mana) {
        Player.mana = mana;
    }

    public static int getGamesPlayed() {
        return gamesPlayed;
    }

    public static void setGamesPlayed(int gamesPlayed) {
        Player.gamesPlayed = gamesPlayed;
    }

    public static int getWonGames() {
        return wonGames;
    }

    public static void setWonGames(int wonGames) {
        Player.wonGames = wonGames;
    }
}
