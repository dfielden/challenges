public final class Line {
    private final Coordinate2D c1;
    private final Coordinate2D c2;

    public Line(Coordinate2D c1, Coordinate2D c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public Coordinate2D getC1() {
        return c1;
    }

    public Coordinate2D getC2() {
        return c2;
    }

    public double length() {
        int xLength = Math.abs(c1.getX() - c2.getX());
        int yLength = Math.abs(c1.getY() - c2.getY());
        return Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
    }

    @Override public String toString() {
        return "Coordinate 1: [" + c1.getX() + ", " + c1.getY() + "]\n"
                + "Coordinate 1: [" + c1.getX() + ", " + c1.getY() + "]\n"
                + "length: " + this.length();
    }

    @Override public boolean equals(Object compared) {
        if (!(compared instanceof Line)) {
            return false;
        }
        if (this == compared) {
            return true;
        }
        Line l = (Line) compared;

        return (this.c1.equals(l.c1) && this.c2.equals(l.c2)) || (this.c1.equals(l.c2) && this.c2.equals(l.c1));
    }

    @Override public int hashCode() {
        return this.c1.hashCode() + this.c2.hashCode() * 31;
    }
}
