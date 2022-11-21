package main.gameDetails.card.minion.abilities;

import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;

/**
 * Class that implements the ability of the minion card
 */
public class Disciple extends Minion {
    /**
     * @param cardAttacked contains the data of the attacked card
     */
    public void godsPlan(final CardDetails cardAttacked) {
        cardAttacked.setHealth(cardAttacked.getHealth() + 2);
    }
}
