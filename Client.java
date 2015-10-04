/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Dillon
 */
public class Client {
    public static void main(String[] args) throws IOException {
        try {
            Socket clientSocket = new Socket("localhost", 4000);
            try {       
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(),true);;
                BufferedReader in = new BufferedReader(new InputStreamReader
                        (clientSocket.getInputStream()));
                Scanner inStream = new Scanner(in);
                System.out.println("Enter name: ");
                Scanner scanner = new Scanner(System.in);
                String player = scanner.nextLine();
                out.println(player);
                String choice;
                do {
                    System.out.println("Play rock, paper, or scissors: ");
                    Scanner scanner_ = new Scanner(System.in);
                    choice = scanner_.nextLine();
                    out.println(choice);

                    String serverChoice = inStream.nextLine();
                    System.out.println("Player move: "+choice+
                            " Server move: "+serverChoice);
                    String winner = inStream.nextLine();
                    if(winner.equals("Draw")) {
                        System.out.println("Draw! Re-play!");
                    } else {
                        System.out.println(winner);
                    }
                } while(inStream.hasNextLine());
            } finally {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

