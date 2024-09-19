import java.util.Objects;

public class Coordinates {
    int x, y;

//    @Override public int hashCode() {
//        return (x * 31) ^ y;
//    }
//
//    @Override public boolean equals(Object o) {
//        if (o instanceof Coordinates) {
//            Coordinates p = (Coordinates) o;
//            return (p.x == x) && (p.y == y);
//        }
//        return false;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Coordinates copy() {
        return new Coordinates(x, y);
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
