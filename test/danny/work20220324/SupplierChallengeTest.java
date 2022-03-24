package danny.work20220324;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class SupplierChallengeTest {

    @Test
    public void emptyListOfStringAddNothing() {
        List<String> strings = new ArrayList<>();
        assertTrue(strings.isEmpty());
        SupplierChallenge.fillList(strings, new StringSupplier(), 0);
        assertTrue(strings.isEmpty());
    }

    @Test
    public void supplyToEmptyListOfString() {
        List<String> strings = new ArrayList<>();
        assertTrue(strings.isEmpty());
        int totalStringsToAdd = 5;
        SupplierChallenge.fillList(strings, new StringSupplier(), totalStringsToAdd);
        assertEquals(totalStringsToAdd, strings.size());
    }

    @Test
    public void nonEmptyListOfStringAddNothing() {
        List<String> strings = new ArrayList<>();
        strings.add("abc");
        strings.add("def");
        strings.add("ghi");
        assertEquals(3, strings.size());
        SupplierChallenge.fillList(strings, new StringSupplier(), 0);
        assertEquals(3, strings.size());
    }

    @Test
    public void supplyToNonEmptyListOfString() {
        List<String> strings = new ArrayList<>();
        strings.add("abc");
        strings.add("def");
        strings.add("ghi");
        int initialListSize = strings.size();
        int totalStringsToAdd = 5;
        assertEquals(3, strings.size());
        SupplierChallenge.fillList(strings, new StringSupplier(), totalStringsToAdd);
        assertEquals(initialListSize + totalStringsToAdd, strings.size());
    }

    @Test
    public void emptyListOfIntegerAddNothing() {
        List<Integer> ints = new ArrayList<>();
        assertTrue(ints.isEmpty());
        SupplierChallenge.fillList(ints, new IntegerSupplier(), 0);
        assertTrue(ints.isEmpty());
    }

    @Test
    public void supplyToEmptyListOfIntegers() {
        List<Integer> ints = new ArrayList<>();
        assertTrue(ints.isEmpty());
        int totalIntsToAdd = 5;
        SupplierChallenge.fillList(ints, new IntegerSupplier(), totalIntsToAdd);
        assertEquals(totalIntsToAdd, ints.size());
    }

    @Test
    public void nonEmptyListOfIntegersAddNothing() {
        List<Integer> ints = new ArrayList<>();
        ints.add(22);
        ints.add(44);
        ints.add(66);
        assertEquals(3, ints.size());
        SupplierChallenge.fillList(ints, new IntegerSupplier(), 0);
        assertEquals(3, ints.size());
    }

    @Test
    public void supplyToNonEmptyListOfInteger() {
        List<Integer> ints = new ArrayList<>();
        ints.add(22);
        ints.add(44);
        ints.add(66);
        int initialListSize = ints.size();
        int totalIntegersToAdd = 5;
        assertEquals(3, ints.size());
        SupplierChallenge.fillList(ints, new IntegerSupplier(), totalIntegersToAdd);
        assertEquals(initialListSize + totalIntegersToAdd, ints.size());
    }


    static class StringSupplier implements Supplier<String> {
        @Override
        public String get() {
            Random random = new Random();
            int StringLength = random.nextInt(10);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < StringLength; i++) {
                sb.append((char)(random.nextInt(26) + 'a'));
            }
            return sb.toString();
        }
    }

    static class IntegerSupplier implements Supplier<Integer> {
        @Override
        public Integer get() {
            Random random = new Random();
            return random.nextInt(100);
        }
    }

}