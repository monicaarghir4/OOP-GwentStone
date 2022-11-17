package main.gameDetails.card.minion.abilities;

import main.gameDetails.card.minion.Minion;
import main.gameDetails.details.CardDetails;

public class Miraj extends Minion {
    public void Skyjack (CardDetails cardAttacked, CardDetails cardAttacker) {
        int aux = cardAttacked.getHealth();

        cardAttacked.setHealth(cardAttacker.getHealth());
        cardAttacker.setHealth(aux);
    }
}
