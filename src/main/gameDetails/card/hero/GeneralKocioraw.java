package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

public class GeneralKocioraw extends Hero {
    public void BloodThirst (Game currGame, int affectedRow) {
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
    }
}
