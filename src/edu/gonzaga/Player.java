package edu.gonzaga;

import java.util.*;

/**
 * Represents a player in the game
 */
public class Player {
    private int playerNum;
    private List<Domino> hand;

    public Player (int playerNum, List<Domino> boneyard) {
        this.playerNum = playerNum;
        ArrayList<Domino> hand = new ArrayList<>();
        ArrayList<Integer> randNums = new ArrayList<>();
        for(int i = 0; i < 28; i++) {
            randNums.add(i, i);
        }
        Collections.shuffle(randNums);
        for(int i = 0; i < 3; i++) {
            hand.add(boneyard.get(randNums.get(i)));
            boneyard.remove(randNums.get(i));
        }
        this.hand = hand;
    }
    //Getter
    public List<Domino> getHand() {
        return hand;
    }

    /**
     * Returns a String representation of the hand
     * @return string of hand
     */
    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        if(this.playerNum == 1) {
            for (int i = 0; i < hand.size(); i++) {
                handString.append(hand.get(i)).append(" ");
            }
        }
        else {
            for(int i = 0; i < hand.size(); i++) {
                handString.append("[?|?]").append(" ");
            }
        }
        return handString.toString();
    }

    /**
     * Returns true if the hand has at least one domino that can play on the board,
     * false otherwise
     * @param board
     * @return true, false
     */
    public boolean canPlay(int turnNum, OneArmJoeBoard board) {
        if(turnNum == 2) { // Turn number 2 is a special case, can start the arm in either direction
            for(int i = 0; i < board.getDominoes().size(); i++) {
                for(int j = 0; j < hand.size(); j++) {
                    if(hand.get(j).getPipsRight() == board.getDominoes().get(0).getPipsLeft()) {
                        board.getDominoes().get(0).flip(); //flip
                        return true; //Check for match on the opposite arm
                    }
                    if(hand.get(j).getPipsLeft() == board.getDominoes().get(0).getPipsRight() || hand.get(j).getPipsRight() == board.getDominoes().get(0).getPipsRight()) {
                        return true; //If match is found return true
                    }
                }
            }
        }
        else {
            for(int i = 0; i < board.getDominoes().size(); i++) {
                for(int j = 0; j < hand.size(); j++) {
                    if(hand.get(j).getPipsLeft() == board.getDominoes().get(board.getDominoes().size() - 1).getPipsRight()) {
                        return true; //If match is found return true
                    }
                    if(hand.get(j).getPipsRight() == board.getDominoes().get(board.getDominoes().size() - 1).getPipsRight()) {
                        return true; //If match is found return true
                    }
                }
            }
        }
        return false;
    }

    public void firstTurn(int startPlayer, OneArmJoeBoard board) {
        if(startPlayer == 1) {
            System.out.println("- It is Player1's turn -");
            System.out.println("Board: " + board + "<-");
            System.out.println("Player1's hand: " + this);
            System.out.println("Which domino would you like to play? Enter the pip numbers of the domino:");
            String playTurn = getInput(); //Get user input then turn string into two int values
            int pipsLeft = getPipsLeft(playTurn);
            int pipsRight = getPipsRight(playTurn);
            int i;
            for( i = 0; i < hand.size(); i++) {
                if(pipsLeft == hand.get(i).getPipsLeft() && pipsRight == hand.get(i).getPipsRight()) {
                    break;
                }
            }
            //Add domino to board and remove from hand
            board.getDominoes().add(hand.get(i));
            hand.remove(i);
        }
        else if(startPlayer == 2) {
            System.out.println("- It is Player2's turn -");
            System.out.println("Board: " + board + "<-");
            System.out.println("Player2's hand: " + this);
            System.out.println("Playing a " + hand.get(0));
            //Add domino to board and remove from hand
            board.getDominoes().add(hand.get(0));
            hand.remove(0);
        }
    }

    public void playTurn(int turnNum, int player, Boneyard boneyard, OneArmJoeBoard board){
        boolean validPlay = false;
        boolean mustDraw = false;
        if(turnNum == 1) {
            firstTurn(player, board);
            System.out.println("Press enter to continue...");
            getInput();
        }
        else {
            if(player == 1) {
                System.out.println("- It is Player1's turn -");
                while(!validPlay) {
                    while(!mustDraw) { //Draw loop as long as player can't play domino
                        System.out.println("Board: " + board + "<-");
                        System.out.println("Player1's hand: " + this);
                        mustDraw = canPlay(turnNum, board);
                        if (!mustDraw) {
                            System.out.println("Looks like Player1 can't play -> Drawing...");
                            hand.add(boneyard.drawDomino()); //Draw if player can't play Domino
                        }
                    }
                    System.out.println("Which domino would you like to play? Enter the pip numbers of the domino:");
                    String playTurn = getInput(); //Get user input then turn string into two int values
                    int pipsLeft = getPipsLeft(playTurn);
                    int pipsRight = getPipsRight(playTurn);
                    validPlay = playDomino(pipsLeft, pipsRight, board);
                }
            }
            if(player == 2) {
                System.out.println("- It is Player2's turn -");
                while(!mustDraw) { //Draw loop as long as player can't play domino
                    System.out.println("Board: " + board + "<-");
                    System.out.println("Player2's hand: " + this);
                    mustDraw = canPlay(turnNum, board);
                    if(!mustDraw) {
                        System.out.println("Looks like Player2 can't play -> Drawing...");
                        hand.add(boneyard.drawDomino()); //Draw if player can't play Domino
                    }
                }
                int j = 0; //Initialize to widen scope for later use
                for(int i = 0; i < board.getDominoes().size(); i++) {
                    for(j = 0; j < hand.size() - 1; j++) {
                        if(hand.get(j).getPipsLeft() == board.getDominoes().get(board.getDominoes().size() - 1).getPipsRight()) {
                            break; //If match is found return true
                        }
                        if(hand.get(j).getPipsRight() == board.getDominoes().get(board.getDominoes().size() - 1).getPipsRight()) {
                            break;
                        }
                    }
                }
                System.out.println("Playing a " + hand.get(j));
                //Add domino to board and remove from hand
                board.addDomino(hand.get(j));
                hand.remove(j);
                System.out.println("Press enter to continue...");
                getInput();
            }
        }
    }

    /**
     * Attempts to play a Domino the user specified by its number of pips.
     * Returns true if the Domino is successfully removed from the hand and
     * played on the board, false otherwise
     * @param pipsLeft
     * @param pipsRight
     * @param board
     * @return true, false
     */
    public boolean playDomino(int pipsLeft, int pipsRight, OneArmJoeBoard board) {
        boolean checkHand;
        boolean checkBoneyard;
        int i;
        for(i = 0; i < hand.size() - 1; i++) {
            if(pipsLeft == hand.get(i).getPipsLeft() && pipsRight == hand.get(i).getPipsRight()) {
                break;
            }
        }
        checkBoneyard = board.addDomino(hand.get(i));
        if(checkBoneyard) {
            hand.remove(i); //Remove the domino from hand
            return true; //Domino has been played successfully
        }
        else { //If checkBoard is false, return false
            return false;
        }
    }

    /**
     * Return the total number of pips in the hand
     * @return score of hand
     */
    public int scoreHand() {
        int score = 0;
        for(int i = 0; i < hand.size() - 1; i++) {
            score += hand.get(i).sumDomino();
        }
        return score;
    }

    /**
     * Methods return the number of pips input by the user
     * @param input
     * @return int value
     */
    private int getPipsLeft(String input) {
        char charLeft = input.charAt(0);
        return Integer.parseInt(String.valueOf(charLeft));
    }
    private int getPipsRight(String input) {
        char charRight = input.charAt(1);
        return Integer.parseInt(String.valueOf(charRight));
    }

    /**
     * Returns a string input from the user
     * @return input
     */
    private String getInput() {
        Scanner kb = new Scanner(System.in);
        String input = kb.nextLine();
        return input;
    }
}
