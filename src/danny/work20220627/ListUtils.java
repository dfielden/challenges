/**
 *  Static utils methods for lists and iterables.
 */

package danny.work20220627;

import java.util.*;
import java.util.function.Function;

public final class ListUtils {
    private ListUtils() {
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hi");
        list.add("bye");
        list.add("good");
        list.add("danny");

        System.out.println(size(list));
        List<String> newList = transformElements(list, addHello);
        System.out.println(list);
        System.out.println(newList);
        System.out.println(removeEveryOther(newList));
    }

    public static int size(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            Collection<?> collection = (Collection<?>) iterable;
            return collection.size();
        }
        Iterator<?> it = iterable.iterator();
        int count = 0;
        while (it.hasNext()) {
            count += 1;
            it.next();
        }
        return count;
    }

    public static <T, R> List<R> transformElements(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static <T> List<T> removeEveryOther(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        List<T> result = new ArrayList<>();
        int count = 0;
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (count % 2 == 0) {
                result.add(t);
            }
            count += 1;
        }
        return result;
    }

    static final Function<String, String> addHello = (s -> s + "hello");
}

