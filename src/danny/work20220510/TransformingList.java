package danny.work20220510;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TransformingList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("danny");
        ReverseString rs = new ReverseString();
        for (String s : list) {
            System.out.println(rs.apply(s));
        }
    }

    static List<String> transform(List<String> input, Function<String, String> fn) {
        List<String> result = new ArrayList<>();
        for (String s : input) {
            result.add(fn.apply(s));
        }
        return result;
    }

    static class ReverseString implements Function<String, String> {
        @Override
        public String apply(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = s.length() - 1; i > -1; i--) {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }
}
