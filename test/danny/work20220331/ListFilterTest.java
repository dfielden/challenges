package danny.work20220331;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ListFilterTest {
    private final Predicate<Integer> divisibleByTwo = num -> num % 2 == 0;
    private final Predicate<String> lengthGreaterThanFive = string -> string.length() > 5;
    private final List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    private final List<String> words = new ArrayList<>(Arrays.asList("hello", "gooddanny", "boat", "goaterstoat", "tallythesheep"));

    @Test
    public void empty() {
        ListFilter<Integer> listFilter = new ListFilter<>();
        List<Integer> nums = new ArrayList<>();
        List<Integer> result = listFilter.filter(nums, divisibleByTwo);
        assertTrue(result.isEmpty());
    }

    @Test
    public void intPredicate() {
        ListFilter<Integer> listFilter = new ListFilter<>();
        List<Integer> result = listFilter.filter(nums, divisibleByTwo);
        assertEquals(5, result.size());
        assertFalse(result.contains(1));
        assertFalse(result.contains(3));
        assertFalse(result.contains(5));
        assertFalse(result.contains(7));
        assertFalse(result.contains(9));
        assertTrue(result.contains(2));
        assertTrue(result.contains(4));
        assertTrue(result.contains(6));
        assertTrue(result.contains(8));
        assertTrue(result.contains(10));
    }

    @Test
    public void stringPredicate() {
        ListFilter<String> listFilter = new ListFilter<>();
        List<String> result = listFilter.filter(words, lengthGreaterThanFive);
        assertEquals(3, result.size());
        assertFalse(result.contains("hello"));
        assertFalse(result.contains("boat"));
        assertTrue(result.contains("gooddanny"));
        assertTrue(result.contains("goaterstoat"));
        assertTrue(result.contains("tallythesheep"));
    }



}