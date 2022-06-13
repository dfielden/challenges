package danny.scratch;

public abstract class A {
    public A() {
        System.out.println("A");
    }
    public static int staticInt() {
        return 1;
    }

    public int classInt() {
        return 10;
    }

    @Override
    public void finalize() {
        System.out.println("good danny");
    }

}
