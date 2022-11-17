package main.gameDetails.card.minion.abilities;

import main.game.Game;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;
import main.gameDetails.details.CoordinatesDetails;

public class TheRipper extends Minion {
    public void WeakKnees (CardDetails cardAttacked) {
        cardAttacked.setAttackDamage(cardAttacked.getAttackDamage() - 2);

        if (cardAttacked.getAttackDamage() < 0) {
            cardAttacked.setAttackDamage(0);
        }
    }
}
