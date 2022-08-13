package danny.work20220627;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

public class ListUtilsTest {
    @Test
    public void transform() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        List<Integer> transformed = ListUtils.transformElements(numbers, num -> num * 2);
        for (int i = 0; i < numbers.size(); i++) {
            assertEquals(numbers.get(i) * 2, (int)transformed.get(i));
        }
    }

    final static Function<Integer, Integer> multiplyByTwo = (num -> num * 2);
}