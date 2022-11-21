package main.gameDetails.card.environment;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class used for the environment type of cards
 */
public class Environment extends CardDetails {
    private final ArrayList<String> environmentCards = new ArrayList<>();

    /**
     * verifies which type of environment card we have and uses its special ability
     * @param card the card we need to check
     * @param currGame the game that is playing
     * @param affectedRow the row that is going to be affected by the card
     */
    public void useEnvironment(final CardDetails card, final Game currGame,
                               final int affectedRow) {
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

    /**
     * constructor that adds the name of the cards in the arraylist
     */
    public Environment() {
        environmentCards.add("Firestorm");
        environmentCards.add("Heart Hound");
        environmentCards.add("Winterfell");
    }

    /**
     * @return returns the arraylist which contains all the names of the environment cards
     */
    public ArrayList<String> getEnvironmentCards() {
        return environmentCards;
    }
}
