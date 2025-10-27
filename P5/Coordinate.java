package P5;

public class Coordinate {

    public Coordinate(int r, int c) {
        row = r;
        col = c;
    }

    public void printCoord() {
        System.out.print('<');
        System.out.print(row);
        System.out.print(' ');
        System.out.print(col);
        System.out.println('>');
    }

    public int row;
    public int col;
}
