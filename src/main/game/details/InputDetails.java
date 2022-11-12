package main.game.details;

import fileio.GameInput;
import fileio.Input;

import java.util.ArrayList;

public class InputDetails {
    private DecksDetails playerOneDecks;
    private DecksDetails playerTwoDecks;
    private ArrayList<GameDetails> games = new ArrayList<>();

    public InputDetails() {
    }

    public InputDetails(Input input) {
        this.playerOneDecks = new DecksDetails(input.getPlayerOneDecks());
        this.playerTwoDecks = new DecksDetails(input.getPlayerTwoDecks());

        for (GameInput gameInput : input.getGames()) {
            this.games.add(new GameDetails(gameInput));
        }
    }

    public DecksDetails getPlayerOneDecks() {
        return playerOneDecks;
    }

    public void setPlayerOneDecks(DecksDetails playerOneDecks) {
        this.playerOneDecks = playerOneDecks;
    }

    public DecksDetails getPlayerTwoDecks() {
        return playerTwoDecks;
    }

    public void setPlayerTwoDecks(DecksDetails playerTwoDecks) {
        this.playerTwoDecks = playerTwoDecks;
    }

    public ArrayList<GameDetails> getGames() {
        return games;
    }

    public void setGames(ArrayList<GameDetails> games) {
        this.games = games;
    }
}
