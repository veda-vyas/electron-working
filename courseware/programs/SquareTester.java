// You are to write the constructor specified for this Square class. The Square class 
// has an instance variable of type double, side, which is the length of each side of 
// the square. The javadoc has been provided for you to help you tell what you needs to
// be done
//     
// HINT: Write the constructor for the class Square.
// The constructor will take in a parameter of the type double
// and assign that parameter to the instance variable side
//
    
    
class Square
{
    private double side;

    /**
     * Constructor for objects of class Square
     * @param theSide the length of the side of this Square
     */


      // your code goes below:
     

      /**
       * Gets the length of a side of this square
       * @return the side of this square
       */
      public double getSide()
      {
          return side;
      }
}

/**
 * A tester for the Square class
 * You don't need to change or modify this file   
 */
public class SquareTester
{
   public static void main(String[] args)
   {
       Square square = new Square(10.4);
       System.out.println(square.getSide());
       System.out.println("Expected: 10.4");
   }
}