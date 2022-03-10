import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public final class StringList {

    private StringNode head = null;

    public void add(String s) {
        StringNode nodeToAdd = new StringNode(s);
        if (head == null) {
            head = nodeToAdd;
            // the String we are adding comes before the string at the head of the list
        } else if (s.compareTo(head.s) < 0) {
            nodeToAdd.next = head;
            head = nodeToAdd;
            // check String at head is not the same as the one we are adding
        } else if (s.compareTo(head.s) != 0) {
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
            // add String in the middle of the list - check not duplicate
            if (current.s.compareTo(s) != 0) {
                prev.next = nodeToAdd;
                nodeToAdd.next = current;
            }
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

    Iterator<String> iterator() {   // Add test
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

    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        Iterator<String> it = iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        StringNode current = head;
        while (current != null) {
            sb.append(current.s);
            if (current.next != null) {
                sb.append(" --> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    final class StringNode {
        private final String s;
        private StringNode next;

        StringNode(String s) {
            this.s = s;
        }

        public String getS() {
            return s;
        }

        public StringNode getNext() {
            return next;
        }
    }
}
