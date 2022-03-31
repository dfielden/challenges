package danny.work20220331;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

public class TransformingIterableTest {

    private final Function<String, String> stringToUpper = String::toUpperCase;
    private final Function<String, String> stringToLower = String::toLowerCase;
    private final Function<Integer, Integer> multiplyByTwo = i -> i * 2;

    @Test
    public void empty() {
        TransformingIterable<String> transformingIterable = new TransformingIterable<>(new ArrayList<>(), stringToUpper);
        transformingIterable.transform();
        assertEquals(new ArrayList<String>(), transformingIterable.getIterable());
    }

    @Test
    public void iterableToUpperCase() {
        TransformingIterable<String> transformingIterable = new TransformingIterable<>(generateLowerCaseList(), stringToUpper);
        List<String> transformed = transformingIterable.transform();
        assertEquals(generateUpperCaseList(), transformed);
    }

    @Test
    public void iterableToLowerCase() {
        TransformingIterable<String> transformingIterable = new TransformingIterable<>(generateUpperCaseList(), stringToLower);
        List<String> transformed = transformingIterable.transform();
        assertEquals(generateLowerCaseList(), transformed);
    }

    @Test
    public void multiplyByTwo() {
        TransformingIterable<Integer> transformingIterable = new TransformingIterable<>(generateNums(), multiplyByTwo);
        List<Integer> transformed = transformingIterable.transform();
        List<Integer> copyOfOriginal = generateNums();
        for (int i = 0; i < copyOfOriginal.size(); i++) {
            assertEquals(copyOfOriginal.get(i) * 2, (int)transformed.get(i));
        }

    }

    private List<String> generateUpperCaseList() {
        return new ArrayList<>(Arrays.asList("HELLO", "GOODBYE", "TALLY"));
    }

    private List<String> generateLowerCaseList() {
        return new ArrayList<>(Arrays.asList("hello", "goodbye", "tally"));
    }

    private List<Integer> generateNums() {
        return new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    }

}