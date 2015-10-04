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
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            Scanner sc1 = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            String servMove = getMove();
            writer.println(servMove);
            
            PLAYER_NAME = sc1.nextLine();
            System.out.println(PLAYER_NAME);
            
            String playerMove = sc1.nextLine();
            System.out.println(playerMove);
            
            String roundWin = winner(playerMove, servMove);
            writer.println(roundWin);
            
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
    
    public static String winner(String playerMove, String servMove) {
        String winner;
        if(servMove.equals(playerMove)) {
            winner = "Draw";
        } else if(servMove.equals("rock") && playerMove.equals("scissors")) {
            winner = "The Server wins!";
        } else if (servMove.equals("scissors") && playerMove.equals("paper")) {
            winner = "The Server wins!";
        } else if (servMove.equals("paper") && playerMove.equals("rock")) {
            winner = "The Server wins!";
        } else {
            winner = PLAYER_NAME +" wins!";
        }
        return winner;
    }
}

