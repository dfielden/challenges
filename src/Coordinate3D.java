public final class Coordinate3D {
    private final int x;
    private final int y;
    private final int z;

    public Coordinate3D(int x, int y, int z) {
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

    /** Returns the euler distance. */
    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Coordinate3D add(Coordinate3D c) {
        return new Coordinate3D(this.x + c.x, this.y + c.y, this.z + c.z);
    }

    public static Coordinate3D add(Coordinate3D a, Coordinate3D b) {
        return new Coordinate3D(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + ", z=" + z + "]";
    }

    @Override
    public boolean equals(Object compared) {
        if (!(compared instanceof Coordinate3D)) {
            return false;
        }
        if (this == compared) {
            return true;
        }
        Coordinate3D c = (Coordinate3D) compared;
        return c.x == this.x && c.y == this.y && c.z == this.z;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x) * 31 + Integer.hashCode(y) + Integer.hashCode(z);
    }
}
