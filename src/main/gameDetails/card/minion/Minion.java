package main.gameDetails.card.minion;

import main.game.Game;
import main.gameDetails.card.minion.abilities.Disciple;
import main.gameDetails.card.minion.abilities.Miraj;
import main.gameDetails.card.minion.abilities.TheCursedOne;
import main.gameDetails.card.minion.abilities.TheRipper;
import main.gameDetails.details.CardDetails;
import main.gameDetails.details.CoordinatesDetails;

import java.util.ArrayList;

/**
 * Class used for the normal type of cards
 */
public class Minion extends CardDetails {
    private ArrayList<String> minionCardsFrontRow = new ArrayList<>();
    private ArrayList<String> minionCardsBackRow = new ArrayList<>();
    private ArrayList<String> tankCards = new ArrayList<>();

    /**
     * @param cardAttacker the card that contains the data of the attacker
     * @param cardAttacked contains the data of the attacked card
     * @param currGame the game that is playing
     * @param coordinateCardAttacked the row and column of the affected card
     */
    public void useAbility(final CardDetails cardAttacker, final CardDetails cardAttacked,
                           final Game currGame, final CoordinatesDetails coordinateCardAttacked) {
        if (cardAttacker.getName().compareTo("Disciple") == 0) {
            Disciple disciple = new Disciple();

            disciple.godsPlan(cardAttacked);

        } else if (cardAttacker.getName().compareTo("Miraj") == 0) {
            Miraj miraj = new Miraj();

            miraj.skyjack(cardAttacked, cardAttacker);

        } else if (cardAttacker.getName().compareTo("The Cursed One") == 0) {
            TheCursedOne theCursedOne = new TheCursedOne();

            theCursedOne.Shapeshift(cardAttacked, currGame, coordinateCardAttacked);
        } else if (cardAttacker.getName().compareTo("The Ripper") == 0) {
            TheRipper theRipper = new TheRipper();

            theRipper.weakKnees(cardAttacked);
        }
    }

    /**
     * constructor that adds the names of the cards to each arraylist
     */
    public Minion() {
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

    /**
     * @return returns the arraylist which contains the tank cards
     */
    public ArrayList<String> getTankCards() {
        return tankCards;
    }

    /**
     * @return returns the arraylist which contains the cards supposed to be on the first row
     */
    public ArrayList<String> getMinionCardsFrontRow() {
        return minionCardsFrontRow;
    }

    /**
     * @return returns the arraylist which contains the cards supposed to be on the back row
     */
    public ArrayList<String> getMinionCardsBackRow() {
        return minionCardsBackRow;
    }
}
