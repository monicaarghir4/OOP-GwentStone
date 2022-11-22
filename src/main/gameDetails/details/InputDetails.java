package main.gameDetails.details;

import fileio.GameInput;
import fileio.Input;

import java.util.ArrayList;

/**
 * Class that deep copies the details of the input
 */
public class InputDetails {
    private DecksDetails playerOneDecks;
    private DecksDetails playerTwoDecks;
    private ArrayList<GameDetails> games = new ArrayList<>();

    /**
     * default constructor
     */
    public InputDetails() {
    }

    /**
     * deep copies from the input
     * @param input the input data
     */
    public InputDetails(final Input input) {
        this.playerOneDecks = new DecksDetails(input.getPlayerOneDecks());
        this.playerTwoDecks = new DecksDetails(input.getPlayerTwoDecks());

        for (GameInput gameInput : input.getGames()) {
            this.games.add(new GameDetails(gameInput));
        }
    }

    /**
     * @return returns the decks that the first player has
     */
    public DecksDetails getPlayerOneDecks() {
        return playerOneDecks;
    }

    /**
     * @param playerOneDecks changes the decks that the first player has
     */
    public void setPlayerOneDecks(final DecksDetails playerOneDecks) {
        this.playerOneDecks = playerOneDecks;
    }

    /**
     * @return returns the decks that the second player has
     */
    public DecksDetails getPlayerTwoDecks() {
        return playerTwoDecks;
    }

    /**
     * @param playerTwoDecks changes the decks that the second player has
     */
    public void setPlayerTwoDecks(final DecksDetails playerTwoDecks) {
        this.playerTwoDecks = playerTwoDecks;
    }

    /**
     * @return returns the game details
     */
    public ArrayList<GameDetails> getGames() {
        return games;
    }

    /**
     * @param games changes the game details
     */
    public void setGames(final ArrayList<GameDetails> games) {
        this.games = games;
    }
}
