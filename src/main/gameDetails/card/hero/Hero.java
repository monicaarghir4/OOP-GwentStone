package main.gameDetails.card.hero;

import main.game.Game;
import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Hero extends CardDetails {
    //TODO
    private ArrayList<String> heroCards = new ArrayList<>();

    public void useAbility(CardDetails hero, Game currGame, int affectedRow) {
        if (hero.getName().compareTo("Lord Royce") == 0) {
            LordRoyce lordRoyce = new LordRoyce();

            lordRoyce.SubZero(currGame, affectedRow);

        } else if (hero.getName().compareTo("Empress Thorina") == 0) {
            EmpressThorina empressThorina = new EmpressThorina();

            empressThorina.LowBlow(currGame, affectedRow);

        } else if (hero.getName().compareTo("King Mudface") == 0) {
            KingMudface kingMudface = new KingMudface();

            kingMudface.EarthBorn(currGame, affectedRow);

        } else {
            GeneralKocioraw generalKocioraw = new GeneralKocioraw();

            generalKocioraw.BloodThirst(currGame, affectedRow);
        }
    }

    public Hero() {
        heroCards.add("Empress Thorina");
        heroCards.add("General Kocioraw");
        heroCards.add("King Mudface");
        heroCards.add("Lord Royce");
    }

    public ArrayList<String> getHeroCards() {
        return heroCards;
    }

    public void setHeroCards(ArrayList<String> heroCards) {
        this.heroCards = heroCards;
    }
}
