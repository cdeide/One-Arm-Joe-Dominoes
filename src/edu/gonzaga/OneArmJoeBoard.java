package edu.gonzaga;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the One-Arm Joe game board
 */
public class OneArmJoeBoard {
    private List<Domino> dominoes; //Dominoes that have been played in the game so far

    //Constructors
    public OneArmJoeBoard() {
        ArrayList<Domino> dominoes = new ArrayList<>();
        this.dominoes = dominoes;
    }
    //Getter
    public List<Domino> getDominoes() {
        return dominoes;
    }
    //Setter
    public void setDominoes(List<Domino> dominoes) {
        this.dominoes = dominoes;
    }

    /**
     * Displays the end-to-end matched Dominoes that have been played
     * @return String representation of the object
     */
    @Override
    public String toString() {
        if(dominoes == null) {
            return " ";
        }
        else {
            StringBuilder listString = new StringBuilder();
            for (int i = 0; i < dominoes.size(); i++) {
                listString.append(dominoes.get(i)).append(" ");
            }
            return listString.toString();
        }
    }

    /**
     * Attempts to add a Domino to the last played Domino on the board.
     * Returns true if Domino is successfully played on the board, false otherwise
     * @param domino
     * @return true, false
     */
    public boolean addDomino(Domino domino) {
        if(this.dominoes.get(dominoes.size() - 1).getPipsRight() == domino.getPipsRight()) {
            domino.flip(); //flip
            dominoes.add(domino);
            return true;
        }
        else if(this.dominoes == null || this.dominoes.get(dominoes.size() - 1).getPipsRight() == domino.getPipsLeft()) {
            dominoes.add(domino);
            return true; //if list is empty or left pip equals the right pip, player can add domino
        }
        else {
            System.out.println("Cannot play this domino.");
            return false;
        }
    }
}
