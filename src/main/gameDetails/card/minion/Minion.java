package main.gameDetails.card.minion;

import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Minion extends CardDetails {
    //TODO
    private ArrayList<String> minionCardsFrontRow = new ArrayList<>();
    private ArrayList<String> minionCardsBackRow = new ArrayList<>();

    public Minion(){
        minionCardsBackRow.add("Sentinel");
        minionCardsBackRow.add("Berserker");
        minionCardsFrontRow.add("Goliath");
        minionCardsFrontRow.add("Warden");
        minionCardsFrontRow.add("The Ripper");
        minionCardsFrontRow.add("Miraj");
        minionCardsBackRow.add("The Cursed One");
        minionCardsBackRow.add("Disciple");
    }

    public ArrayList<String> getMinionCardsFrontRow() {
        return minionCardsFrontRow;
    }

    public void setMinionCardsFrontRow(ArrayList<String> minionCardsFrontRow) {
        this.minionCardsFrontRow = minionCardsFrontRow;
    }

    public ArrayList<String> getMinionCardsBackRow() {
        return minionCardsBackRow;
    }

    public void setMinionCardsBackRow(ArrayList<String> minionCardsBackRow) {
        this.minionCardsBackRow = minionCardsBackRow;
    }
}
