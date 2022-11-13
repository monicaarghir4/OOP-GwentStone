package main.gameDetails.details;

import fileio.CardInput;
import fileio.DecksInput;

import java.util.ArrayList;

public class DecksDetails {
    private int nrCardsInDeck;
    private int nrDecks;
    private ArrayList<ArrayList<CardDetails>> decks = new ArrayList<>();

    public DecksDetails() {
    }

    public DecksDetails(DecksInput decksInput) {
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

    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    public void setNrCardsInDeck(int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    public int getNrDecks() {
        return nrDecks;
    }

    public void setNrDecks(int nrDecks) {
        this.nrDecks = nrDecks;
    }

    public ArrayList<ArrayList<CardDetails>> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<ArrayList<CardDetails>> decks) {
        this.decks = decks;
    }
}
