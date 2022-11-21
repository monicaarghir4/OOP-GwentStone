package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that implements the ability of the firestorm card
 */
public class Firestorm extends Environment {
    /**
     * @param currGame the game that's playing
     * @param affectedRow the row that is going to be affected
     */
    public void useFirestorm(final Game currGame, final int affectedRow) {
        ArrayList<CardDetails> row = currGame.getGameTable().get(affectedRow);

        for (int i = 0; i < row.size(); i++) {
            CardDetails card = row.get(i);
            card.setHealth(card.getHealth() - 1);

            // if the health gets too low we remove the card
            if (card.getHealth() < 1) {
                currGame.getGameTable().get(affectedRow).remove(card);
                i--;
            }
        }
    }
}
