package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Winterfell extends Environment{
    //TODO
    public void useWinterfell (Game currGame, int affectedRow) {
        ArrayList<CardDetails> row = currGame.getGameTable().get(affectedRow);

        for (CardDetails card : row) {
            if (!card.isFrozen()) {
                card.setFrozen(true);
            }
        }
    }
}
