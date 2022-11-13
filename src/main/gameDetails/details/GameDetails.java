package main.gameDetails.details;

import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

public class GameDetails {
    private StartGameDetails startGame;
    private ArrayList<ActionsDetails> actions = new ArrayList<>();

    public GameDetails() {
    }

    public GameDetails(GameInput gameInput) {
        this.startGame = new StartGameDetails(gameInput.getStartGame());
        for (ActionsInput action : gameInput.getActions()) {
            this.actions.add(new ActionsDetails(action));
        }
    }

    public StartGameDetails getStartGame() {
        return startGame;
    }

    public void setStartGame(StartGameDetails startGame) {
        this.startGame = startGame;
    }

    public ArrayList<ActionsDetails> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionsDetails> actions) {
        this.actions = actions;
    }
}
