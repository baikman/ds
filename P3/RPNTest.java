package P3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

/**
* This class uses JUnit to test various calculations and all exception throws from the RPNCalculate method of RPN.
*
* @author Brandon Aikman
* @version 1.0
* File: RPNTest.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class uses JUnit to test various calculations and all exception throws from the RPNCalculate method of RPN.
*/

public class RPNTest {
    @Test
    public void testComplex() {
        assertEquals(-146.3, RPN.RPNCalculate("23.3 5 16.2 + 8 * -"), 0.00001);
    }

    @Test
    public void testAddition() {
        assertEquals(19, RPN.RPNCalculate("7 8.5 + 3.5 +"), 0.00001);
    }

    @Test
    public void testSubtraction() {
        assertEquals(1.5, RPN.RPNCalculate("8.5 7 -"), 0.00001);
    }

    @Test
    public void testSubtractionNegative() {
        assertEquals(-1.5, RPN.RPNCalculate("7 8.5 -"), 0.00001);
    }

    @Test
    public void testMultiplication() {
        assertEquals(12, RPN.RPNCalculate("8 1.5 *"), 0.00001);
    }

    @Test
    public void testDivision() {
        assertEquals(6.0, RPN.RPNCalculate("12 2 /"), 0.00001);
    }

    @Test
    public void divisionByZeroException() {
        InvalidRPNString exception = assertThrows(InvalidRPNString.class, () -> {
            RPN.RPNCalculate("1 0 /");
        });
        assertEquals("Tried to divide by zero; invalid.", exception.getMessage());
    }

    @Test
    public void additionLessThanTwoOnStackException() {
        InvalidRPNString exception = assertThrows(InvalidRPNString.class, () -> {
            RPN.RPNCalculate("1 +");
        });
        assertEquals("Tried to add two numbers with less than two items in the stack.", exception.getMessage());
    }

    @Test
    public void subtractionLessThanTwoOnStackException() {
        InvalidRPNString exception = assertThrows(InvalidRPNString.class, () -> {
            RPN.RPNCalculate("1 -");
        });
        assertEquals("Tried to subtract two numbers with less than two items in the stack.", exception.getMessage());
    }

    @Test
    public void multiplicationLessThanTwoOnStackException() {
        InvalidRPNString exception = assertThrows(InvalidRPNString.class, () -> {
            RPN.RPNCalculate("1 *");
        });
        assertEquals("Tried to multiply two numbers with less than two items in the stack.", exception.getMessage());
    }

    @Test
    public void divisionLessThanTwoOnStackException() {
        InvalidRPNString exception = assertThrows(InvalidRPNString.class, () -> {
            RPN.RPNCalculate("1 /");
        });
        assertEquals("Tried to divide two numbers with less than two items in the stack.", exception.getMessage());
    }

    @Test
    public void incorrectInput() {
        InvalidRPNString exception = assertThrows(InvalidRPNString.class, () -> {
            RPN.RPNCalculate("a b +");
        });
        assertEquals("Invalid input, expected a number, +, -, *, or /.", exception.getMessage());
    }
}