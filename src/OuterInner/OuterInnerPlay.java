package OuterInner;

public class OuterInnerPlay {
    int a = 1;
    private int b = 2;
    static int c = 4;
    private static int d = 8;

    class Inner {
        // Inner class can access static and instance variables
        // can only be created in context of an instance of outer class
        public void printA() {
            System.out.println(a);
        }

        public void printB() {
            System.out.println(b);
        }

        public void printC() {
            System.out.println(c);
        }

        public void printD() {
            System.out.println(d);
        }
    }

    static class Nested {
        // Static nested class can only access static variables
        // can be created without an instance of outer class

        public void printA() {
            //System.out.println(a);
        }

        public void printB() {
            //System.out.println(b);
        }

        public void printC() {
            System.out.println(c);
        }

        public void printD() {
            System.out.println(d);
        }
    }
}
