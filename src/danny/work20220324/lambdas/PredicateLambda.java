package danny.work20220324.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateLambda {
    public static void main(String[] args) {
        DivisibleByTwo divisibleByTwo = new DivisibleByTwo();
        System.out.printf("5 divisible by 2: %s%n", divisibleByTwo.test(5));
        System.out.printf("5 divisible by 3: %s%n", divisibleByThree.test(5));
        System.out.printf("5 divisible by 5: %s%n", divisibleByFive.test(5));
        System.out.println();

        System.out.printf("13 divisible by 2: %s%n", divisibleByTwo.test(13));
        System.out.printf("13 divisible by 3: %s%n", divisibleByThree.test(13));
        System.out.printf("13 divisible by 5: %s%n", divisibleByFive.test(13));
        System.out.println();

        System.out.printf("30 divisible by 2: %s%n", divisibleByTwo.test(30));
        System.out.printf("30 divisible by 3: %s%n", divisibleByThree.test(30));
        System.out.printf("30 divisible by 5: %s%n", divisibleByFive.test(30));

        List<String> inputs = new ArrayList<>();
        inputs.add("gubbyworm");
        inputs.add("dogubby");
        inputs.add("hf");
        inputs.add("dannyworm");
        inputs.add("tally");
        inputs.add("worm");

        List<String> filtered = filter(inputs, s -> s.contains("worm"));
        System.out.println("filtered = " + filtered);
    }

    static List<String> filter(List<String> input, Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for (String s : input) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    // regular class
    static final class DivisibleByTwo implements Predicate<Integer> {
        @Override
        public boolean test(Integer i) {
            return i % 2 == 0;
        }
    }

    // anonymous class
    static final Predicate<Integer> divisibleByThree = new Predicate<Integer>() {
        @Override
        public boolean test(Integer i) {
            return i % 3 == 0;
        }
    };

    // lambda
    static final Predicate<Integer> divisibleByFive = i -> i % 5 == 0;
}
