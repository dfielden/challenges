package danny.work20220225;

import java.util.*;

public final class IntersectionSet<T> extends AbstractCollection<T> implements Set<T> {
    private final Set<T> underlyingSet1;
    private final Set<T> underlyingSet2;

    public IntersectionSet(Set<T> underlyingSet1, Set<T> underlyingSet2) {
        this.underlyingSet1 = underlyingSet1;
        this.underlyingSet2 = underlyingSet2;
    }

    @Override
    public int size() {
        return createIntersectionSet().size();
    }

    @Override
    public boolean isEmpty() {
        for (T item : this.underlyingSet1) {
            if (this.underlyingSet2.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        Set<T> intersectionSet = createIntersectionSet();
        if (o == null) {
            return false;
        }
        return intersectionSet.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        Set<T> intersectionSet = createIntersectionSet();
        return intersectionSet.iterator();
    }

    @Override
    public Object[] toArray() {
        Set<T> intersectionSet = createIntersectionSet();
        return intersectionSet.toArray();
    }
// METHOD IMPLEMENTED BY EXTENDING ABSTRACT COLLECTION
//    @Override
//    public <T1> T1[] toArray(T1[] a) {
//        return null;
//    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Set<T> intersectionSet = createIntersectionSet();
        for (Object item : c) {
            if (!intersectionSet.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    private Set<T> createIntersectionSet() {
        Set<T> intersectionSet = new HashSet<>();
        for (T item : this.underlyingSet1) {
            if (this.underlyingSet2.contains(item)) {
                intersectionSet.add(item);
            }
        }
        return intersectionSet;
    }
}
