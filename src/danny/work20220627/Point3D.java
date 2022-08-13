package danny.work20220627;

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

    @Override
    public String toString() {
        return String.format("[%d, %d, %d]", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(!(o instanceof Point3D)) {
            return false;
        }
        Point3D p = (Point3D) o;
        return this.x == p.x && this.y == p.y && this.z == p.z;
    }

    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(x) + Integer.hashCode(y) + Integer.hashCode(z);
    }
}
