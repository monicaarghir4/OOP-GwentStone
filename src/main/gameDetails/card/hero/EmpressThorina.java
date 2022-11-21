package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

/**
 * Class that implements the ability of the hero
 */
public class EmpressThorina extends Hero {
    /**
     * @param currGame the game that's playing
     * @param affectedRow the row that is going to be affected
     */
    public void lowBlow(final Game currGame, final int affectedRow) {
        int maxHealth = 0;

        // calculating the maximum health of the cards on the row
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
            }
        }

        // removing the first card with the maximum health
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getHealth() == maxHealth) {
                currGame.getGameTable().get(affectedRow).remove(card);
                return;
            }
        }
    }
}
