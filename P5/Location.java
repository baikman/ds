package P5;

/**
* This class defines a Location class for use with MazeSolver.
*
* @author Brandon Aikman
* @version 1.0
* File: Location.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class defines a Location class for use with MazeSolver.
*/
public class Location {
    
    /**
     * Location constructor, which constructs a Location object based on the type and coordinate of a maze cell.
     * 
     * @param t character that denotes type of maze cell (S, X, ., T).
     * @param c Coordinate object of maze cell.
     */
    public Location(char t, Coordinate c) {
        parent = null;
        type = t;
        coord = c;
        visited = false;
    }

    /**
     * Location constructor, which constructs a Location object based on another Location object.
     * 
     * @param l Location object to construct new Location object on.
     */
    public Location(Location l) {
        parent = l.parent;
        type = l.type;
        coord = l.coord;
        visited = l.visited;
    }

    /**
     * Default Location constructor.
     * 
     */
    public Location() {
        parent = null;
        type = '0';
        coord = null;
        visited = false;
    }

    protected Location parent;
    protected char type;
    protected Coordinate coord;
    protected boolean visited;
}