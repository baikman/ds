package numeric;

/**
* This class computes the greatest common divisor of two integers.
*
* @author Brandon Aikman
* @version 1.0
* File: GCD.java
* Created: Sep 2025
*
* Description: This class computes the greatest common divisor of two integers using recursive function calls.
*/

public class GCD {
    /**
     * Main function which handles I/O from the command line.
     *
     * @param args two integer arguments to compute the GCD from.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of command-line parameters! Try again with two.");
        } else {
            System.out.println(computeGCD(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
        }
    }
    
    /**
     * computeGCD function, which returns the integer greatest common divisor.
     *
     * @param x first integer to compute GCD of.
     * @param y second integer to compute GCD of.
     * @return the result of the GCD function.
     */
    public static int computeGCD(int x, int y) {
        if (y == 0) {
            if (x > 0) {
                return x;
            } else {
                return -x;
            }
        } else {
            return computeGCD(y, x % y);
        }
    }
}
