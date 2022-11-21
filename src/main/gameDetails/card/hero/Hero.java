package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

/**
 * Class used for the heroes type of cards
 */
public class Hero extends CardDetails {
    private final ArrayList<String> heroCards = new ArrayList<>();

    /**
     * verifies which type of hero card we have and uses its special ability
     * @param hero the card we need to check
     * @param currGame the game that is playing
     * @param affectedRow the row that is going to be affected by the card
     */
    public void useAbility(final CardDetails hero, final Game currGame, final int affectedRow) {
        if (hero.getName().compareTo("Lord Royce") == 0) {
            LordRoyce lordRoyce = new LordRoyce();

            lordRoyce.subZero(currGame, affectedRow);

        } else if (hero.getName().compareTo("Empress Thorina") == 0) {
            EmpressThorina empressThorina = new EmpressThorina();

            empressThorina.lowBlow(currGame, affectedRow);

        } else if (hero.getName().compareTo("King Mudface") == 0) {
            KingMudface kingMudface = new KingMudface();

            kingMudface.earthBorn(currGame, affectedRow);

        } else {
            GeneralKocioraw generalKocioraw = new GeneralKocioraw();

            generalKocioraw.bloodThirst(currGame, affectedRow);
        }
    }

    /**
     * constructor that adds the names of the heroes to the arraylist
     */
    public Hero() {
        heroCards.add("Empress Thorina");
        heroCards.add("General Kocioraw");
        heroCards.add("King Mudface");
        heroCards.add("Lord Royce");
    }

    /**
     * @return returns the arraylist of heroes
     */
    public ArrayList<String> getHeroCards() {
        return heroCards;
    }
}
