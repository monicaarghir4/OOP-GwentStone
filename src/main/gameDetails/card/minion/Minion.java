package main.gameDetails.card.minion;

import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Minion extends CardDetails {
    //TODO
    private ArrayList<String> minionCards = new ArrayList<>();

    public Minion(){
        minionCards.add("Sentinel");
        minionCards.add("Berserker");
        minionCards.add("Goliath");
        minionCards.add("Warden");
        minionCards.add("The Ripper");
        minionCards.add("Miraj");
        minionCards.add("The Cursed One");
        minionCards.add("Disciple");
    }

    public ArrayList<String> getMinionCards() {
        return minionCards;
    }

    public void setMinionCards(ArrayList<String> minionCards) {
        this.minionCards = minionCards;
    }
}
