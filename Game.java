// Daniel Scheuermann
// CS 110 OL

/**
 * Game class for BattleShip Game
 */
 
// Imports
import java.util.Random;
import java.io.*;
 
public class Game {
    
   // Instance Variables
   
   private ComputerBoard computer;
   private UserBoard player;
   
   
   // Constructors
   
   public Game () throws IOException {
      this.computer = new ComputerBoard("compFleet.txt");
      this.player = new UserBoard("userFleet.txt");
   }
   
   
   // Moves
   
   /**
    * makeComputerMove
    * @return Array containing the move and the result
    */
   public String[] makeComputerMove () {
          
     // Call UserBoards makeComputerMove method and return it
     return player.makeComputerMove();       
   }
   
   /**
    * makePlayerMove
    * @param move - move to make
    * @return results of move
    */
   public String makePlayerMove (String move) {
      return computer.makePlayerMove(new Move(move));
   }
   
   
   // Check to see if anyone is defeated
   
   /**
    * computerDefeated
    * @return boolean true or false based on if computer is defeated
    */
   public boolean computerDefeated () {
      return computer.getFleet().gameOver();
   }
   
   /**
    * playerDefeated
    * @return boolean true or false based on if player is defeated
    */
   public boolean playerDefeated () {
      return player.getFleet().gameOver();
   }
   
   
   // toString
   
   /**
    * toString
    * @return string representation of boards
    */
   public String toString () {
      String str = "Computer Board\n";
      str += computer.toString();
      str += "\nPlayer Board\n";
      str += player.toString();
      str += "\n";
      
      return str;
   }
   
   
}