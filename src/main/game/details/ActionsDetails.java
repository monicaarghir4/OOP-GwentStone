package main.game.details;

import fileio.ActionsInput;

public class ActionsDetails {
    private String command;
    private int handIdx;
    private CoordinatesDetails cardAttacker;
    private CoordinatesDetails cardAttacked;
    private int affectedRow;
    private int playerIdx;
    private int x;
    private int y;

    public ActionsDetails() {
    }

    public ActionsDetails(ActionsInput actions) {
        this.command = new String(actions.getCommand());
        this.handIdx = actions.getHandIdx();
        this.cardAttacker = new CoordinatesDetails(actions.getCardAttacker());
        this.cardAttacked = new CoordinatesDetails(actions.getCardAttacked());
        this.affectedRow = actions.getAffectedRow();
        this.playerIdx = actions.getPlayerIdx();
        this.x = actions.getX();
        this.y = actions.getY();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getHandIdx() {
        return handIdx;
    }

    public void setHandIdx(int handIdx) {
        this.handIdx = handIdx;
    }

    public CoordinatesDetails getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(CoordinatesDetails cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public CoordinatesDetails getCardAttacked() {
        return cardAttacked;
    }

    public void setCardAttacked(CoordinatesDetails cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public int getAffectedRow() {
        return affectedRow;
    }

    public void setAffectedRow(int affectedRow) {
        this.affectedRow = affectedRow;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
