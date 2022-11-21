package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

/**
 * Class that implements the ability of the hero
 */
public class KingMudface extends Hero {
    /**
     * @param currGame the game that's playing
     * @param affectedRow the row that is going to be affected
     */
    public void earthBorn(final Game currGame, final int affectedRow) {
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            card.setHealth(card.getHealth() + 1);
        }
    }
}
