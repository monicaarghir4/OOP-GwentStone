package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

public class LordRoyce extends Hero{
    public void SubZero(Game currGame, int affectedRow) {
        int maxAttack = 0;

        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getAttackDamage() > maxAttack) {
                maxAttack = card.getAttackDamage();
            }
        }

        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getAttackDamage() == maxAttack) {
                card.setFrozen(true);
                return;
            }
        }
    }
}
