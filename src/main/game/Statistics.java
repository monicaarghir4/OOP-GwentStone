package main.game;

public class Statistics {
    private int totalGamesPlayed;
    private int playerOneWins;
    private int playerTwoWins;

    public int getPlayerOneWins() {
        return playerOneWins;
    }

    public void setPlayerOneWins(int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public void setPlayerTwoWins(int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    public Statistics() {
        this.totalGamesPlayed = 0;
        this.playerOneWins = 0;
        this.playerTwoWins = 0;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }
}
