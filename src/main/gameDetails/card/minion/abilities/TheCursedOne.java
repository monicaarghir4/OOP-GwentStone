package main.gameDetails.card.minion.abilities;

import main.game.Game;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;
import main.gameDetails.details.CoordinatesDetails;

public class TheCursedOne extends Minion {
    public void Shapeshift (CardDetails cardAttacked, Game currGame, CoordinatesDetails coordCardAttacked) {
        int aux = cardAttacked.getHealth();

        cardAttacked.setHealth(cardAttacked.getAttackDamage());
        cardAttacked.setAttackDamage(aux);

        if (cardAttacked.getHealth() == 0) {
            currGame.getGameTable().get(coordCardAttacked.getX()).remove(cardAttacked);
        }
    }
}
