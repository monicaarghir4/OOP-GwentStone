package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Environment extends CardDetails {
    //TODO
    private ArrayList<String> environmentCards = new ArrayList<>();

    public void useEnvironment(CardDetails card, Game currGame, int affectedRow) {
        if (card.getName().compareTo("Firestorm") == 0) {
            Firestorm firestorm = new Firestorm();

            firestorm.useFirestorm(currGame, affectedRow);

        } else if (card.getName().compareTo("Heart Hound") == 0) {
            HeartHound heartHound = new HeartHound();

            heartHound.useHeartHound(currGame, affectedRow);

        } else {
            Winterfell winterfell = new Winterfell();

            winterfell.useWinterfell(currGame, affectedRow);

        }


    }

    public Environment() {
        environmentCards.add("Firestorm");
        environmentCards.add("Heart Hound");
        environmentCards.add("Winterfell");
    }

    public ArrayList<String> getEnvironmentCards() {
        return environmentCards;
    }

    public void setEnvironmentCards(ArrayList<String> environmentCards) {
        this.environmentCards = environmentCards;
    }
}
