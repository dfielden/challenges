package danny.work20220510;

public final class Line {
    private final Point2D p1;
    private final Point2D p2;

    public Line (Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double length() {
        int xLength = Math.abs(p1.getX() - p2.getX());
        int yLength = Math.abs(p1.getY() - p2.getY());
        return Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
    }

    @Override
    public String toString() {
        return "Point1: " + p1.toString() + ", Point2: " + p2.toString() + ", length: " + length();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Line)) {
            return false;
        }
        Line l = (Line) o;
        return (l.p1.equals(this.p1) && l.p2.equals(this.p2)) || (l.p1.equals(this.p2) && l.p2.equals(this.p1));
    }

    @Override
    public int hashCode() {
        return 31 + p1.hashCode() + p2.hashCode();
    }
}
