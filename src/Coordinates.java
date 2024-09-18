public class Coordinates {
    int x, y;

    public Coordinates copy() {
        return new Coordinates(x, y);
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
