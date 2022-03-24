package danny.work20220324.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerLambda {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("goodbye");

        // regular class
        WordPrinter printer = new WordPrinter();
        for (String word : words) {
            printer.accept(word);
        }

        // anonymous class
        for (String word : words) {
            AnonymousPrint.accept(word);
        }

        //lamdba
        for (String word : words) {
            print.accept(word);
        }

        // or use the forEach method which applies the accept method to each item
        words.forEach(print);

    }

    // regular class
    static final class WordPrinter implements Consumer<String> {
        @Override
        public void accept(String word) {
            System.out.println(word);
        }
    }

    // anonymous class
    static final Consumer<String> AnonymousPrint =  new Consumer<String>() {
        @Override
        public void accept(String word) {
            System.out.println(word);
        }
    };

    // lambda
    static final Consumer<String> print = word -> System.out.println(word);




}
