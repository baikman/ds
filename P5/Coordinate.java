package P5;

/**
* This class defines Coordinate for use with MazeSolver
*
* @author Brandon Aikman
* @version 1.0
* File: Coordinate.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class defines Coordinate.
*/
public class Coordinate {

    /**
     * Coordinate object constructor.
     * 
     * @param r Row integer.
     * @param c Column interger.
     */
    public Coordinate(int r, int c) {
        row = r;
        col = c;
    }

    /**
     * printCoord method, which formats and prints a Coordinate object.
     *
     */
    public void printCoord() {
        System.out.print('<');
        System.out.print(row);
        System.out.print(' ');
        System.out.print(col);
        System.out.println('>');
    }

    protected int row;
    protected int col;
}