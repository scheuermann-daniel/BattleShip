// Daniel Scheuermann
// CS 110 OL

/**
 * Fleet class, representing a players fleet.
 */

public class Fleet {
   
   // Instance Variables
   
   private Ship battleship;
   private Ship aircraftCarrier;
   private Ship cruiser;
   private Ship sub;
   private Ship destroyer;
   
   
   // Constructors
   
   public Fleet () {
      this.battleship = new Battleship();
      this.aircraftCarrier = new AircraftCarrier();
      this.cruiser = new Cruiser();
      this.sub = new Submarine();
      this.destroyer = new Destroyer();
   }
   
   
   // Getters
   
   /**
    * getBattleship
    * @return battleship reference
    */
    public Ship getBattleship () {
      return battleship;
    }
    
    /**
    * getAircraftCarrier
    * @return aircraftCarrier reference
    */
    public Ship getAircraftCarrier () {
      return aircraftCarrier;
    }
    
    /**
    * getCruiser
    * @return cruiser reference
    */
    public Ship getCruiser () {
      return cruiser;
    }
    
    /**
    * getSub
    * @return submarine reference
    */
    public Ship getSub () {
      return sub;
    }
    
    /**
    * getDestroyer
    * @return destroyer reference
    */
    public Ship getDestroyer () {
      return destroyer;
    }
   
   
   // Other Methods
   
   /**
    * updateFleet
    * @param ship - type of ship to provide an update on.
    * @return boolean - informs ship that it has been hit, then return true of false based on whether or not it has been sunk.
    */
    public boolean updateFleet (ShipType ship) {
      // Make ship value
      Ship shipType =  enumToShip(ship);
      
      // Indicate Ship has been hit
      shipType.hit();
      
      // If Sunk, indicate it
      if (shipType.getSunk()) {
         return true;
      }
      
      return false;
    }
    
    /**
     * gameOver
     * @return boolean - return boolean based on whether or not all ships have been sunk.
     */
    public boolean gameOver () {
      if (battleship.getSunk() && destroyer.getSunk() && cruiser.getSunk() && aircraftCarrier.getSunk() && sub.getSunk()) {
         return true;
      }
      return false;
    }
    
    /**
     * enumToShip
     * @param shipType - ship type
     * @return Ship Object
     */
    public Ship enumToShip (ShipType shipType) {
        if (shipType == ShipType.ST_AIRCRAFT_CARRIER) {
            return aircraftCarrier;
        } else if (shipType == ShipType.ST_BATTLESHIP) {
            return battleship;
        } else if (shipType == ShipType.ST_CRUISER) {
            return cruiser;
        } else if (shipType == ShipType.ST_DESTROYER) {
            return destroyer;
        } else {
            return sub;
        }
    }
    
}