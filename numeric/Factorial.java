package numeric;

/**
* This class computes the factorial of a non-negative integer.
*
* @author Brandon Aikman
* @version 1.0
* File: Factorial.java
* Created: Sep 2025
*
* Description: This class computes the factorial of a non-negative integer iteratively.
*/

public class Factorial {
    /**
     * Main function which handles I/O from the command line.
     *
     * @param args one integer arguments to compute the factorial of.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect number of command-line parameters! Try again with just one.");
        } else if (Integer.parseInt(args[0]) < 0) {
            System.out.println("Cannot compute factorial of a negative number! Try again with a positive number.");
        } else { 
            System.out.println(computeFactorial(Integer.parseInt(args[0])));
        }
    }

    /**
     * computeFactorial function, which returns the double factorial of an integer argument x.
     *
     * @param x integer argument to compute the factorial of.
     * @return the factorial computed.
     */
    public static double computeFactorial(int x) {
        double factorial = 1.0;
        if (x == 0 || x == 1) {
            return 1.0;
        } else {
            while (x != 1) {
                factorial *= (double)x;
                x--;
            }
        }
        return factorial;
    }
}
