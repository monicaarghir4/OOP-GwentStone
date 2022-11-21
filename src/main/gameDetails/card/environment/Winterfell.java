package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that implements the ability of the card
 */
public class Winterfell extends Environment {
    /**
     * @param currGame the game that's playing
     * @param affectedRow the row that is going to be affected
     */
    public void useWinterfell(final Game currGame, final int affectedRow) {
        ArrayList<CardDetails> row = currGame.getGameTable().get(affectedRow);

        for (CardDetails card : row) {
            card.setFrozen(true);
        }
    }
}
