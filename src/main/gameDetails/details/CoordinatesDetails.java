package main.gameDetails.details;

import fileio.Coordinates;

/**
 * Class that deep copies the coordinates details from the input
 */
public class CoordinatesDetails {
    private int x, y;

    /**
     * default constructor
     */
    public CoordinatesDetails() {
    }

    /**
     * deep copies from the input
     * @param coordinates the coordinates
     */
    public CoordinatesDetails(final Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    /**
     * @return returns the row
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
     * @return returns the column
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
