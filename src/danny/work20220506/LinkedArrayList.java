package danny.work20220506;

import java.util.*;

public class LinkedArrayList<T> implements List<T> {
    private final int arraySize;
    private int totalSize = 0;
    private ListNode head = null;

    public LinkedArrayList() {
        this.arraySize = 100;
    }

    public LinkedArrayList(int arraySize) {
        this.arraySize = arraySize;
    }

    @Override
    public int size() {
        return totalSize;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        ListNode current = head;
        while (current != null) {
            if (current.contains(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public boolean add(T t) {
        if (head == null) {
            head = new ListNode();
            return head.add(t);
        } else {
            // Find first non-full ListNode.
            ListNode current = head;
            while (current.next != null) {
                if (current.size < arraySize) {
                    return current.add(t);
                }
                current = current.next;
            }
            // current is the last ListNode and is completely full - so create new ListNode.
            ListNode last = new ListNode();
            current.next = last;
            return last.add(t);
        }
    }

    @Override
    public boolean remove(Object o) {
        ListNode node = head;
        while (node != null) {
            if (node.remove(o)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public T get(int index) {
        if (index > totalSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int indexCount = 0;
        ListNode current = head;
        while (current.size - 1 + indexCount < index) {
            indexCount += current.size - 1;
            current = current.next;
        }
        int indexOfArr = index - indexCount;
        return (T)current.arr[indexOfArr];
    }

    @Override
    public T set(int index, T element) {
        if (index > totalSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int indexCount = 0;
        ListNode current = head;
        while (current.size - 1 + indexCount < index) {
            indexCount += current.size - 1;
            current = current.next;
        }
        int indexOfArr = index - indexCount;
        current.arr[indexOfArr] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index > totalSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int indexCount = 0;
        ListNode current = head;
        while (current.size - 1 + indexCount < index) {
            indexCount += current.size - 1;
            current = current.next;
        }
        int indexOfArr = index - indexCount;
        if (current.arr[indexOfArr] == null) {
            current.arr[indexOfArr] = element;
        } else {

        }
    }

    @Override
    public T remove(int index) {
        if (index > totalSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int indexCount = 0;
        ListNode current = head;
        while (current.size - 1 + indexCount < index) {
            indexCount += current.size - 1;
            current = current.next;
        }
        int indexOfArr = index - indexCount;
        T t = (T)current.arr[indexOfArr];
        current.remove(indexOfArr);
        return t;
    }

    @Override
    public int indexOf(Object o) {
        ListNode current = head;
        int index = -1;
        while (current != null) {
            for (int i = 0; i < current.size; i++) {
                index += 1;
                if (current.arr[i].equals(o)) {
                    return index;
                }
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        ListNode current = head;
        int currentIndex = -1;
        int foundIndex = -1;
        while (current != null) {
            for (int i = 0; i < current.size; i++) {
                currentIndex += 1;
                if (current.arr[i].equals(o)) {
                    foundIndex = currentIndex;
                }
            }
            current = current.next;
        }
        return foundIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public T previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(T t) {

            }

            @Override
            public void add(T t) {

            }
        };
    }



    // Unsupported methods.

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }
    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }


    final class ListNode {
        private final Object[] arr = new Object[arraySize];
        private ListNode next;
        private int size = 0;

        private boolean add(Object o) {
            for (int i = 0; i < arraySize; i++) {
                if (arr[i] == null) {
                    arr[i] = o;
                    this.size += 1;
                    totalSize += 1;
                    return true;
                }
            }
            return false;
        }

        private boolean remove(Object o) {
            for (int i = 0; i < arraySize; i++) {
                if (arr[i].equals(o)) {
                    return remove(i);
                }
            }
            return false;
        }

        private boolean remove(int i) {
            size -= 1;
            totalSize -= 1;
            // shift all subsequent els in array one to left until we reach null elements
            for (int j = i; j < arraySize - 1; j++) {
                if (arr[j] == null) {
                    return true;
                }
                arr[j] = arr[j + 1];
            }
            return true;
        }

        private boolean contains(Object o) {
            for (Object value : arr) {
                if (value.equals(o)) {
                    return true;
                }
            }
            return false;
        }
    }

}
