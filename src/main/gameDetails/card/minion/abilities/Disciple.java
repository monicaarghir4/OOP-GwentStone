package main.gameDetails.card.minion.abilities;

import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;

public class Disciple extends Minion {
    public void GodsPlan (CardDetails cardAttacked) {
        cardAttacked.setHealth(cardAttacked.getHealth() + 2);
    }
}
