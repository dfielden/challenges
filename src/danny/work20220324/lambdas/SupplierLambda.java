package danny.work20220324.lambdas;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierLambda {

    public static void main(String[] args) {
        // regular class
        RandomIntClass randomIntClass = new RandomIntClass();
        System.out.println(randomIntClass.get());

        // anonymous class
        System.out.println(anonIntSupplier.get());

        // lambda
        System.out.println(randomInt.get());
    }

    // regular class
    static final class RandomIntClass implements Supplier<Integer> {
        @Override
        public Integer get() {
            return new Random().nextInt(100);
        }
    }

    // anonymous class
    static final Supplier<Integer> anonIntSupplier = new Supplier<Integer>() {
        @Override
        public Integer get() {
            return new Random().nextInt(100);
        }
    };

    // lambda
    static final Supplier<Integer> randomInt = () -> new Random().nextInt(100);

    // lambdas can be multiline
    static final Supplier<Integer> randomInt2 = () -> {
        if (1 == 1) {
            return new Random().nextInt(100);
        } else {
            return new Random().nextInt(100);
        }
    };
}
