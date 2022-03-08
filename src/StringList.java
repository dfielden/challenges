import java.util.Iterator;

public final class StringList {

    public static void main(String[] args) {
        StringList stringList = new StringList();

        stringList.add("hello");
        System.out.println(stringList.contains("hello"));
        System.out.println(stringList.contains("egg"));
        System.out.println(stringList.contains("zebra"));
        System.out.println(stringList.size());
        stringList.printAll();
        System.out.println("---------");

        stringList.add("egg");
        System.out.println(stringList.contains("hello"));
        System.out.println(stringList.contains("egg"));
        System.out.println(stringList.contains("zebra"));
        System.out.println(stringList.size());
        stringList.printAll();
        System.out.println("---------");

        stringList.add("zebra");
        System.out.println(stringList.contains("hello"));
        System.out.println(stringList.contains("egg"));
        System.out.println(stringList.contains("zebra"));
        System.out.println(stringList.size());
        stringList.printAll();
        System.out.println("---------");

        stringList.add("fridge");
        System.out.println(stringList.size());
        stringList.printAll();
        System.out.println("---------");

        stringList.add("apple");
        System.out.println(stringList.size());
        stringList.printAll();
        System.out.println("---------");

        stringList.add("robot");
        System.out.println(stringList.size());
        stringList.printAll();
        System.out.println("---------");

    }

    private StringNode head = null;

    public void add(String s) {
        StringNode nodeToAdd = new StringNode(s);
        if (head == null) {
            head = nodeToAdd;
            // the String we are adding comes before the string at the head of the list
        } else if (s.compareTo(head.s) < 0) {
            nodeToAdd.next = head;
            head = nodeToAdd;
        } else {
            StringNode current = head;
            StringNode prev = head;
            while (s.compareTo(current.s) > 0) {
                // the String we are adding comes at the end of the list
                if (current.next == null) {
                    current.next = nodeToAdd;
                    return;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
            // add String in the middle of the list
            prev.next = nodeToAdd;
            nodeToAdd.next = current;
        }
    }

    public boolean contains(String s) {
        if (head == null) {
            return false;
        }
        StringNode current = head;
        if (current.s.equals(s)) {
            return true;
        }
        while (current.next != null) {
            current = current.next;
            if (current.s.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void printAll() {
        System.out.println(this.toString());
    }

    public int size() {
        if (head == null) {
            return 0;
        }
        int count = 1;
        StringNode current = head;
        while (current.next != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }

    Iterator<String> iterator() {
        return new Iterator<String>() {
            StringNode current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public String next() {
                if (hasNext()) {
                    String s = current.s;
                    current = current.next;
                    return s;
                }
                return null;
            }
        };
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        StringNode current = head;
        sb.append(current.s);
        while (current.next != null) {
            sb.append(" --> ");
            sb.append(current.next.s);
            current = current.next;
        }
        return sb.toString();
    }

    private final class StringNode {
        private final String s;
        private StringNode next;

        StringNode(String s) {
            this.s = s;
        }
    }
}
