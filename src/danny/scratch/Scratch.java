package danny.scratch;


import danny.work20220629.Reducible;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Scratch {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Daniel"));
        list.add(new Person("D"));
        list.add(new Person("Dan"));
        list.add(new Person("DanielFielden"));
        list.add(new Person("Dany"));

        System.out.println(list);
        Collections.sort(list, comparator);
        System.out.println(list);

        Collections.sort(list, Comparator.comparing(Person::getName));
        System.out.println(list);
        Collections.sort(list, (p1, p2) -> Integer.compare(p1.getName().length(), p2.getName().length()));
        System.out.println(list);

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 2, 5)));

        try {
            Files.lines(Paths.get("ppl.txt"))
                    .map(line -> line.split(","))
                    .filter(array -> array.length == 3)
            .map(array -> new Ppl(array[0], array[1], Integer.parseInt(array[2])))
            .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // functional interface
        Integer[] intArr = new Integer[]{1,2,3,4,5,6,7,8};

        // lambda
        System.out.println(lambdaRed.reduce(intArr));

        // anonymous class
        System.out.println(reducible.reduce(intArr));

        // class
        GoodDanny gd = new GoodDanny();
        System.out.println(gd.reduce(intArr));

        System.err.println("error message");
    }

    static Comparator<Person> comparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return Integer.compare(o1.name.length(), o2.name.length());
        }
    };

    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < emails.length; i++) {
            String prefix = emails[i].split("@")[0];
            String postfix = "@" + emails[i].split("@")[1];
            System.out.println(postfix);
            String noPlus = prefix.split("\\+")[0];
            String noDot = noPlus.replace(".", "");
            System.out.println(noDot+postfix);
            set.add(noDot + postfix);
        }
        return set.size();
    }

    public static final class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static final class Ppl {
        private final String firstname;
        private final String lastname;
        private final int age;

        public Ppl(String firstname, String lastname, int age) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
        }

        public String getFirstname() {
            return this.firstname;
        }

        public String getLastname() {
            return this.lastname;
        }

        public int getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            return String.format("name: %s %s, age: %d", firstname, lastname, age);
        }
    }


    public static double multiply(double a) {
        return a * 2;
    }

    public static long add (long a) {
        return a + 2;
    }

    public final static class GoodDanny implements Reducible<Integer>, Serializable {
        @Override
        public Integer reduce(Integer[] arr) {
            int result = 0;
            for (int i : arr) {
                result += i;
            }
            return result;
        }
    }

    static final Reducible<Integer> reducible = new Reducible<Integer>() {
        @Override
        public Integer reduce(Integer[] arr) {
            int result = 0;
            for (int i : arr) {
                result += i;
            }
            return result;
        }
    };

    static final Reducible<Integer> lambdaRed = arr -> {
        int result = 0;
        for (int i : arr) {
            result += i;
        }
        return result;
    };



}

