package main.gameDetails.details;

import fileio.StartGameInput;

/**
 * Class that deep copies the details of the beginning of the game
 */
public class StartGameDetails {
    private int playerOneDeckIdx;
    private int playerTwoDeckIdx;
    private int shuffleSeed;
    private CardDetails playerOneHero;
    private CardDetails playerTwoHero;
    private int startingPlayer;

    /**
     * default constructor
     */
    public StartGameDetails() {
    }

    /**
     * deep copies the data
     * @param start the input data
     */
    public StartGameDetails(final StartGameInput start) {
        this.playerOneDeckIdx = start.getPlayerOneDeckIdx();
        this.playerTwoDeckIdx = start.getPlayerTwoDeckIdx();
        this.shuffleSeed = start.getShuffleSeed();
        this.playerOneHero = new CardDetails(start.getPlayerOneHero());
        this.playerTwoHero = new CardDetails(start.getPlayerTwoHero());
        this.startingPlayer = start.getStartingPlayer();
    }

    /**
     * @return returns the index of the deck the first player uses in the game
     */
    public int getPlayerOneDeckIdx() {
        return playerOneDeckIdx;
    }

    /**
     * @param playerOneDeckIdx changes the index of the deck the first player uses in the game
     */
    public void setPlayerOneDeckIdx(final int playerOneDeckIdx) {
        this.playerOneDeckIdx = playerOneDeckIdx;
    }

    /**
     * @return returns the index of the deck the second player uses in the game
     */
    public int getPlayerTwoDeckIdx() {
        return playerTwoDeckIdx;
    }

    /**
     * @param playerTwoDeckIdx changes the index of the deck the second player uses in the game
     */
    public void setPlayerTwoDeckIdx(final int playerTwoDeckIdx) {
        this.playerTwoDeckIdx = playerTwoDeckIdx;
    }

    /**
     * @return returns the seed use for the shuffling of the deck
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }

    /**
     * @param shuffleSeed changes the seed use for the shuffling of the deck
     */
    public void setShuffleSeed(final int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }

    /**
     * @return returns the hero the first player has
     */
    public CardDetails getPlayerOneHero() {
        return playerOneHero;
    }

    /**
     * @param playerOneHero changes the hero the first player has
     */
    public void setPlayerOneHero(final CardDetails playerOneHero) {
        this.playerOneHero = playerOneHero;
    }

    /**
     * @return returns the hero the second player has
     */
    public CardDetails getPlayerTwoHero() {
        return playerTwoHero;
    }

    /**
     * @param playerTwoHero changes the hero the second player has
     */
    public void setPlayerTwoHero(final CardDetails playerTwoHero) {
        this.playerTwoHero = playerTwoHero;
    }

    /**
     * @return returns the player that starts the game
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

    /**
     * @param startingPlayer changes the player that starts the game
     */
    public void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
}
