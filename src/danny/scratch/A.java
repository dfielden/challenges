package danny.scratch;

public abstract class A implements AA {
    public A() {
        System.out.println("A");
    }
    public static int staticInt() {
        return 1;
    }

    public int classInt() {
        return 10;
    }

    public abstract String getString();

    public String interfaceString() {
        return "hi";
    }

    @Override
    public void finalize() {
        System.out.println("good danny");
    }

}
