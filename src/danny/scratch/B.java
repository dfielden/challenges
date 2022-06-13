package danny.scratch;

public class B extends A {

    public B() {
        System.out.println("B");
    }

    public static int staticInt() {
        return 2;
    }

    @Override
    public int classInt() {
        return 20;
    }
}
