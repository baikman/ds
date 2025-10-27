package P5;

public class Location {

    public Location(char t, Coordinate c) {
        parent = null;
        type = t;
        coord = c;
        visited = false;
    }

    public Location(Location l) {
        parent = l.parent;
        type = l.type;
        coord = l.coord;
        visited = l.visited;
    }

    public Location() {
        parent = null;
        type = '0';
        coord = null;
        visited = false;
    }

    public Location parent;
    public char type;
    public Coordinate coord;
    public boolean visited;
}
