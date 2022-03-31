package danny.work20220331;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class TransformingIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;
    private final Function<T, T> function;

    public TransformingIterable(Iterable<T> iterable, Function<T, T> function) {
        this.iterable = iterable;
        this.function = function;

    }

    public Iterable<T> getIterable() {
        return this.iterable;
    }

    public Function<T, T> getFunction() {
        return this.function;
    }

    public List<T> transform() {
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) {
            T t = iterator.next();
            list.add(function.apply(t));
        }
        return list;
    }

    @Override
    public Iterator<T> iterator() {
        return iterable.iterator();
    }
}
