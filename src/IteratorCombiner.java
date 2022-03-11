import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorCombiner<T> implements Iterator<T> {
    private final ArrayList<Iterator<T>> listOfIterators;
    private int index = 0;  // Index of the *next* iterator to use.
    Iterator<T> currentIterator;


    public IteratorCombiner(ArrayList<Iterator<T>> listOfIterators) {
        this.listOfIterators = listOfIterators;
        if (listOfIterators.size() > 0) {
            currentIterator = listOfIterators.get(index++);
        }
    }

    @Override
    public boolean hasNext() {
        if (listOfIterators == null || listOfIterators.size() == 0) {
            return false;
        }
        if (currentIterator.hasNext()) {
            return true;
        } else {
            // Find another iterator with elements.
            while (index < listOfIterators.size() - 1) {
                currentIterator = listOfIterators.get(index++);
                if (currentIterator.hasNext()) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public T next() {
        if (hasNext()) {
            return currentIterator.next();
        }
        throw new NoSuchElementException();
    }










}
