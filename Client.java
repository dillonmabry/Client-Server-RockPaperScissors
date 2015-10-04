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
                System.out.println("Enter name: ");
                /*player enters name */
                String player = scanner.nextLine();
                out.println(player);
                String choice;
                do {
                    System.out.println("Play rock, paper, or scissors: ");
                    Scanner choiceScanner = new Scanner(System.in);
                    /* player chooses rock, paper, or scissors */
                    choice = choiceScanner.nextLine();
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
    }
}

