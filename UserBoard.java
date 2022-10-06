// Daniel Scheuermann
// CS 110 OL

/**
 * UserBoard class holding information for the user's fleet.
 */
 
// Imports
import java.util.Random;
import java.io.*;
import java.util.ArrayList;
 
public class UserBoard extends Board {
   
    // Instance Variables
    
    private ArrayList<Move> moves;
    private Random rand;
    
    
    // Constructors
   
    /**
    * @param file - file to read board information from
    */
    public UserBoard (String file) throws IOException {
        // Call super with file
        super(file);
        
        // Create random object
        rand = new Random();
        
        // Initialize all possible moves
        moves = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                moves.add(new Move(i, k )); 
            }
        }
    }
   
   
    // Other Methods
    
    /**
     * pickRandomMove
     * @return random move from moves list
     */
    public Move pickRandomMove () {
        // Make variables and get random move
        int index =  rand.nextInt(moves.size());
        Move move = moves.get(index);
        
        // Remove move to avoid repeats
        moves.remove(index);
        return move;
    }
        
   
     /**
      * makeComputerMove
      * @param move - move to make against user board
      * @return results of move
      */
     public String[] makeComputerMove () {
        // Create array to retun
        String [] arr = new String[2];
         
        // Make Variables for method
        Move compMove = pickRandomMove();
        
        // Add compMove to array
        arr[0] = compMove.toString();
        
        // Apply to layout
        CellStatus cell = this.applyMoveToLayout(compMove);
        String cellString = cell.toString();
        ShipType shipType;
        
        if (cellString.substring(1).equals("A")) {
            shipType = ShipType.ST_AIRCRAFT_CARRIER;
        } else if (cellString.substring(1).equals("B")) {
            shipType = ShipType.ST_BATTLESHIP;
        } else if (cellString.substring(1).equals("C")) {
            shipType = ShipType.ST_CRUISER;
        } else if (cellString.substring(1).equals("D")) {
            shipType = ShipType.ST_DESTROYER;
        } else if (cellString.substring(1).equals("S")) {
            shipType = ShipType.ST_SUB;
        } else {
            shipType = null;
        }
        
        // Hit spot
        boolean isSunk = false;
        if (shipType != null) {
            isSunk = this.getFleet().updateFleet(shipType);
        }
        
        // Get String and set to array
        if (isSunk) {
            sunkLayout(shipType);
            arr[1] = "Your Battleship Was Sunk!";
        } else {
            arr[1] = null;
        }
        
        
        // Return statement
        return arr;
        
     }
     
     /**
      * sunkLayout
      * @param shipType - type of ship
      * Set ships to sunk status
      */
     public void sunkLayout (ShipType shipType) {
         for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                if (shipType.equals(ShipType.ST_AIRCRAFT_CARRIER)) {
                    if (getLayout().get(i).get(k).equals(CellStatus.AIRCRAFT_CARRIER_HIT)) {
                        getLayout().get(i).set(k, CellStatus.AIRCRAFT_CARRIER_SUNK);
                    }
                } else if (shipType.equals(ShipType.ST_BATTLESHIP)) {
                    if (getLayout().get(i).get(k).equals(CellStatus.BATTLESHIP_HIT)) {
                        getLayout().get(i).set(k, CellStatus.BATTLESHIP_SUNK);
                    }
                } else if (shipType.equals(ShipType.ST_CRUISER)) {
                    if (getLayout().get(i).get(k).equals(CellStatus.CRUISER_HIT)) {
                        getLayout().get(i).set(k, CellStatus.CRUISER_SUNK);
                    }                    
                } else if (shipType.equals(ShipType.ST_DESTROYER)) {
                    if (getLayout().get(i).get(k).equals(CellStatus.DESTROYER_HIT)) {
                        getLayout().get(i).set(k, CellStatus.DESTROYER_SUNK);
                    }
                } else {
                    if (getLayout().get(i).get(k).equals(CellStatus.SUB_HIT)) {
                        getLayout().get(i).set(k, CellStatus.SUB);
                    }
                }
            }
        }
     }
   
   
    /**
    * toString
    * @return String representation of user's board
    */
    public String toString () {

        // Letters reference
        String Letters = "ABCDEFGHIJ";
        String board = "   1  2  3  4  5  6  7  8  9  10\n";
        
        for (int i = 0; i < SIZE; i++) {
            // Labels
            board += Letters.substring(i, i+1) + "  ";
            
            // Add each value to board depending on status
            for (int k = 0; k < SIZE; k++) {
                board += this.getLayout().get(i).get(k).toString().charAt(1) + "  ";
            }
            
            // Create new line
            board += ("\n");
          }
       
        // return
        return board; 
    }
}