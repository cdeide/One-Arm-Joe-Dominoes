/**
 * This program is a single player game of One-Arm Joe Dominoes against a computer player
 * It is a multi-round game, lasting until one player reaches at least 20 points
 * CPSC 312, Fall 2021
 * PA2
 * No sources to site
 * @Connor Deide
 * @Version v1.0.0 9/21/2020
 */
package edu.gonzaga;

/**
 * Represents a single domino
 */
public class Domino {
    private int pipsLeft; //number of pips on the left side of Domino
    private int pipsRight; //number of pips on the right side of the Domino

    //Constructor
    public Domino(int pipsLeft, int pipsRight) {
        this.pipsLeft = pipsLeft;
        this.pipsRight = pipsRight;
    }
    //Getters
    public int getPipsLeft() {
        return pipsLeft;
    }
    public int getPipsRight() {
        return pipsRight;
    }

    /**
     * Returns a string representation of a Domino
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.pipsLeft + "|" + this.pipsRight + "]";
    }

    /**
     * Flips the left and right pip values
     */
    public void flip() {
        int tmp = this.pipsLeft;
        this.pipsLeft = this.pipsRight;
        this.pipsRight = tmp;
    }

    /**
     * Returns the total number of pips on the Domino
     * @return sum
     */
    public int sumDomino() {
        return this.pipsLeft + this.pipsRight;
    }
}
