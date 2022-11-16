package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class HeartHound extends Environment{
    //TODO
    public void useHeartHound (Game currGame, int affectedRow) {
        ArrayList<CardDetails> row = currGame.getGameTable().get(affectedRow);
        int max_health = 0;

        for (CardDetails card : row) {
            if (card.getHealth() > max_health) {
                max_health = card.getHealth();
            }
        }

        CardDetails cardMax = new CardDetails();

        for (CardDetails card : row) {
            if (card.getHealth() == max_health) {
                cardMax = new CardDetails(card);
                currGame.getGameTable().get(affectedRow).remove(card);
                break;
            }
        }

        if (affectedRow == 0) {
            currGame.getGameTable().get(3).add(cardMax);
        } else if (affectedRow == 1) {
            currGame.getGameTable().get(2).add(cardMax);
        } else if (affectedRow == 2) {
            currGame.getGameTable().get(1).add(cardMax);
        } else {
            currGame.getGameTable().get(0).add(cardMax);
        }
    }
}
