/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Server class to manage server connections/sockets
 * @author Dillon
 */
public class Server {
    
    private static String PLAYER_NAME;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      try {
          /* create new server socket on open port */
          ServerSocket serverSocket = new ServerSocket(4000);
          /* accept the client socket connection */
          Socket clientSocket = serverSocket.accept();
        try {
            /* create in and out using inputstream and outputstream */
            BufferedReader in = new BufferedReader(new InputStreamReader
                (clientSocket.getInputStream()));
            Scanner scanner = new Scanner(in);
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            /* get the server move for game */
            String serverMove = getMove();
            out.println(serverMove);
            /* get the player name */
            PLAYER_NAME = scanner.nextLine();
            System.out.println(PLAYER_NAME);
            /* get the player move */
            String playerMove = scanner.nextLine();
            System.out.println(playerMove);
            /* determine the round winner and put to printwriter */
            String roundWin = winner(playerMove, serverMove);
            out.println(roundWin);
            /* close the socket when finished */
            } finally {
                clientSocket.close();
            }    
        /* catch any exceptions */
        } catch(IOException e) {
          e.printStackTrace();
        }
    }
    
    /** 
     * Method to get the move of the server
     * @return the move of the server (rock, paper, or scissors)
     */
    public static String getMove() {
        /* create random num generator with 3 options */
        Random random = new Random();
        int randomNum = random.nextInt((3-0)+0);
        String move = null;   
        if(randomNum == 0) {
            move = "rock";
        } else if (randomNum == 1) {
            move = "paper";
        } else if (randomNum == 2) {
            move = "scissors";
        }
        return move;
    }
    
    /**
     * Method to determine winner between the server and player
     * @param playerMove the player move
     * @param serverMove the server move
     * @return the print statement showing the winner
     */
    public static String winner(String playerMove, String serverMove) {
        String winner;
        if(serverMove.equals(playerMove)) {
            winner = "Draw";
        } else if(serverMove.equals("rock") && playerMove.equals("scissors")) {
            winner = "The Server wins!";
        } else if (serverMove.equals("scissors") && playerMove.equals("paper")) {
            winner = "The Server wins!";
        } else if (serverMove.equals("paper") && playerMove.equals("rock")) {
            winner = "The Server wins!";
        } else {
            winner = PLAYER_NAME +" wins!";
        }
        return winner;
    }
}

