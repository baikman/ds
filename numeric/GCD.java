package numeric;

public class GCD {
    // Main function which handles I/O.
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of command-line parameters! Try again with two.");
        } else {
            System.out.println(computeGCD(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
        }
    }

    // computeGCD function, which returns the integer greatest common divisor of integers x and y, regardless of sign.
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
