// Daniel Scheuermann
// CS 110 OL

/**
 * ComputerBoard class holding information for the computer's fleet.
 */
 
// Imports
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
 
public class ComputerBoard extends Board {
   
    // Constructors
   
    /**
    * @param file - file to read board information from
    */
    public ComputerBoard (String file) throws IOException {
        
      super(file);
    }
   
   
    // Other Methods
   
    /**
    * makePlayerMove
    * @param move - move to make against computer board
    * @return results of move
    */
    public String makePlayerMove (Move move) {
        // Cell
        CellStatus cell = this.applyMoveToLayout(move);
        String cellString = cell.toString();
        
        // Get ship type
        ShipType shipType;
        
        // Sort through type
        if (cellString.substring(1).equals("A")) {
            shipType = ShipType.ST_AIRCRAFT_CARRIER;
        } else if (cellString.substring(1).equals("B")) {
            shipType = ShipType.ST_BATTLESHIP;
        } else if (cellString.substring(1).equals("C")) {
            shipType = ShipType.ST_CRUISER;
        } else if (cellString.substring(1).equals("D")) {
            shipType = ShipType.ST_DESTROYER;
        } else {
            shipType = ShipType.ST_SUB;
        }
        
        // Hit spot
        boolean isSunk = getFleet().updateFleet(shipType);
        
        // Get String and set to array
        if (isSunk) {
            sunkLayout(shipType);
            return "You Sank My Battleship!";
        } else {
            return null;
        }
        
    }
    
         /**
      * sunkLayout
      * @param shipType - type of ship
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
     * @return String representation of computer's board
     */
    public String toString () {
       
        // Letters reference
        String Letters = "ABCDEFGHIJ";
        String board = "   1  2  3  4  5  6  7  8  9  10\n";
        
        for (int i = 0; i < SIZE; i++) {
            // Labels
            board += Letters.substring(i, i+1) + "  ";
            
            // Add each Value as O/H/X depending on status
            for (int k = 0; k < SIZE; k++) {
                board += this.getLayout().get(i).get(k).toString().charAt(0) + "  ";
            }
         
            board += ("\n");
          }
       
        return board; 
    }
}