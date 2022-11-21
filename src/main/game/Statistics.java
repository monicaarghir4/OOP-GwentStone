package main.game;

/**
 * Class used for stocking the data of the games statistics
 */
public class Statistics {
    private int totalGamesPlayed;
    private int playerOneWins;
    private int playerTwoWins;

    /**
     * @return returns the number of games won by the first player
     */
    public int getPlayerOneWins() {
        return playerOneWins;
    }

    /**
     * @param playerOneWins changes the number of games won by the first player
     */
    public void setPlayerOneWins(final int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    /**
     * @return returns the number of games won by the second player
     */
    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    /**
     * @param playerTwoWins changes the number of games won by the second player
     */
    public void setPlayerTwoWins(final int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    /**
     * constructor that sets the fields to 0
     */
    public Statistics() {
        this.totalGamesPlayed = 0;
        this.playerOneWins = 0;
        this.playerTwoWins = 0;
    }

    /**
     * @return returns the total number of games played by the moment
     */
    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    /**
     * @param totalGamesPlayed changes the total number of games played by the moment
     */
    public void setTotalGamesPlayed(final int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }
}
