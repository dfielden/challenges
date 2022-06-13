package danny.work20220510;

public final class Point2D {
    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Point2D add(Point2D p) {
        return new Point2D(this.x + p.x, this.y + p.y);
    }

    public static Point2D add(Point2D p1, Point2D p2) {
        return new Point2D(p1.x + p2.x, p1.y + p2.y);
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Point2D)) {
            return false;
        }
        Point2D p = (Point2D) o;
        return p.x == this.x && p.y == this.y;
    }

    @Override
    public int hashCode() {
        return 31 + Integer.hashCode(x) + Integer.hashCode(y);
    }
}
