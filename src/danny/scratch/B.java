package danny.scratch;

public abstract class B extends A {

    public B() {
        System.out.println("B");
    }

    public static int staticInt() {
        return 2;
    }

    public String getString() {
        return "hi";
    }

    @Override
    public int classInt() {
        return 20;
    }
}
