package main.gameDetails;

import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that holds the details of a player
 */
public class Player {
    private final ArrayList<CardDetails> playersHand = new ArrayList<>();
    private int mana;

    /**
     * @return returns the cards a player has in his hand
     */
    public ArrayList<CardDetails> getPlayersHand() {
        return playersHand;
    }

    /**
     * @return returns the mana of a player
     */
    public int getMana() {
        return mana;
    }

    /**
     * @param mana changes the mana of a player
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * default constructor
     */
    public Player() {
    }
}
