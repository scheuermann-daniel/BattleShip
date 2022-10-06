// Daniel Scheuermann
// CS 110 OL

/**
 * Move class, holding the information for a players turn.
 */
 
public class Move {

   // Variables
   
   // Instance Variables
   
   private int row;
   private int col;
   
   // Constants
   
   final String LETTERS = "ABCDEFGHIJ";
   
   
   // Constructors
   
   /**
    * @param row - row of guess
    * @param col - col of guess
    */
   public Move (int row, int col) {
      this.row = row;
      this.col = col;
   }
   
   /**
    * @param guess - String guess containing letter and number (ex. A5)
    */
   public Move (String guess) {
      try { 
      this.row = LETTERS.indexOf((guess.substring(0,1)).toUpperCase());
      this.col = Integer.parseInt(guess.substring(1))-1;
      } catch (Exception e) {
        this.row =  -1;
        this.col = -1;
     }
   }
   
   
   // Getters
   
   /**
    * row
    * @return row of move
    */
   public int row () {
      return row;
   }
   
   /**
    * col
    * @return column of move
    */
   public int col () {
      return col;
   }
   
   
   // Other Methods
   
   /**
    * toString
    * @return string representation of a move
    * Rows are Letters, Columns are letters. Format is 'column x row'.
    * Example: 'A5'
    */
   public String toString () {
       return (LETTERS.substring(row, row+1)) + (this.col+1);
   }
}