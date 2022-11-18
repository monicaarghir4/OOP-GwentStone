package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

public class EmpressThorina extends Hero{
    public void LowBlow (Game currGame, int affectedRow) {
        int maxHealth = 0;

        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
            }
        }

        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            if (card.getHealth() == maxHealth) {
                currGame.getGameTable().get(affectedRow).remove(card);
                return;
            }
        }
    }
}
