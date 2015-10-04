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
                InputStream in = clientSocket.getInputStream();
                PrintWriter writer = new PrintWriter
                    (clientSocket.getOutputStream(),true);
                Scanner input = new Scanner(in);
                System.out.println("Enter name: ");
                Scanner sc1 = new Scanner(System.in);
                String player = sc1.nextLine();
                writer.println(player);
                String choice;
                do {
                    System.out.println("Play rock, paper, or scissors: ");
                    Scanner sc2 = new Scanner(System.in);
                    choice = sc2.nextLine();
                    writer.println(choice);

                    String serverChoice = input.nextLine();
                    System.out.println("Player move: "+choice+
                            " Server move: "+serverChoice);
                    String winner = input.nextLine();
                    if(winner.equals("Draw")) {
                        System.out.println("Draw! Re-play!");
                    } else {
                        System.out.println(winner);
                    }
                } while(input.hasNextLine());
            } finally {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

