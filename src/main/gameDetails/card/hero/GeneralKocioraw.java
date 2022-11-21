package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

/**
 * Class that implements the ability of the hero
 */
public class GeneralKocioraw extends Hero {
    /**
     * @param currGame the game that's playing
     * @param affectedRow the row that is going to be affected
     */
    public void bloodThirst(final Game currGame, final int affectedRow) {
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
    }
}
