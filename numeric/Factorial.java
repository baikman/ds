package numeric;

public class Factorial {
    // Main function which handles I/O.
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect number of command-line parameters! Try again with just one.");
        } else if (Integer.parseInt(args[0]) < 0) {
            System.out.println("Cannot compute factorial of a negative number! Try again with a positive number.");
        } else { 
            System.out.println(computeFactorial(Integer.parseInt(args[0])));
        }
    }

    // computeFactorial function, which returns the double factorial of an integer argument x.
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
