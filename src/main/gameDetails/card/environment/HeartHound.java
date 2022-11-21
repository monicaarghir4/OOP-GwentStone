package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class that implements the cards ability
 */
public class HeartHound extends Environment {
    private final int number3 = 3;
    /**
     * @param currGame the game that's playing
     * @param affectedRow the row that is going to be affected
     */
    public void useHeartHound(final Game currGame, final int affectedRow) {
        ArrayList<CardDetails> row = currGame.getGameTable().get(affectedRow);

        // calculating the maximum health
        int number0 = 0;
        int maxHealth = number0;

        for (CardDetails card : row) {
            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
            }
        }

        CardDetails cardMax = new CardDetails();

        // deleting the first card of the row with the maximum health
        for (CardDetails card : row) {
            if (card.getHealth() == maxHealth) {
                cardMax = new CardDetails(card);
                currGame.getGameTable().get(affectedRow).remove(card);
                break;
            }
        }

        // adding the card to the players row
        int number1 = 1;
        int number2 = 2;

        if (affectedRow == number0) {
            currGame.getGameTable().get(number3).add(cardMax);
        } else if (affectedRow == number1) {
            currGame.getGameTable().get(number2).add(cardMax);
        } else if (affectedRow == number2) {
            currGame.getGameTable().get(number1).add(cardMax);
        } else {
            currGame.getGameTable().get(number0).add(cardMax);
        }
    }
}
