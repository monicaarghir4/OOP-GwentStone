package main.gameDetails.card.hero;

import main.gameDetails.details.CardDetails;

import java.util.ArrayList;

public class Hero extends CardDetails {
    //TODO
    private ArrayList<String> heroCards = new ArrayList<>();

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
