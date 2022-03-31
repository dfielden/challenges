package danny.work20220328;

import java.util.*;
import java.util.function.Function;

public final class TransformingList<T,S> implements List<T> {
    private final Function<T,S> convertIn;
    private final Function<S,T> convertOut;
    private final List<S> items = new ArrayList<>();

    public TransformingList(Function<T,S> convertIn, Function<S,T> convertOut) {
        this.convertIn = convertIn;
        this.convertOut = convertOut;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    @Deprecated
    public boolean contains(Object o) {
        S s = convertIn.apply((T) o);
        return items.contains(s);
    }

    @Override
    public Iterator<T> iterator() {
        return convertOutList().iterator();
    }

    @Override
    public Object[] toArray() {
        return convertOutList().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return convertOutList().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return items.add(convertIn.apply(t));
    }

    @Override
    @Deprecated
    public boolean remove(Object o) {
        S s = convertIn.apply((T) o);
        return items.remove(s);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        List<T> tList = convertOutList();
        return tList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Collection<S> col = colTToColS(c);
        return items.addAll(col);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Collection<S> col = colTToColS(c);
        return items.addAll(index, col);
    }

    @Override
    @Deprecated
    public boolean removeAll(Collection<?> c) {
        List<S> collection = new ArrayList<>();
        for (Object o : c) {
            T t = (T)o;
            collection.add(convertIn.apply(t));
        }
        return items.removeAll(collection);
    }

    @Override
    @Deprecated
    public boolean retainAll(Collection<?> c) {
        List<S> collection = new ArrayList<>();
        for (Object o : c) {
            T t = (T)o;
            collection.add(convertIn.apply(t));
        }
        return items.retainAll(collection);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public T get(int index) {
        return convertOut.apply(items.get(index));
    }

    @Override
    public T set(int index, T element) {
        S s = convertIn.apply(element);
        S listSet = items.set(index, s);
        return convertOut.apply(listSet);
    }

    @Override
    public void add(int index, T element) {
        items.add(index, convertIn.apply(element));
    }

    @Override
    public T remove(int index) {
        return convertOut.apply(items.remove(index));
    }

    @Override
    @Deprecated
    public int indexOf(Object o) {
        S s = convertIn.apply((T) o);
        return items.indexOf((s));
    }

    @Override
    @Deprecated
    public int lastIndexOf(Object o) {
        S s = convertIn.apply((T) o);
        return items.lastIndexOf(s);
    }

    @Override
    public ListIterator<T> listIterator() {
        return convertOutList().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return convertOutList().listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return convertOutList().subList(fromIndex, toIndex);
    }

    private List<T> convertOutList() {
        List<T> list = new ArrayList<>();
        for (S item : items) {
            list.add(convertOut.apply(item));
        }
        return list;
    }

    private Collection<S> colTToColS (Collection<? extends T> c) {
        Collection<S> col = new ArrayList<>();
        for (Object o : c) {
            T t = (T) o;
            col.add(convertIn.apply(t));
        }
        return col;
    }
}
