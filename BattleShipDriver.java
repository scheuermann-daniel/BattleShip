// Daniel Scheuermann
// CS 110 OL

/**
 * Driver for battleship game
 */
 
// Imports
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
 
public class BattleShipDriver {
    
   // Keep track of player moves made
   public static ArrayList<String> movesMade = new ArrayList<String>();
      
   public static void main (String[] args) throws IOException {
      
      // Variables
      
         // Variable to hold computer move information
      String[] temp = new String[2];
      
         // keyboard to get user input
      Scanner keyboard = new Scanner(System.in);
      
         // for turn variable, 0 is player, 1 is computer
      int turn = 0;
         
        // move player enters
      String move;
      
      // Introduction
      System.out.println("\n--------------------------");
      System.out.println("  Welcome to battleship!");
      System.out.println("--------------------------");
      
      // Start the game
      Game game = new Game();
      
      // Flip Coin
      Random rand = new Random();
      if (rand.nextInt(2) == 0) {
         System.out.println("You won the coin toss and get to go first.\n");
      } else {
         System.out.println("The computer won the coin toss and gets to go first.\n");
         turn = 1;
      }
      
      
      // Play game
      
      while (!game.computerDefeated() && !game.playerDefeated()) {
         if (turn == 0) {
             
            System.out.print("Your turn: ");
            
            // Validate move
            move = keyboard.nextLine();
            
            while (!isValid(move)) {
                System.out.print("Move is not valid, please try again! ");
                move = keyboard.nextLine();
            }
            
            // Formatting
            System.out.println("");
            
            // add move to array
            movesMade.add(move);
            
            // Make msg variable
            String msg = game.makePlayerMove(move);
            
            // Validate
            if (msg != null) {
                System.out.println(msg);
                System.out.println("");
            }
            
            System.out.println(game.toString());
            turn = 1;
         }
         
         if (turn == 1) {
            // Get confirmation to continue.
            System.out.println("Computer's turn. Press enter to continue");
            
            // Keep trying till they press 'enter'
            boolean valid = false;
            while (!valid) {
                try {
                    keyboard.nextLine();
                    valid = true;
                } catch (Exception e){
                    valid = false;
                }
            }
            
            // Make temporary holding variable
            temp = game.makeComputerMove();
            
            // Print what computer chose
            System.out.println("Computer Chose : " + temp[0] + "\n");
            
            // Print results
            if (temp[1] != null) {
                System.out.println(temp[1]);
                System.out.println("");
            }
            
            System.out.println(game.toString());
            turn = 0;
         }
         
      }
      
      if (game.computerDefeated()) {
          // Win statement
          System.out.println("---------");
          System.out.println(" You Won!");
          System.out.println("---------");        
      } else {
          // Loss statement
          System.out.println("---------");
          System.out.println(" You Lost!");
          System.out.println("---------"); 
      }
      
      // close keyboard
      keyboard.close();
      
    }
      
    /**
     * isValid
     * @param String move to make
     * @return boolean true if valid false otherwise
     */
    private static boolean isValid (String m) {
        try {
            // Make Move
            Move move = new Move(m);
          
            // Validate
            if (move.row() >= 0 && move.row() < 10 && move.col() >= 0 && move.col() < 10) {
                if (!movesMade.contains(m)) {
                    return true;
                }
            }
            
            // Results
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}