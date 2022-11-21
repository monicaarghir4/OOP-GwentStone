package main.gameDetails.card.minion.abilities;

import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;

/**
 * Class that implements the ability of the minion card
 */
public class TheRipper extends Minion {
    /**
     * @param cardAttacked contains the data of the attacked card
     */
    public void weakKnees(final CardDetails cardAttacked) {
        cardAttacked.setAttackDamage(cardAttacked.getAttackDamage() - 2);

        if (cardAttacked.getAttackDamage() < 0) {
            cardAttacked.setAttackDamage(0);
        }
    }
}
