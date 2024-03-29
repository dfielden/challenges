package danny.work20220331;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class ListFilter<T> {

    public List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
}
