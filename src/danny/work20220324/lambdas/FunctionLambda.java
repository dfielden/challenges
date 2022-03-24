package danny.work20220324.lambdas;

import java.util.function.Function;

public class FunctionLambda {
    public static void main(String[] args) {
        // regular class
        MultiplyByTwo multiplyByTwo = new MultiplyByTwo();
        System.out.println(multiplyByTwo.apply(2));

        // anonymous class
        System.out.println(anonymousMultiplyByTwo.apply(2));

        // lambda
        System.out.println(lambdaMultiplyByTwo.apply(2));

    }

    // regular class
    static final class MultiplyByTwo implements Function<Integer, String> {
        @Override
        public String apply(Integer n) {
            return String.format("%d multiplied by 2 is %d", n, n*2);
        }
    }

    // anonymous class
    static final Function<Integer, String> anonymousMultiplyByTwo = new Function<Integer, String>() {
        @Override
        public String apply(Integer n) {
            return String.format("%d multiplied by 2 is %d", n, n*2);
        }
    };

    // lambda
    static final Function<Integer, String> lambdaMultiplyByTwo = n -> String.format("%d multiplied by 2 is %d", n, n*2);;
}
