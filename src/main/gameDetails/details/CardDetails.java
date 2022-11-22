package main.gameDetails.details;

import fileio.CardInput;
import main.gameDetails.card.hero.Hero;
import main.gameDetails.card.minion.Minion;

import java.util.ArrayList;

/**
 * Class that deep copies the data for the cards
 */
public class CardDetails {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors = new ArrayList<>();
    private String name;

    private boolean isFrozen;

    private boolean hasAttacked;

    private final int heroHealth = 30;

    /**
     * @return returns the variable to know if a card has attacked yet
     */
    public boolean isHasAttacked() {
        return hasAttacked;
    }

    /**
     * @param hasAttacked changes the propriety that a card has attacked
     */
    public void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     * @return returns the variable to know if a card is frozen
     */
    public boolean isFrozen() {
        return isFrozen;
    }

    /**
     * @param frozen changes the propriety that a card is frozen
     */
    public void setFrozen(final boolean frozen) {
        isFrozen = frozen;
    }

    /**
     * default constructor
     */
    public CardDetails() {
    }

    /**
     * deep copies the data for the card from the input
     * @param card the input
     */
    public CardDetails(final CardInput card) {
        this.mana = card.getMana();
        this.attackDamage = card.getAttackDamage();

        Hero hero = new Hero();

        // if the card is hero type he has a fixed health at 30
        if (hero.getHeroCards().contains(card.getName())) {
            this.health = heroHealth;
        } else {
            this.health = card.getHealth();
        }

        this.description = card.getDescription();
        this.colors.addAll(card.getColors());
        this.name = card.getName();

        Minion minion = new Minion();

        if (minion.getMinionCardsBackRow().contains(card.getName())
                || minion.getMinionCardsFrontRow().contains(card.getName())) {
            this.isFrozen = false;
            this.hasAttacked = false;
        }
    }

    /**
     * deep copies the data for the card from another card
     * @param card the input
     */
    public CardDetails(final CardDetails card) {
        this.mana = card.getMana();
        this.attackDamage = card.getAttackDamage();

        Hero hero = new Hero();

        if (hero.getHeroCards().contains(card.getName())) {
            this.health = heroHealth;
        } else {
            this.health = card.getHealth();
        }

        this.description = card.getDescription();
        this.colors.addAll(card.getColors());
        this.name = card.getName();

        Minion minion = new Minion();

        if (minion.getMinionCardsBackRow().contains(card.getName())
                || minion.getMinionCardsFrontRow().contains(card.getName())) {
            this.isFrozen = false;
            this.hasAttacked = false;
        }
    }

    /**
     * @return returns the mana of the card
     */
    public int getMana() {
        return mana;
    }

    /**
     * @param mana changes the mana of the player
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * @return returns the attack damage the card has
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * @param attackDamage changes the attack damage the card has
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * @return returns the health a card has
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health changes the health a card has
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * @return returns the description of the card
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description changes the description of the card
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return returns the array of the colors of the card
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * @param colors change the array of the colors of the card
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * @return returns the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * @param name changes the name of the card
     */
    public void setName(final String name) {
        this.name = name;
    }
}
