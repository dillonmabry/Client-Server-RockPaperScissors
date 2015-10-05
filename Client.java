/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Client class to manage client side connections/ports
 * @author Dillon
 */
public class Client {
    
    public static String choices[] = {"rock", "paper", "scissors"};
    /**
     * Main method
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        try {
            /* create new client socket with open port */
            Socket clientSocket = new Socket("localhost", 4000);
            try {
                /* create out and in with outputstream and inputstream */
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(),true);;
                BufferedReader in = new BufferedReader(new InputStreamReader
                        (clientSocket.getInputStream()));
                /* create stream scanner and normal scanner */
                Scanner inStream = new Scanner(in); 
                Scanner scanner = new Scanner(System.in);
                do {
                    System.out.println("Enter name: ");
                    /*player enters name */
                    String player = scanner.nextLine();
                    out.println(player);
                    String choice = null;
                    while(true) {
                        System.out.println("Play rock, paper, or scissors: ");
                        Scanner choiceScanner = new Scanner(System.in);
                        /* player chooses rock, paper, or scissors */
                        choice = choiceScanner.nextLine().toLowerCase();
                        if(!((choice.toLowerCase().equals(choices[0]))
                                || (choice.toLowerCase().equals(choices[1]))
                                || (choice.toLowerCase().equals(choices[2])))) {
                            System.out.println("Invalid choice, please enter"
                                + " rock, paper, or scissors!"); 
                            continue;
                        } else if (choice.toLowerCase().equals("rock") || 
                                choice.toLowerCase().equals("paper") || 
                                choice.toLowerCase().equals("scissors")) {
                            break;
                        }   
                    }
                    out.println(choice);
                    /* get the server choice */
                    String serverChoice = inStream.nextLine();
                    System.out.println("Player move: "+choice+
                            " Server move: "+serverChoice);
                    String winner = inStream.nextLine();
                    /* if the result is a draw */
                    if(winner.equals("Draw")) {
                        System.out.println("Draw! Re-play!");
                    } else {
                        System.out.println(winner);
                    }
                    System.out.println("");
                    while(true) {
                        System.out.println("Do you want to play again? Y/N");
                        String playAgain = scanner.nextLine();
                        if(playAgain.toLowerCase().equals("y")) {
                            break;
                        } else if(playAgain.toLowerCase().equals("n")) {
                            System.out.println("Exiting....");
                            System.exit(0);
                        } else {
                            System.out.println("");
                            System.out.println("Unknown input choice! "
                                    + " Please enter Y or N");
                            continue;
                        }
                    }
                /* perform only while server side has next input */
                } while(inStream.hasNextLine());
            /* close socket when finished */
            } finally {
                clientSocket.close();
            }
         /* catch any exceptions */
        } catch (IOException e) {
            e.printStackTrace();
        }
      System.exit(0);
    }
}

