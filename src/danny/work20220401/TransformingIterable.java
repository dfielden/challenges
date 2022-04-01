package danny.work20220401;

import java.util.Iterator;
import java.util.function.Function;

public class TransformingIterable<T> implements Iterable<T> {
    private final Iterable<T> iterable;
    private final Function<T, T> function;

    public TransformingIterable(Iterable<T> iterable, Function<T, T> function) {
        this.iterable = iterable;
        this.function = function;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = iterable.iterator();
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public T next() {
                return function.apply(it.next());
            }
        };
    }
}