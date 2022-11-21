package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

/**
 * Class that implements the ability of the hero
 */
public class LordRoyce extends Hero {
    /**
     * @param currGame the game that's playing
     * @param affectedRow the row that is going to be affected
     */
    public void subZero(final Game currGame, final int affectedRow) {
        int maxAttack = 0;

        // calculating the maximum attack damage of the cards on the row
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getAttackDamage() > maxAttack) {
                maxAttack = card.getAttackDamage();
            }
        }

        //freezing the first card with the maximum attack damage
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getAttackDamage() == maxAttack) {
                card.setFrozen(true);
                return;
            }
        }
    }
}
