package danny.work20220510;

public final class Point3D {
    private final int x;
    private final int y;
    private final int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public double magnitude() {
        return Math.cbrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Point3D add(Point3D p) {
        return new Point3D(this.x + p.x, this.y + p.y, this.z + p.z);
    }

    public static Point3D add(Point3D p1, Point3D p2) {
        return new Point3D(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z);
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + ", z: " + x + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Point3D)) {
            return false;
        }
        Point3D p = (Point3D) o;
        return p.x == this.x && p.y == this.y && p.z == this.z;
    }

    @Override
    public int hashCode() {
        return 31 + Integer.hashCode(x) + Integer.hashCode(y) + Integer.hashCode(z);
    }
}
