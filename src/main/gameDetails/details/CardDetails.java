package main.gameDetails.details;

import fileio.CardInput;
import main.gameDetails.card.environment.Environment;
import main.gameDetails.card.hero.Hero;
import main.gameDetails.card.minion.Minion;

import java.util.ArrayList;

public class CardDetails {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors = new ArrayList<>();
    private String name;

    private boolean isFrozen;

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public CardDetails() {
    }

    public CardDetails(CardInput card) {
        this.mana = card.getMana();
        this.attackDamage = card.getAttackDamage();

        Hero hero = new Hero();

        if (hero.getHeroCards().contains(card.getName())) {
            this.health = 30;
        } else {
            this.health = card.getHealth();
        }

        this.description = new String(card.getDescription());
        this.colors.addAll(card.getColors());
        this.name = new String(card.getName());

        Minion minion = new Minion();

        if (minion.getMinionCardsBackRow().contains(card.getName()) || minion.getMinionCardsFrontRow().contains(card.getName())) {
            this.isFrozen = false;
        }
    }

    public CardDetails(CardDetails card) {
        this.mana = card.getMana();
        this.attackDamage = card.getAttackDamage();

        Hero hero = new Hero();

        if (hero.getHeroCards().contains(card.getName())) {
            this.health = 30;
        } else {
            this.health = card.getHealth();
        }

        this.description = new String(card.getDescription());
        this.colors.addAll(card.getColors());
        this.name = new String(card.getName());

        Minion minion = new Minion();

        if (minion.getMinionCardsBackRow().contains(card.getName()) || minion.getMinionCardsFrontRow().contains(card.getName())) {
            this.isFrozen = false;
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
