package edu.gonzaga;

import java.util.Random;

public class Game {
    /**
     * Driver class with main function and game loop
     */
    public static void main(String[] args) {
        int totalScore = 0;
        while(totalScore < 20) {
            Game newGame = new Game();
            totalScore += newGame.playGame();
        }

    }

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
        int turnNum = 1;
        while(!gameOver) {
            int checkGame;
            if(playerNum == 1) {
                player1.playTurn(turnNum, playerNum, newBoneyard, newBoard);
                System.out.println();
                //Change turns
                playerNum = switchPlayer(playerNum);
            }
            else if(playerNum == 2) {
                player2.playTurn(turnNum, playerNum, newBoneyard, newBoard);
                 {

                }
                //Change turns
                playerNum = switchPlayer(playerNum);
            }
            checkGame = checkGameState(player1, player2, newBoneyard);
            if(checkGame == 1) {
                score = player2.scoreHand();
                gameOver = true;
            }
            if(checkGame == 2) {
                score = player1.scoreHand();
                gameOver = true;
            }
            if(checkGame == 3) {
                if(player1.scoreHand() < player2.scoreHand()) {
                    score = player2.scoreHand() - player1.scoreHand();
                }
                else {
                    score = player1.scoreHand() - player2.scoreHand();
                }
                gameOver = true;
            }
            else {
                turnNum++;
            }
        }
        return score;
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
