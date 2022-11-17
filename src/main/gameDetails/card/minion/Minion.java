package main.gameDetails.card.minion;

import main.game.Game;
import main.gameDetails.card.minion.abilities.Disciple;
import main.gameDetails.card.minion.abilities.Miraj;
import main.gameDetails.card.minion.abilities.TheCursedOne;
import main.gameDetails.card.minion.abilities.TheRipper;
import main.gameDetails.details.CardDetails;
import main.gameDetails.details.CoordinatesDetails;

import java.util.ArrayList;

public class Minion extends CardDetails {
    //TODO
    private ArrayList<String> minionCardsFrontRow = new ArrayList<>();
    private ArrayList<String> minionCardsBackRow = new ArrayList<>();
    private ArrayList<String> tankCards = new ArrayList<>();

    public void useAbility(CardDetails cardAttacker, CardDetails cardAttacked, Game currGame, CoordinatesDetails coordCardAttacked) {
        if (cardAttacker.getName().compareTo("Disciple") == 0) {
            Disciple disciple = new Disciple();

            disciple.GodsPlan(cardAttacked);

        } else if (cardAttacker.getName().compareTo("Miraj") == 0) {
            Miraj miraj = new Miraj();

            miraj.Skyjack(cardAttacked, cardAttacker);

        } else if (cardAttacker.getName().compareTo("The Cursed One") == 0) {
            TheCursedOne theCursedOne = new TheCursedOne();

            theCursedOne.Shapeshift(cardAttacked, currGame, coordCardAttacked);
        } else if (cardAttacker.getName().compareTo("The Ripper") == 0) {
            TheRipper theRipper = new TheRipper();

            theRipper.WeakKnees(cardAttacked);
        }
    }

    public Minion(){
        minionCardsBackRow.add("Sentinel");
        minionCardsBackRow.add("Berserker");
        minionCardsFrontRow.add("Goliath");
        minionCardsFrontRow.add("Warden");
        minionCardsFrontRow.add("The Ripper");
        minionCardsFrontRow.add("Miraj");
        minionCardsBackRow.add("The Cursed One");
        minionCardsBackRow.add("Disciple");
        tankCards.add("Goliath");
        tankCards.add("Warden");
    }

    public ArrayList<String> getTankCards() {
        return tankCards;
    }

    public void setTankCards(ArrayList<String> tankCards) {
        this.tankCards = tankCards;
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
