package main.gameDetails.card.environment;

import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Environment extends CardDetails {
    //TODO
    private ArrayList<String> environmentCards = new ArrayList<>();

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
