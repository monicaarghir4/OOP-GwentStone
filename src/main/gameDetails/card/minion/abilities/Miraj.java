package main.gameDetails.card.minion.abilities;

import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;

/**
 * Class that implements the ability of the minion card
 */
public class Miraj extends Minion {
    /**
     * @param cardAttacked contains the data of the attacked card
     * @param cardAttacker contains the data of the attacker card
     */
    public void skyjack(final CardDetails cardAttacked, final CardDetails cardAttacker) {
        int aux = cardAttacked.getHealth();

        cardAttacked.setHealth(cardAttacker.getHealth());
        cardAttacker.setHealth(aux);
    }
}
