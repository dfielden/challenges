package danny.work20220322;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StringTransformer {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("goodbye");
        words.add("iexist");

        List<String> transformedWords = transform(words, mixedCase);
        for (String word : transformedWords) {
            System.out.println(word);
        }

        System.out.println();

        List<String> words2 = new ArrayList<>();
        words2.add("hello");
        words2.add("goodbye");
        words2.add("iexist");
        List<Function<String, String>> functions = new ArrayList<>();
        functions.add(addWorm);
        functions.add(new Reverse());
        functions.add(mixedCase);
        List<String> multiTransformedWords = transformMultipleTimes(words2, functions);
        for (String s : multiTransformedWords) {
            System.out.println(s);
        }
    }

    static List<String> transform(List<String> input, Function<String, String> fn) {
        List<String> result = new ArrayList<>();
        for (String s : input) {
            result.add(fn.apply(s));
        }
        return result;
    }

    static List<String> transformMultipleTimes(List<String> input, List<Function<String, String>> fns) {
        List<String> result = new ArrayList<>();
        for (String s : input) {
            for (Function<String, String> fn : fns) {
                s = fn.apply(s);
            }
            result.add(s);
        }
        return result;
    }

    // with anonymous class
    static Function<String, String> addWorm = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return s + "WORM";
        }
    };

    // with class
    static class Reverse implements Function<String, String> {
        @Override
        public String apply(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(s.length() - 1 - i));
            }
            return sb.toString();
        }
    }

    // with anonymous class
    static Function<String, String> mixedCase = new Function<String, String>() {
        @Override
        public String apply(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 0) {
                    sb.append(Character.toUpperCase(s.charAt(i)));
                } else {
                    sb.append(Character.toLowerCase(s.charAt(i)));
                }
            }
            return sb.toString();
        }
    };


    static class Danny implements Function<String, String> {
        @Override
        public String apply(String s) {
            return null;
        }
    }

    static Danny danny1 = new Danny();
    static Danny danny2 = new Danny();



}
