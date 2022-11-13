package main.gameDetails.details;

import fileio.Coordinates;

public class CoordinatesDetails {
    private int x, y;

    public CoordinatesDetails() {
    }

    public CoordinatesDetails(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
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
