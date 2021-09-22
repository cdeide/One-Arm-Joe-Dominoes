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

import java.util.Random;

public class Game {
    /**
     * Driver class with main function and game loop
     * */
    public static void main(String[] args) {
        int totalScore = 0;
        while(totalScore < 20) {
            Game newGame = new Game();
            totalScore += newGame.playGame();
        }
    }

    /**
     * Method deals with the general game flow of one round
     * @return
     */
    private int playGame() {
        int score = 0;
        boolean gameOver = false;
        // Create boneyard and players
        Boneyard newBoneyard = new Boneyard();
        OneArmJoeBoard newBoard = new OneArmJoeBoard();
        Player player1 = new Player(1, newBoneyard.getDominoes());
        Player player2 = new Player(2, newBoneyard.getDominoes());
        System.out.println(newBoneyard);
        //Set start player
        int playerNum  = setStartPlayer();
        int turnNum = 1; //To be used for the first and second turn
        while(!gameOver) { //Continue turns until game is over
            int checkGame;
            if(playerNum == 1) {
                player1.playTurn(turnNum, playerNum, newBoneyard, newBoard);
                System.out.println(); //Formatting
                //Change turns
                playerNum = switchPlayer(playerNum);
            }
            else if(playerNum == 2) {
                player2.playTurn(turnNum, playerNum, newBoneyard, newBoard);
                //Change turns
                playerNum = switchPlayer(playerNum);
            }
            checkGame = checkGameState(player1, player2, newBoneyard);
            if(checkGame == 1) { //Player1 win condition
                System.out.println("Congrats Player1, you won the round");
                score = player2.scoreHand();
                System.out.println("Player1 score: " + score);
                gameOver = true;
            }
            if(checkGame == 2) { //Player2 win condition
                System.out.println("Congrats Player2, you won the round");
                score = player1.scoreHand();
                System.out.println("Player2 score: " + score);
                gameOver = true;
            }
            if(checkGame == 3) { //Draw condition
                System.out.println("Round ended in a draw.");
                if(player1.scoreHand() < player2.scoreHand()) {
                    score = player2.scoreHand() - player1.scoreHand();
                    System.out.println("Player1 score: " + score);
                }
                else {
                    score = player1.scoreHand() - player2.scoreHand();
                    System.out.println("Player2 score: " + score);
                }
                gameOver = true;
            }
            else { //If round is not over, iterate turn
                turnNum++;
            }
        }
        return score; //Return score to be summed in the main method
    }

    /**
     * Returns a one or a two picking which player starts
     * @return 1 or 2
     */
    private static int setStartPlayer () {
        Random rand = new Random();
        return rand.nextInt(2) + 1;
    }

    /**
     * Switches the player and returns the int value
     * @param playerNum
     * @return playerNum
     */
    private static int switchPlayer(int playerNum) {
        if(playerNum == 1) {
            playerNum = 2;
        }
        else if(playerNum == 2) {
            playerNum = 1;
        }
        return playerNum;
    }

    /**
     * Returns an int (0-3) representing different endgame conditions
     * @param player1
     * @param player2
     * @param boneyard
     * @return int value
     */
    private int checkGameState(Player player1, Player player2, Boneyard boneyard) {
        if(player1.getHand() == null) {
            return 1;
        }
        if(player2.getHand() == null) {
            return 2;
        }
        if(boneyard.getDominoes() == null) {
            return 3;
        }
        else {
            return 0;
        }
    }
}
