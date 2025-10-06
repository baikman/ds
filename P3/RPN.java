package P3;

import java.util.Stack;
import java.util.Scanner;

/**
* This class calculates the value of a string in reverse-Polish notation.
*
* @author Brandon Aikman
* @version 1.0
* File: RPN.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class calculates the value of a string in reverse-Polish notation.
*/

public class RPN {

    /**
     * RPNCalculate method, which calculates the value of a string in reverse-Polish notation.
     *
     * @param input String to calculate the value of.
     * @return the result of the RPNCalculate function.
     */
    public static double RPNCalculate(String input) {
        Stack<Double> stk = new Stack<>();  // Stack to hold doubles
        Scanner scan = new Scanner(input);  // Scanner that parses input string
        double inp1, inp2;                  // Double holders for two inputs to any operation
        String next;                        // String to hold the next value from scan
 
        while (scan.hasNext()) {
            // Check if the next value is an int or double
            if (scan.hasNextInt() || scan.hasNextDouble()) {
                next = scan.next();
                stk.push(Double.parseDouble(next));
            }
            // If not int or double, check if it is an operator or invalid symbol
            else {
                next = scan.next();
                if (next.equals("+")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to add two numbers with less than two items in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        stk.push(inp2 + inp1);
                    }
                } else if (next.equals("-")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to subtract two numbers with less than two items in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        stk.push(inp2 - inp1);
                    }
                } else if (next.equals("*")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to multiply two numbers with less than two items in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        stk.push(inp2 * inp1);
                    }
                } else if (next.equals("/")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to divide two numbers with less than two items in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        if (inp1 == 0.0) throw new InvalidRPNString("Tried to divide by zero; invalid.");
                        stk.push(inp2 / inp1);
                    }
                } else throw new InvalidRPNString("Invalid input, expected a number, +, -, *, or /.");
            }
        }
        
        scan.close(); // Close scanner
        
        // Return the only value on stack, if the size is one        
        if (stk.size() == 1) return stk.pop(); 
        else throw new InvalidRPNString("Invalid size remaining on the stack.");
    }
}