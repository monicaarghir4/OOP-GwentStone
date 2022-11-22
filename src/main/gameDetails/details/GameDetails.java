package main.gameDetails.details;

import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

/**
 * Class that deep copies the details of the game
 */
public class GameDetails {
    private StartGameDetails startGame;
    private ArrayList<ActionsDetails> actions = new ArrayList<>();

    /**
     * default constructor
     */
    public GameDetails() {
    }

    /**
     * deep copies from the input
     * @param gameInput the game input
     */
    public GameDetails(final GameInput gameInput) {
        this.startGame = new StartGameDetails(gameInput.getStartGame());
        for (ActionsInput action : gameInput.getActions()) {
            this.actions.add(new ActionsDetails(action));
        }
    }

    /**
     * @return returns the details of the beginning of the game
     */
    public StartGameDetails getStartGame() {
        return startGame;
    }

    /**
     * @param startGame changes the details of the beginning of the game
     */
    public void setStartGame(final StartGameDetails startGame) {
        this.startGame = startGame;
    }

    /**
     * @return returns the commands
     */
    public ArrayList<ActionsDetails> getActions() {
        return actions;
    }

    /**
     * @param actions changes the commands
     */
    public void setActions(final ArrayList<ActionsDetails> actions) {
        this.actions = actions;
    }
}
