package main.gameDetails.details;

import fileio.ActionsInput;

/**
 * class that deep copies the actions input
 */
public class ActionsDetails {
    private String command;
    private int handIdx;
    private CoordinatesDetails cardAttacker;
    private CoordinatesDetails cardAttacked;
    private int affectedRow;
    private int playerIdx;
    private int x;
    private int y;

    /**
     * default constructor
     */
    public ActionsDetails() {
    }

    /**
     * constructor that deep copies the input actions
     * @param actions contains the data that is going to be copied
     */
    public ActionsDetails(final ActionsInput actions) {
        this.command = new String(actions.getCommand());
        this.handIdx = actions.getHandIdx();
        if (actions.getCardAttacker() != null) {
            this.cardAttacker = new CoordinatesDetails(actions.getCardAttacker());
        }
        if (actions.getCardAttacked() != null) {
            this.cardAttacked = new CoordinatesDetails(actions.getCardAttacked());
        }
        this.affectedRow = actions.getAffectedRow();
        this.playerIdx = actions.getPlayerIdx();
        this.x = actions.getX();
        this.y = actions.getY();
    }

    /**
     * @return returns the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command changes the command
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * @return returns the index of a card in hand
     */
    public int getHandIdx() {
        return handIdx;
    }

    /**
     * @param handIdx changes the index of a card in hand
     */
    public void setHandIdx(final int handIdx) {
        this.handIdx = handIdx;
    }

    /**
     * @return returns the coordinates of the attackers card
     */
    public CoordinatesDetails getCardAttacker() {
        return cardAttacker;
    }

    /**
     * @param cardAttacker changes the coordinates of the attackers card
     */
    public void setCardAttacker(final CoordinatesDetails cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    /**
     * @return returns the coordinates of the attacked card
     */
    public CoordinatesDetails getCardAttacked() {
        return cardAttacked;
    }

    /**
     * @param cardAttacked changes the coordinates of the attacked card
     */
    public void setCardAttacked(final CoordinatesDetails cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    /**
     * @return returns the row that's going to be affected
     */
    public int getAffectedRow() {
        return affectedRow;
    }

    /**
     * @param affectedRow changes the row that's going to be affected
     */
    public void setAffectedRow(final int affectedRow) {
        this.affectedRow = affectedRow;
    }

    /**
     * @return returns the players index
     */
    public int getPlayerIdx() {
        return playerIdx;
    }

    /**
     * @param playerIdx changes the players index
     */
    public void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * @return returns the coordinate of the row
     */
    public int getX() {
        return x;
    }

    /**
     * @param x changes the row
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * @return returns the coordinate of the column
     */
    public int getY() {
        return y;
    }

    /**
     * @param y changes the column
     */
    public void setY(final int y) {
        this.y = y;
    }
}
