// Daniel Scheuermann
// CS 110 OL

/**
 * Board class, representing the players board for the Battle Ship game.
 */
 
 // Imports
 import java.util.*;
 import java.util.Scanner;
 import java.io.File;
 import java.io.*;
 
public class Board {
   
    // Instance Variables
   
    private ArrayList<ArrayList<CellStatus>> layout;
    private Fleet fleet;

    // Size of one dimension of the board
    final int SIZE = 10;
   
   
    // Constructors
   
    /**
     * @param file - file to read from.
     * Initializes layout, setting all cells to CellStatus.NOTHING
     * From file, reads ship to layout
     */
    public Board (String file) throws IOException {
      
        // Start by setting layout to a 1d array with length SIZE
        layout = new ArrayList<>(SIZE);
       
        // Turn it into a 2d array by adding an arraylist to each element in layout
        for (int i = 0; i < SIZE; i++) {
            layout.add(new ArrayList<CellStatus>(SIZE));
        }
        
        // Then, set all cells to CellStatus.NOTHING
        for (int i = 0; i < SIZE; i++) {
            for (int k = 0; k < SIZE; k++) {
                layout.get(i).add(k, CellStatus.NOTHING);            
            }
        }
        
        // Creating a scanner and reading in file
        try {
            Scanner keyboard = new Scanner(new File(file));
            
            // Create an array to place data in
            // First value of each secondary array is the type of ship
            // Second value of each secondary array is starting location
            // Third value of each secondary array is the ending location
            String[][] shipData = {
                                    {keyboard.next(), keyboard.next(), keyboard.next()},
                                    {keyboard.next(), keyboard.next(), keyboard.next()},
                                    {keyboard.next(), keyboard.next(), keyboard.next()},
                                    {keyboard.next(), keyboard.next(), keyboard.next()},
                                    {keyboard.next(), keyboard.next(), keyboard.next()}                
                                  };
            // Add ships to board
            for (int i = 0; i < shipData.length; i++) {
                setShipCells(shipData[i]);
            }
            
            // Close scanner
            keyboard.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        // Instantiate fleet
        fleet = new Fleet();
    }
    
      
   
   
    // Getters
   
    /**
     * getLayout
     * @return the layout of the board
     */
    public ArrayList<ArrayList<CellStatus>> getLayout() {
        return layout;
    }
   
    /**
     * getFleet
     * @return a reference to the fleet object
     */
    public Fleet getFleet() {
        return fleet;
    }
    
    /**
     * gameOver
     * @return a boolean, true if all ships are sunk or false otherwise.
     */
    public boolean gameOver () {
      
        // Call fleet's gameOver method
        return fleet.gameOver();
    }
    
    /**
     * getDirection
     * @param row1 - row of first location
     * @param row2 - row of second location
     * @return true if horizontal, false if vertical  
     */
    public boolean getDirection (int row1, int row2) {
        
        // Compare two rows and return true if they are the same
        if (row1 == row2) {
            return true;
        }
        
        // Otherwise, it's vertical
        return false;
    }
    
    /**
     * empty
     * @param move - a Move object
     * @return true if spot is empty, false if not
     */
    public boolean empty (Move move) {
        // Return whether or not it is empty
        return (layout.get(move.row()).get(move.col())).equals("oo");
        
    }
    
    /**
     * getShipType
     * @param c - String character to deduce ship type from
     * @return CellStatus ship type
     */
    public CellStatus getShipType (String c) {
        if (c.equals("D")) {
            return CellStatus.DESTROYER;
        }
        
        else if (c.equals("C")) {
            return CellStatus.CRUISER;
        }
        
        else if (c.equals("S")) {
            return CellStatus.SUB;
        }
        
        else if (c.equals("B")) {
            return CellStatus.BATTLESHIP;
        }
        
        else if (c.equals("A")) {
            return CellStatus.AIRCRAFT_CARRIER;
        }
        
        else {
            return null;
        }
    }
    
    /**
     * getShipHit
     * @param ship - a string of ship type
     * @return CellStatus type of ship hit
     */
    public CellStatus getShipHit(String ship) {
        if (ship.equals("oA")) {
            return CellStatus.AIRCRAFT_CARRIER_HIT;
        }
        
        else if (ship.equals("oB")) {
            return CellStatus.BATTLESHIP_HIT;
        }
        
        else if (ship.equals("oC")) {
            return CellStatus.CRUISER_HIT;
        }
        
        else if (ship.equals("oD")) {
            return CellStatus.DESTROYER_HIT;
        }
            
        else {
            return CellStatus.SUB_HIT;
        }
    }
    
    // Setters
    
    /**
     * setShipCells
     * @param location - String array with information of ship location
     * Sets ships location on the board
     */
    public void setShipCells (String[] location) {
        
        // Variables for start/end locations for ships
        Move space1 = new Move(location[1]);
        Move space2 = new Move(location[2]);
        
        // Get type of ship
        CellStatus type = getShipType(location[0]);
        
        // Get direction of ship
        boolean dir = getDirection(space1.row(), space2.row());
        
        // Set CellStatus based on dir
        // If horizontal, traverse through columns and replace cells
        if (dir) {
            for (int col = space1.col(); col <= space2.col(); col++) {
                layout.get(space1.row()).set(col, type);
            }
        }
        
        // If vertical, traverse through rows and replace cells
        else {
            for (int row = space1.row(); row <= space2.row(); row++) {
                layout.get(row).set(space1.col(), type);
            }
        }
        
    }
   
    
    // Other Methods
   
    /**
     * moveAvailable
     * @param Move - Move to test for availability
     * @return boolean whether of not that move is valid
     */
    public boolean moveAvailable (Move move) {
        // Put cell in variable and turn into string
        String cell = layout.get(move.col()).get(move.row()).toString();
        
        // If Nothing is there, return true. All unhit cells start with 'o'
        if (cell.substring(0,1).equals("o")) {
            return true;
        }
      
        return false;
    }
    
    /**
     * applyMoveToLayout
     * @param move - Spot player wishes to hit
     * @return Cell status of the spot after move is made
     */
    public CellStatus applyMoveToLayout (Move move) {
      
        // Get Cell to work with
        CellStatus cell = layout.get(move.row()).get(move.col());
        
        // If CellStatus is NOTHING, replace with NOTHING_HIT
        if (empty(move) && moveAvailable(move)) {
            layout.get(move.row()).set(move.col(), CellStatus.NOTHING_HIT);
        }
        
        // Otherwise, if there is a ship there, set it's status to hit
        else {   
            layout.get(move.row()).set(move.col(), getShipHit(cell.toString()));
        }
        
        return cell;
    }

    
}