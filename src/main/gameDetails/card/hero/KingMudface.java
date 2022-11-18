package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

public class KingMudface extends Hero {
    public void EarthBorn (Game currGame, int affectedRow) {
        for (CardDetails card : currGame.getGameTable().get(affectedRow)) {
            card.setHealth(card.getHealth() + 1);
        }
    }
}
