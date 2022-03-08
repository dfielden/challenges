
public final class Coordinate2D {
    private final int x;
    private final int y;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /** Returns the euler distance. */
    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Coordinate2D add(Coordinate2D c) {
        return new Coordinate2D(this.x + c.x, this.y + c.y);
    }

    public static Coordinate2D add(Coordinate2D a, Coordinate2D b) {
        return new Coordinate2D(a.x + b.x, a.y + b.y);
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }

    @Override
    public boolean equals(Object compared) {
        if (!(compared instanceof Coordinate2D)) {
            return false;
        }
        if (this == compared) {
            return true;
        }
        Coordinate2D c = (Coordinate2D) compared;
        return c.x == this.x && c.y == this.y;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x) * 31 + Integer.hashCode(y);
    }
}
