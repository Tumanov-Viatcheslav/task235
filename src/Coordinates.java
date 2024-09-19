public class Coordinates {
    int x, y;

    @Override public int hashCode() {
        return (x * 31) ^ y;
    }

    @Override public boolean equals(Object o) {
        if (o instanceof Coordinates) {
            Coordinates p = (Coordinates) o;
            return (p.x == x) && (p.y == y);
        }
        return false;
    }

    public Coordinates copy() {
        return new Coordinates(x, y);
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
