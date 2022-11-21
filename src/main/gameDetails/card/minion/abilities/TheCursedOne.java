package main.gameDetails.card.minion.abilities;

import main.game.Game;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;
import main.gameDetails.details.CoordinatesDetails;

/**
 * Class that implements the ability of the minion card
 */
public class TheCursedOne extends Minion {
    /**
     * @param cardAttacked contains the data of the attacked card
     * @param currGame the game that is playing
     * @param coordinateCardAttacked the row and column of the affected card
     */
    public void Shapeshift(final CardDetails cardAttacked, final Game currGame,
                           final CoordinatesDetails coordinateCardAttacked) {
        int aux = cardAttacked.getHealth();

        cardAttacked.setHealth(cardAttacked.getAttackDamage());
        cardAttacked.setAttackDamage(aux);

        if (cardAttacked.getHealth() == 0) {
            currGame.getGameTable().get(coordinateCardAttacked.getX()).remove(cardAttacked);
        }
    }
}
