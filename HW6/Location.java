public class Location {
    public final int row;
    public final int col;
    public Location parent; // predecessor in the path

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
        this.parent = null;
    }

    public Location(int row, int col, Location parent) {
        this.row = row;
        this.col = col;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "<" + row + " " + col + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
