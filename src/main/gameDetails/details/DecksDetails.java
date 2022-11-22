package main.gameDetails.details;

import fileio.CardInput;
import fileio.DecksInput;

import java.util.ArrayList;

/**
 * Class that deep copies the decks details from the input
 */
public class DecksDetails {
    private int nrCardsInDeck;
    private int nrDecks;
    private ArrayList<ArrayList<CardDetails>> decks = new ArrayList<>();

    /**
     * default constructor
     */
    public DecksDetails() {
    }

    /**
     * deep copies from the input
     * @param decksInput the decks input
     */
    public DecksDetails(final DecksInput decksInput) {
        this.nrCardsInDeck = decksInput.getNrCardsInDeck();
        this.nrDecks = decksInput.getNrDecks();

        for (ArrayList<CardInput> deck : decksInput.getDecks()) {
            ArrayList<CardDetails> deckNew = new ArrayList<>();
            for (CardInput card : deck) {
                CardDetails cardNew = new CardDetails(card);
                deckNew.add(cardNew);
            }
            this.decks.add(deckNew);
        }
    }

    /**
     * @return returns the number of cards in the deck
     */
    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    /**
     * @param nrCardsInDeck changes the number of cards in the deck
     */
    public void setNrCardsInDeck(final int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    /**
     * @return returns the number of decks for a player
     */
    public int getNrDecks() {
        return nrDecks;
    }

    /**
     * @param nrDecks changes the number of decks for a player
     */
    public void setNrDecks(final int nrDecks) {
        this.nrDecks = nrDecks;
    }

    /**
     * @return returns the decks
     */
    public ArrayList<ArrayList<CardDetails>> getDecks() {
        return decks;
    }

    /**
     * @param decks changes the decks
     */
    public void setDecks(final ArrayList<ArrayList<CardDetails>> decks) {
        this.decks = decks;
    }
}
