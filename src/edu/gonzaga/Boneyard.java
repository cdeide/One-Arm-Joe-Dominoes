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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a stock-pile of Dominoes.
 */
public class Boneyard {
    private List<Domino> dominoes;

    //Constructor
    public Boneyard() {
        ArrayList<Domino> boneyard = new ArrayList<>();
        for(int i = 0; i <= 6; i++) {
            for(int j = 0; j <= 6; j++) {
                if(j >= i) { //Avoid duplicates
                    Domino newDomino = new Domino(i, j);
                    boneyard.add(newDomino);
                }
            }
        }
        Collections.shuffle(boneyard); //Randomize the boneyard
        dominoes = boneyard;
    }

    //Getter
    public List<Domino> getDominoes() {
        return dominoes;
    }

    /**
     * Return a string representation of the Boneyard
     * @return
     */
    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        for(int i = 0; i < dominoes.size(); i++) {
            listString.append(dominoes.get(i)).append(" ");
        }
        return listString.toString();
    }

    /**
     * Returns a random Domino; otherwise returns null.
     * @return
     */
    public Domino drawDomino() {
        //return random domino from the boneyard
        if(dominoes == null) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(dominoes.size());
        Domino domino = dominoes.get(index);
        dominoes.remove(index); //Remove domino from the boneyard
        return domino;
    }
}
