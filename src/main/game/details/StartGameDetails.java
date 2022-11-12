package main.game.details;

import fileio.StartGameInput;

public class StartGameDetails {
    private int playerOneDeckIdx;
    private int playerTwoDeckIdx;
    private int shuffleSeed;
    private CardDetails playerOneHero;
    private CardDetails playerTwoHero;
    private int startingPlayer;

    public StartGameDetails() {
    }

    public StartGameDetails(StartGameInput start) {
        this.playerOneDeckIdx = start.getPlayerOneDeckIdx();
        this.playerTwoDeckIdx = start.getPlayerTwoDeckIdx();
        this.shuffleSeed = start.getShuffleSeed();
        this.playerOneHero = new CardDetails(start.getPlayerOneHero());
        this.playerTwoHero = new CardDetails(start.getPlayerTwoHero());
        this.startingPlayer = start.getStartingPlayer();
    }

    public int getPlayerOneDeckIdx() {
        return playerOneDeckIdx;
    }

    public void setPlayerOneDeckIdx(int playerOneDeckIdx) {
        this.playerOneDeckIdx = playerOneDeckIdx;
    }

    public int getPlayerTwoDeckIdx() {
        return playerTwoDeckIdx;
    }

    public void setPlayerTwoDeckIdx(int playerTwoDeckIdx) {
        this.playerTwoDeckIdx = playerTwoDeckIdx;
    }

    public int getShuffleSeed() {
        return shuffleSeed;
    }

    public void setShuffleSeed(int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }

    public CardDetails getPlayerOneHero() {
        return playerOneHero;
    }

    public void setPlayerOneHero(CardDetails playerOneHero) {
        this.playerOneHero = playerOneHero;
    }

    public CardDetails getPlayerTwoHero() {
        return playerTwoHero;
    }

    public void setPlayerTwoHero(CardDetails playerTwoHero) {
        this.playerTwoHero = playerTwoHero;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
}
