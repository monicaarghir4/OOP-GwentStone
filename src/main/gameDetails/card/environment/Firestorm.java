package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Firestorm extends Environment{
    //TODO
    public void useFirestorm(Game currGame, int affectedRow) {
        ArrayList<CardDetails> row = currGame.getGameTable().get(affectedRow);

        for (int i = 0; i < row.size(); i++) {
            CardDetails card = row.get(i);
            card.setHealth(card.getHealth() - 1);

            if (card.getHealth() < 1) {
                currGame.getGameTable().get(affectedRow).remove(card);
                i--;
            }
        }


//        for (CardDetails card : row) {
//            card.setHealth(card.getHealth() - 1);
//
//            if (card.getHealth() < 1) {
//                currGame.getGameTable().get(affectedRow).remove(card);
//                return;
//            }
//        }
    }
}
