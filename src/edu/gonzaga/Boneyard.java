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

    /**
     * Getter for the boneyard list
     * @return List<Domino>
     */
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
