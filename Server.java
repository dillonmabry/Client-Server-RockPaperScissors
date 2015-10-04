///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Dillon
 */
public class Server {
    
    private static String PLAYER_NAME;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      try {
          ServerSocket serverSocket = new ServerSocket(4000);
          Socket clientSocket = serverSocket.accept();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader
                (clientSocket.getInputStream()));
            Scanner scanner = new Scanner(in);
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            String serverMove = getMove();
            out.println(serverMove);
            
            PLAYER_NAME = scanner.nextLine();
            System.out.println(PLAYER_NAME);
            
            String playerMove = scanner.nextLine();
            System.out.println(playerMove);
            
            String roundWin = winner(playerMove, serverMove);
            out.println(roundWin);
            
            } finally {
                clientSocket.close();
            }    
        } catch(IOException e) {
          e.printStackTrace();
        }
    }
    
    public static String getMove() {
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

