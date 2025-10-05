package P3;

import java.util.Stack;
import java.util.Scanner;

public class rpn {
    static Stack<Double> stk = new Stack<>();

    public static void main (String args[]) {
        String test = "5 10 / 2 *";
        System.out.println(rpnCalculate(test));
    }

    public static double rpnCalculate(String input) {
        Scanner scan = new Scanner(input);

        double inp1, inp2;

        String next;
 
        while (scan.hasNext()) {
            if (scan.hasNextInt() || scan.hasNextDouble()) {
                next = scan.next();
                System.out.println(next);
                stk.push(Double.parseDouble(next));
            }
            else {
                next = scan.next();
                System.out.println(next);
                if (next.equals("+")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to add two numbers with only one in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        stk.push(inp2 + inp1);
                    }
                } else if (next.equals("-")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to subtract two numbers with only one in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        stk.push(inp2 - inp1);
                    }
                } else if (next.equals("*")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to multiply two numbers with only one in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        stk.push(inp2 * inp1);
                    }
                } else if (next.equals("/")) {
                    if (stk.size() < 2) throw new InvalidRPNString("Tried to divide two numbers with only one in the stack.");
                    else {
                        inp1 = stk.pop();
                        inp2 = stk.pop();
                        if (inp1 == 0.0) throw new InvalidRPNString("Tried to divide by zero; invalid.");
                        stk.push(inp2 / inp1);
                    }
                } else throw new InvalidRPNString("Invalid input, expected +, -, *, or /.");
            }
        }
        
        scan.close();

        if (stk.size() == 1) return stk.pop();
        else throw new InvalidRPNString("Invalid size");
    }
}

