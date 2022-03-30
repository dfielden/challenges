package danny.work20220228;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static org.junit.Assert.*;

public class TransformingListTest {
    private static final Function<String, String> toUpper = s -> s.toUpperCase();
    private static final Function<String, String> toLower = s -> s.toLowerCase();
    private static final Function<String, Integer> stringToInteger = s -> Integer.valueOf(s);
    private static final Function<Integer, String> integerToString = i -> i.toString();
    private final List<String> lowerCaseList = Arrays.asList("hello", "good", "danny", "hello");
    private final List<String> otherWords = Arrays.asList("howdy", "goat", "dog");
    private final List<String> upperCaseList = Arrays.asList("HELLO", "GOOD", "DANNY");

    @Test
    public void empty() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        assertEquals(0, transformLowerToUpper.size());
        assertTrue(transformLowerToUpper.isEmpty());
        assertEquals(0, transformLowerToUpper.toArray().length);
    }

    @Test
    public void add() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        for (int i = 0; i < lowerCaseList.size(); i++) {
            transformLowerToUpper.add(lowerCaseList.get(i));
            assertEquals(i + 1, transformLowerToUpper.size());
            assertFalse(transformLowerToUpper.isEmpty());
        }
        assertTrue(transformLowerToUpper.containsAll(lowerCaseList));
        assertFalse(transformLowerToUpper.containsAll(upperCaseList));

        assertEquals(0, transformLowerToUpper.indexOf("hello"));
        assertEquals(1, transformLowerToUpper.indexOf("good"));
        assertEquals(2, transformLowerToUpper.indexOf("danny"));

        assertEquals(3, transformLowerToUpper.lastIndexOf("hello"));
        assertEquals(1, transformLowerToUpper.lastIndexOf("good"));
        assertEquals(2, transformLowerToUpper.lastIndexOf("danny"));

    }

    @Test
    public void addAll() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        assertEquals(4, transformLowerToUpper.size());
    }

    @Test
    public void contains() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        assertTrue(transformLowerToUpper.contains("hello"));
        assertTrue(transformLowerToUpper.contains("good"));
        assertTrue(transformLowerToUpper.contains("danny"));
        assertFalse(transformLowerToUpper.contains("howdy"));
        assertFalse(transformLowerToUpper.contains("goat"));
        assertFalse(transformLowerToUpper.contains("dog"));
    }

    @Test
    public void addAtSpecifiedIndex() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        transformLowerToUpper.add(0, "zero");
        transformLowerToUpper.add(2, "TWO");
        transformLowerToUpper.add(transformLowerToUpper.size(), "final");
        assertEquals(7, transformLowerToUpper.size());
        assertEquals("zero", transformLowerToUpper.get(0));
        assertEquals("hello", transformLowerToUpper.get(1));
        assertEquals("two", transformLowerToUpper.get(2));
        assertEquals("good", transformLowerToUpper.get(3));
        assertEquals("danny", transformLowerToUpper.get(4));
        assertEquals("hello", transformLowerToUpper.get(5));
        assertEquals("final", transformLowerToUpper.get(6));
    }

    @Test
    public void set() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        transformLowerToUpper.set(0, "zero");
        transformLowerToUpper.set(2, "TWO");
        transformLowerToUpper.set(3, "final");
        assertEquals(4, transformLowerToUpper.size());
        assertEquals("zero", transformLowerToUpper.get(0));
        assertEquals("good", transformLowerToUpper.get(1));
        assertEquals("two", transformLowerToUpper.get(2));
        assertEquals("final", transformLowerToUpper.get(3));
    }

    @Test
    public void addAllAtSpecifiedIndex() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.add("start");
        transformLowerToUpper.add("end");
        transformLowerToUpper.addAll(1, lowerCaseList);
        assertEquals(transformLowerToUpper.get(0), "start");
        assertEquals(transformLowerToUpper.get(transformLowerToUpper.size() - 1), "end");
        assertEquals(6, transformLowerToUpper.size());
    }

    @Test
    public void clearAll() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        transformLowerToUpper.clear();
        assertEquals(0, transformLowerToUpper.size());
    }

    @Test
    public void remove() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        assertFalse(transformLowerToUpper.remove("howdy"));
        assertTrue(transformLowerToUpper.remove("hello"));
        assertEquals(3, transformLowerToUpper.size());
    }

    @Test
    public void removeAtSpecifiedIndex() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        transformLowerToUpper.remove(2);
        assertEquals(3, transformLowerToUpper.size());
        assertEquals("hello", transformLowerToUpper.get(0));
        assertEquals("good", transformLowerToUpper.get(1));
        assertEquals("hello", transformLowerToUpper.get(2));
    }

    @Test
    public void removeAll() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        transformLowerToUpper.removeAll(otherWords);
        assertEquals(4, transformLowerToUpper.size());
        transformLowerToUpper.removeAll(lowerCaseList);
        assertEquals(0, transformLowerToUpper.size());
    }

    @Test
    public void retainAll() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        List<String> retainCollection = new ArrayList<>();
        retainCollection.add("hello");
        transformLowerToUpper.retainAll(retainCollection);
        assertEquals(2, transformLowerToUpper.size());
        assertEquals("hello", transformLowerToUpper.get(0));
        assertEquals("hello", transformLowerToUpper.get(1));
    }

    @Test
    public void subList() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        List<String> subList = transformLowerToUpper.subList(1, 3);

        assertEquals(4, transformLowerToUpper.size());
        assertEquals(2, subList.size());
        assertEquals("good", subList.get(0));
        assertEquals("danny", subList.get(1));
    }

    @Test
    public void listIterator() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        List<String> transformingList = new ArrayList<>();
        Iterator<String> transformingIterator = transformLowerToUpper.listIterator();
        transformingIterator.forEachRemaining(transformingList::add);
        assertEquals(lowerCaseList, transformingList);
        assertNotEquals(otherWords, transformingList);
    }

    @Test
    public void listIteratorFromSpecifiedIndex() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        List<String> transformingList = new ArrayList<>();
        Iterator<String> transformingIterator = transformLowerToUpper.listIterator(2);
        transformingIterator.forEachRemaining(transformingList::add);
        assertEquals(lowerCaseList.subList(2, 4), transformingList);
        assertNotEquals(otherWords.subList(2, 3), transformingList);
    }

    @Test
    public void toArray() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);
        assertArrayEquals(lowerCaseList.toArray(), transformLowerToUpper.toArray());
        assertArrayEquals(lowerCaseList.toArray(new String[]{"hello", "good"}), transformLowerToUpper.toArray(new String[]{"hello", "good"}));
    }

    @Test
    public void addLowerTransformToUpper() {
        TransformingList<String, String> transformLowerToUpper = new TransformingList<>(toUpper, toLower);
        transformLowerToUpper.addAll(lowerCaseList);

        assertArrayEquals(lowerCaseList.toArray(), transformLowerToUpper.toArray());
        for (String s : transformLowerToUpper) {
            for (int i = 0; i < s.length(); i++) {
                assertTrue(Character.isLowerCase(s.charAt(i)));
            }
        }
    }

    @Test
    public void addUpperTransformToLower() {
        TransformingList<String, String> transformUpperToLower = new TransformingList<>(toLower, toUpper);
        for (int i = 0; i < upperCaseList.size(); i++) {
            transformUpperToLower.add(lowerCaseList.get(i));
            assertEquals(i + 1, transformUpperToLower.size());
            assertFalse(transformUpperToLower.isEmpty());
        }

        assertArrayEquals(upperCaseList.toArray(), transformUpperToLower.toArray());
        for (String s : transformUpperToLower) {
            for (int i = 0; i < s.length(); i++) {
                assertTrue(Character.isUpperCase(s.charAt(i)));
            }
        }
    }

    @Test
    public void addStringTransformToInteger() {
        TransformingList<String, Integer> transformStringToInteger = new TransformingList<>(stringToInteger, integerToString);
        for (int i = 0; i < 100; i++) {
            transformStringToInteger.add(Integer.toString(i));
            assertEquals(i + 1, transformStringToInteger.size());
            assertFalse(transformStringToInteger.isEmpty());
        }

        for (int i = 0; i < transformStringToInteger.size(); i++) {
            assertEquals(Integer.toString(i), transformStringToInteger.get(i));
        }
    }

    @Test
    public void addIntegerTransformToString() {
        TransformingList<Integer, String> transformIntegerToString = new TransformingList<>(integerToString, stringToInteger);
        for (int i = 0; i < 100; i++) {
            transformIntegerToString.add(i);
            assertEquals(i + 1, transformIntegerToString.size());
            assertFalse(transformIntegerToString.isEmpty());
        }

        for (int i = 0; i < transformIntegerToString.size(); i++) {
            assertEquals(new Integer(i), transformIntegerToString.get(i));
        }
    }

}

